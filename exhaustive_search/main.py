from model.models import Cost, DC, SP, VMRequest, MultiTierAppRequest
import os

datadir = "data"
print("CoMCLOUD")


def configure_sp(spname, ndc, nhost) -> SP:
    sp = SP(spname)

    # load costs
    costarray = None
    costfilename = os.path.join(datadir, "cost_%s.txt"%(spname, ))
    with open(costfilename) as costfile:
        lines = costfile.readlines()
        lines = [l.strip().split(" ") for l in lines]
        costarray = lines
    
    # load hosts
    hostarray = []
    hostfilename = os.path.join(datadir, "hosts_%s.txt"%(spname, ))
    with open(hostfilename) as hostsfile:
        hostsfile.readline()
        for n in range(ndc):
            hostarray.append([])
            hostsfile.readline()
            for h in range(nhost):
                thishost = hostsfile.readline().strip().split(" ")
                hostarray[n].append((thishost[4], thishost[6]))

    # load inter dc latency
    latencyarray = []
    latencyfilename = os.path.join(datadir, "latency_%s.txt"%(spname, ))
    with open(latencyfilename) as latencyfile:
        for n in range(ndc):
            l = latencyfile.readline().strip().split()
            l = [float(item) for item in l]
            latencyarray.append(l)

    # create dcs and add hosts
    for n in range(ndc):
        dc = DC(Cost(costarray[0][n], costarray[1][n], costarray[2][n], costarray[3][n]))
        dc.set_name(spname+"_"+str(n))
        for i in range(nhost):
            # print(dc, hostarray[n][i])
            dc.add_host(int(hostarray[n][i][0]), int(hostarray[n][i][1]))
        sp.add_dc(dc)
    
    sp.set_inter_dc_latency(latencyarray)

    return sp


def read_app_requests():
    requestsfilename = os.path.join(datadir, "request_250.txt")
    # requestsfilename = os.path.join(datadir, "requests.txt")
    apprequestlist = []
    with open(requestsfilename) as requestsfile:
        apprequest = None
        for line in requestsfile.readlines():
            line = line.strip()
            if line.startswith("Multi"):
                if apprequest:
                    apprequestlist.append(apprequest)
                # application request
                apprequest = MultiTierAppRequest()
            elif line.startswith("VM"):
                apprequest.add_vm_request(int(line.split(" ")[4]), int(line.split(" ")[6]))
        if apprequest:
            apprequestlist.append(apprequest)
    return apprequestlist


# 3 SPs
amazon = configure_sp("amazon", 4, 32)
azure = configure_sp("azure", 4, 32)
gcp = configure_sp("gcp", 4, 32)
sp_array = [amazon, azure, gcp]

# amazon.print_details()
app_request_list = read_app_requests()

aggregate_cost = 0
aggregate_latency = 0
for app_request in app_request_list[0:30]:
    bid_array = []

    # get bid from sps
    for sp in sp_array:
        bid_array.append(sp.get_bid(app_request))
    
    if len(sp_array) != len(bid_array):
        raise "Something went wrong"

    # choose minimum bid
    maxbid = float("-inf")
    chosensp = None
    print(bid_array)
    for bididx in range(len(bid_array)):
        if bid_array[bididx]:
            print(bid_array[bididx][0], maxbid)
            if bid_array[bididx][0] > maxbid:
                maxbid = bid_array[bididx][0]
                chosensp = bididx
    if chosensp is None:
        raise "No valid bid found"

    sp_array[chosensp].assign_application_placement(bid_array[chosensp][1])
    # print(chosensp, sp_array[chosensp].get_capacity())
    aggregate_cost += bid_array[chosensp][2]
    aggregate_latency += bid_array[chosensp][3]

print(aggregate_cost, aggregate_latency)