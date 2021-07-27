

class VMTypes:
    SMALL = "small"
    MEDIUM = "medium"
    LARGE = "large"
    XLARGE = "xlarge"

    sizes = {
        SMALL: {
            "cpu": 1,
            "mem": 3840
        },
        MEDIUM:{
            "cpu": 2,
            "mem": 7680
        },
        LARGE: {
            "cpu": 4,
            "mem": 15360
        },
        XLARGE: {
            "cpu": 8,
            "mem": 30720
        }
    }

    @classmethod
    def get_vm_types(cls):
        return [cls.SMALL, cls.MEDIUM, cls.LARGE, cls.XLARGE]

    @classmethod
    def get_size(cls, vm_type):
        if vm_type not in cls.get_vm_types():
            raise
        return cls.sizes[vm_type]
    
    @classmethod
    def get_vm_type_from_size(cls, cpu_size, mem_size):
        for vm_type, vm_size in cls.sizes.items():
            if int(cpu_size) == vm_size["cpu"] and int(mem_size) == vm_size["mem"]:
                return vm_type
        return None


class VMRequest:
    def __init__(self, vm_type_str):
        if vm_type_str not in VMTypes.get_vm_types():
            raise
        self.vmtype = vm_type_str
        self.size = VMTypes.get_size(vm_type_str)

    def __repr__(self):
        return "vm_"+str(self.size)


class MultiTierAppRequest:
    def __init__(self) -> None:
        self.vm_requests = []
    
    def add_vm_request(self, cpu_size, memory_size):
        vmtype = VMTypes.get_vm_type_from_size(cpu_size, memory_size)
        self.vm_requests.append(VMRequest(vmtype))
    
    def __repr__(self) -> str:
        return str(self.vm_requests)


class Cost:
    def __init__(self, small, medium, large, xlarge):
        self.costs = {
            VMTypes.SMALL : float(small),
            VMTypes.MEDIUM : float(medium),
            VMTypes.LARGE : float(large),
            VMTypes.XLARGE : float(xlarge)
        }

    def get_cost(self, vm_type):
        return self.costs[vm_type]
    
    def __repr__(self) -> str:
        return str(self.costs)


class Host:
    def __init__(self, dc, cpu_capacity: int, memory_capacity: int):
        self.cpu_capacity = cpu_capacity
        self.memory_capacity = memory_capacity
        self.dc = dc
        self.assigned_vms = [] # type: list[VMRequest]
    
    def assign_vm(self, vm: VMRequest):
        self.cpu_capacity = self.cpu_capacity - vm.size["cpu"]
        self.memory_capacity = self.memory_capacity - vm.size["mem"]
        self.assigned_vms.append(vm)

    def deassign_vm(self, vm: VMRequest):
        self.cpu_capacity = self.cpu_capacity + vm.size["cpu"]
        self.memory_capacity = self.memory_capacity + vm.size["mem"]
        self.assigned_vms.remove(vm)
    
    def get_assigned_vms(self):
        return self.assigned_vms
    
    def __repr__(self) -> str:
        return str(self.cpu_capacity) + " " +  str(self.memory_capacity)


class DC:
    def __init__(self, cost):
        self.hosts = [] # type: list[Host]
        self.sp = None
        self.cost = cost # type: Cost
    
    def get_capacity(self):
        capacity = {"cpu":0, "mem":0}
        for host in self.hosts:
            capacity["cpu"] += host.cpu_capacity
            capacity["mem"] += host.memory_capacity
        return capacity

    def set_name(self, name:str):
        self.name = name
    
    def assign_sp(self, sp):
        self.sp = sp # type: SP

    def add_host(self, cpu_capacity: int, memory_capacity: int):
        new_host = Host(self, cpu_capacity, memory_capacity)
        self.hosts.append(new_host)
    
    def assign_vm(self, vm: VMRequest):
        best_assignment = (None, float("inf"))

        # iterate among hosts, check if capcity is available
        for host in self.hosts:
            new_cpu_capcaity = host.cpu_capacity - vm.size["cpu"]
            new_mem_capcaity = host.memory_capacity - vm.size["mem"]
            if new_cpu_capcaity >= 0 and new_mem_capcaity>= 0:
                if new_cpu_capcaity < best_assignment[1]:
                    best_assignment = (host, new_cpu_capcaity)
        
        if best_assignment[0]:
            best_assignment[0].assign_vm(vm)
            return True

        else:
            return False
    
    def deassign_vm(self, vm: VMRequest):
        for host in self.hosts:
            if vm in host.get_assigned_vms():
                host.deassign_vm(vm)
    
    def current_capacity(self):
        c = 0
        for host in self.hosts:
            c+= host.capacity
        return c

    def __repr__(self) -> str:
        return "dc_"+self.name
    
    def print_details(self):
        print("dc_"+self.name)
        print("hosts:")
        for host in self.hosts:
            print(host)



