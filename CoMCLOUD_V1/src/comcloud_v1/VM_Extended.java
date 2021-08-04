/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;

import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Vm;

/**
 *
 * @author sunitapattanayak
 */
public final class VM_Extended 
{
  
    // Defines the specific instance type of the VM, i.e., type 1(1) or type 2 (2) or type 3 (3) or type 4 (4).
    public int type;
    
    
    String owner_SP;
    
    // A Vm reference variable type
    public Vm v = null;
    
    // Id of the Vm
    public int vmid;
    
    //
    public int brokerId;
    
    public VM_Extended
        (int type, String owner_SP, int id, int brokeId)     
         {
             this.type = type;
             this.owner_SP = owner_SP;
             this.vmid = id;
             this.brokerId = brokerId;
             v = createvm(id);
         }
    
        public Vm createvm(int id)
        {
            long disksize=20000;
            int mips = 10000;
            int bandwidth=1000;
            int ram;
            int vcpu;
            String VMM= "XEN";
               
            switch(this.owner_SP)
            {
                case "Azure":
                    
                    switch(this.type)
                    {
                        case 1: 
                            ram = 2*1024; 
                            vcpu = 1;
                            v  = new Vm
                                (this.vmid,
                                 this.brokerId,
                                 mips,
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared());
                            
                            break;
                            
                        case 2: ram = 4*1024; 
                                vcpu = 2;
                                v = new Vm
                                (
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared()); 
                                
                            break;
                            
                        case 3: ram = 8*1024; 
                                vcpu = 4;
                                 v = new Vm
                                ( 
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared());
                             
                              break;
                              
                        case 4: ram = 16*1024; 
                                vcpu = 8;
                                 v = new Vm
                                (
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared()); 
                                 
                            break;     
                    }
                    
                case "Amazon":
                    
                    switch(this.type)
                    {
                        case 1: ram = 2*1024; 
                                vcpu = 1;
                                v = new Vm
                                ( 
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared());
                            break;  
                        
                        case 2: ram = 4*1024; 
                                vcpu = 2;
                                 v = new Vm
                                ( 
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared()); 
                                 
                            break; 
                            
                        case 3: ram = 8*1024; 
                                vcpu = 2;
                                v = new Vm
                                ( 
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared());   
                                
                           break;    
                           
                        case 4: ram = 16*1024; 
                                vcpu = 4;
                                v = new Vm
                                (
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared());  
                            break;    
                    }
                    
                case "Google":
                    
                    switch(this.type)
                    {
                        case 1: ram = 3840; 
                                vcpu = 1;
                                v = new Vm
                                (
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared());
                              break;  
                        case 2: ram = 7680; 
                                vcpu = 2;
                                v = new Vm
                                ( 
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared()); 
                              break;  
                              
                        case 3: ram = 15360; 
                                vcpu = 4;
                                 v = new Vm
                                ( 
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared());   
                            break;
                        case 4: ram = 30720; 
                                vcpu = 8;
                                v = new Vm
                                ( 
                                 this.vmid, 
                                 this.brokerId, 
                                 mips, 
                                 vcpu, 
                                 ram, 
                                 bandwidth,
                                 disksize,
                                 VMM, 
                                 new CloudletSchedulerTimeShared());   
                        break;        
                    }
                    
            }
            
            return v;
        }
        
        public String getowner_SP()
        {
            return this.owner_SP;
        }
        
        public int getinstancetype()
        {
            return this.type;
        }
        
        public Vm getvm()
        {
            return this.v;
        }
        
        
}