class SP:
    def __init__(self, spname):
        self.dcs = [] # type: list(DC)
        self.name = spname
    
    def get_capacity(self):
        capacity = {"cpu":0, "mem":0}
        for dc in self.dcs:
            dccap = dc.get_capacity()
            capacity["cpu"] += dccap["cpu"]
            capacity["mem"] += dccap["mem"]
        return capacity
    
    def assign_application_placement(self, placement):
        for vmrequest, dc in placement:
            dc.assign_vm(vmrequest)

    def set_inter_dc_latency(self, inter_dc_latency_matrix):
        self.inter_dc_latency_matrix = inter_dc_latency_matrix
    
    def add_dc(self, dc: DC):
        dc.assign_sp(self)
        dc.set_name(str(len(self.dcs)))
        self.dcs.append(dc)
    
    def _permute(self, vm_list, permute_results, result):
        # print("--->>>", vm_list)
        # print("--->>>", result)
        if result is None:
            result = ()

        # take a vm
        for vmidx, vm in enumerate(vm_list[0:1]):
            # assign in a dc
            for dc in self.dcs:
                # print(dc.current_capacity())
                # print("chose vm:", vm, "dc", dc)
                isassigned = dc.assign_vm(vm)
                # print(isassigned)
                if isassigned:
                    newresult =  result + ((vm, dc),)
                    # permute with other vms
                    if vmidx + 1 < len(vm_list):
                        new_vm_list = vm_list[vmidx+1 : ]
                        self._permute(new_vm_list, permute_results, newresult)
                        # print("--->return")
                    # base case
                    else:
                        # print("appending result")
                        permute_results.append(newresult)

                    # deassign vm from dc
                    dc.deassign_vm(vm)
                else:
                    # try next dc
                    pass
    
    def compute_placement_cost(self, placement):
        total_cost = 0
        for assignment in placement:
            assignedvm, assigneddc = assignment
            assignment_cost = assigneddc.cost.get_cost(assignedvm.vmtype)
            # print(assignedvm, assigneddc, assignment_cost)
            total_cost += assignment_cost
        return total_cost
    
    def compute_placement_latency(self, placement):
        total_latency = 0.0
        total_links = 0.0
        assigned_dc_list = []
        for assignment in placement:
            assignedvm, assigneddc = assignment
            assigned_dc_list.append(assigneddc)
        # print(assigned_dc_list)
        for dc1 in assigned_dc_list:
            for dc2 in assigned_dc_list:
                # print(int(dc1.name), int(dc2.name))
                total_links += 1
                total_latency += self.inter_dc_latency_matrix[int(dc1.name)][int(dc2.name)]
        return total_latency / total_links

    def get_bid(self, app_request:MultiTierAppRequest):

        # find all possible assignments in DCs
        placement_results = []
        
        self._permute(app_request.vm_requests, placement_results, None)
        # print(placement_results)

        if not len(placement_results):
            return None

        placement_cost_latency = {}
        # Make dict of permutation to cost and latency
        for placement in placement_results:
            placement_cost_latency[placement] = {}
            placement_cost_latency[placement]["cost"] = self.compute_placement_cost(placement)
            placement_cost_latency[placement]["latency"] = self.compute_placement_latency(placement)

        # Find Min by cost + latency
        mincostlatency = float("inf")
        chosenplacement = None
        chosenplacement_cost = None
        chosenplacement_latency = None

        # normalize cost and latency across all permutations
        # placement_cost_latency_ = {}


        for placement, costs in placement_cost_latency.items():
            totalcost = costs["cost"] + costs["latency"]
            if totalcost < mincostlatency:
                mincostlatency = totalcost
                chosenplacement = placement
                chosenplacement_cost = costs["cost"]
                chosenplacement_latency = costs["latency"]

        return (mincostlatency, chosenplacement, chosenplacement_cost, chosenplacement_latency)

    def print_details(self):
        print("SP Name:", self.name)
        print("DC details:")
        for dc in self.dcs:
            dc.print_details()
        print(self.inter_dc_latency_matrix)