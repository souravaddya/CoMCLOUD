/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerSpaceShared;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;

/**
 *
 * @author sunitapattanayak
 */
public final class DatacenterFederated 
{
    
    // Depicts the specific region of the deployment of the DC.
    public final String region;
    
    // These variables depict the price of 4 VM instance types at this DC.
    
    public final double type1;
    
    public final double type2;
    
    public final double type3;
    
    public final double type4;
    
    // Store the data center reference. 
    public Datacenter DC = null;
    
    
    //int host_startindex;
    
    public DatacenterFederated
        (       String region, 
                double type1, 
                double type2, 
                double type3, 
                double type4
        ) 
                
        throws Exception
        {
            this.region = region;
            this.type1 = type1;
            this.type2 = type2;
            this.type3 = type2;
            this.type4 = type4;
            //this.host_startindex = this.host_startindex;
            DC = createDatacenter();
        }
       
      /* public int lastindex()
       {
           return this.host_startindex;
       }*/
        
       public Datacenter createDatacenter() throws Exception
       {
           // Datacenter DC = null;
           
           //Base number of MIPS for type 1 hosts.
            int mips1 =1000000;
           
            // Generate the first pe List
            List<Pe> pelist1 = new ArrayList<>();
            
            for(int pecount = 0; pecount< 16 ; pecount++)
            {
              pelist1.add(new Pe(pecount, new PeProvisionerSimple(mips1))); 
              
            }
        
            // Generate the second pe list for type 2 hosts
            int mips2 = 1250000;
           
            // Generate the first pe List
            List<Pe> pelist2 = new ArrayList<>();
            
            for(int pecount = 0; pecount< 16 ; pecount++)
            {
              pelist2.add(new Pe(pecount, new PeProvisionerSimple(mips2))); 
              
            }
            
            // Generate the third pe list for type 3 hosts
            int mips3 = 1350000;
           
            // Generate the first pe List
            List<Pe> pelist3 = new ArrayList<>();
            
            for(int pecount = 0; pecount< 32 ; pecount++)
            {
              pelist3.add(new Pe(pecount, new PeProvisionerSimple(mips3))); 
              
            }
            
        // Create the first three Host of the DCs
        
        int host_id=0;
        
        List<Host> HostList = new ArrayList<>();
        // Create host of type 1 with host id 0
        
        int ram1 = 131584;
        int bw1 = 80000;
        long storage1 = 1000000;
        Host h = new Host
        (host_id, 
        new RamProvisionerSimple(ram1), 
        new BwProvisionerSimple(bw1),
        storage1, 
        pelist1, new VmSchedulerSpaceShared(pelist1));
        //pelist1, new Vm_Scheduler(pelist1));
        HostList.add(h);
        
        
        host_id++;
        // Create host of type 2 with host id 1
        int ram2 = 131584;
        int bw2= 80000;
        long storage2 = 80000000;
        h = new Host
        (host_id, 
        new RamProvisionerSimple(ram2), 
        new BwProvisionerSimple(bw2),
        storage2, 
        pelist2, new VmSchedulerSpaceShared(pelist2));
        //pelist2, new Vm_Scheduler(pelist2));        
        HostList.add(h);
        
        
        host_id++;
        // Create host of type 3 with host id 2
        int ram3 = 262144;
        int bw3= 80000;
        long storage3 = 80000000;
        h = new Host
        (host_id, 
        new RamProvisionerSimple(ram3), 
        new BwProvisionerSimple(bw3),
        storage3, 
        pelist3, new VmSchedulerSpaceShared(pelist3));
        //pelist3, new Vm_Scheduler(pelist3));        
        HostList.add(h);
            
        int host_count;
        // create another 29 hosts
        for (host_count = host_id+1; host_count<32;host_count++)
        {
            int rand= new Random().nextInt(3);
            //System.out.println("random number generated"+rand);
            switch(rand)
            {
                case 0: h = new Host
                        (host_count, 
                        new RamProvisionerSimple(ram1), 
                        new BwProvisionerSimple(bw1),
                        storage1, 
                        pelist1, new VmSchedulerTimeShared(pelist1));
                        //pelist1, new Vm_Scheduler(pelist1));      
                        HostList.add(h);
                        break;
                case 1: h = new Host
                        (host_count, 
                        new RamProvisionerSimple(ram2), 
                        new BwProvisionerSimple(bw2),
                        storage2, 
                        pelist2, new VmSchedulerTimeShared(pelist2));
                        //pelist2, new Vm_Scheduler(pelist2));
                        HostList.add(h);
                        break;
                case 2: h = new Host
                        (host_count, 
                        new RamProvisionerSimple(ram3), 
                        new BwProvisionerSimple(bw3),
                        storage3, 
                        pelist3, new VmSchedulerTimeShared(pelist3));
                        //pelist3, new Vm_Scheduler(pelist3));
                        HostList.add(h);  
                        break;
            }
        }
        
       // host_startindex = host_count;
        
        // Use it for printing the details. 
       /* for (Host h1: HostList)
        {
            System.out.println("The host id is:"+h1.getId()+"Pe:"+h1.getNumberOfPes()+"Ram:"+h1.getRam());
        }*/
       
       
       // Define the datacenter characteretics before creating an object of the DC class.
        String architecture = "X86"; 
        String os = "Linux"; 
        String vmm = "XEN"; 
        double timezone = 5.0; 
        double costPerSec = 3.0;
        double costPerMem = 1.0;
        double costPerStorage = 0.05;
        double costPerBw = 0.10;
        
        DatacenterCharacteristics datacentercharaecterestic = new DatacenterCharacteristics(
                architecture, os, vmm, HostList, timezone, costPerSec, 
                costPerMem, costPerStorage, costPerBw);
         
         LinkedList<Storage> SANStore = new LinkedList<>();
         
            switch(this.region)
            {
                case "Region 1": DC = new Datacenter
                        (
                        "Datacenter_0", 
                        datacentercharaecterestic, 
                        new 
                        VmAllocationPolicySimple(HostList), 
                        SANStore, 
                        0);
                    break;
                case "Region 2": DC = new Datacenter
                        (
                        "Datacenter_1", 
                        datacentercharaecterestic, 
                        new 
                        VmAllocationPolicySimple(HostList), 
                        SANStore, 
                        0);
                    break;
                    
                case "Region 3": DC = new Datacenter
                        (
                        "Datacenter_2", 
                        datacentercharaecterestic, 
                        new 
                        VmAllocationPolicySimple(HostList), 
                        SANStore, 
                        0);
                    break;
                    
                case "Region 4": DC = new Datacenter
                        (
                        "Datacenter_3", 
                        datacentercharaecterestic, 
                        new 
                        VmAllocationPolicySimple(HostList), 
                        SANStore, 
                        0);
                    break;    
            }
           
           return DC;
           
       }
                       
        public String getRegion()
        {
                return this.region;
        }
        
        
        public double getpriceinstancetype1()
        {
            return this.type1;
        }
        
        
        public double getpriceinstancetype2()
        {
            return this.type2;
        }
        
        
        public double getpriceinstancetype3()
        {
            return this.type3;
        }
        
        
        public double getpriceinstancetype4()
        {
            return this.type4;
        }
        
        
        public Datacenter getdatacenter()
        {
            return this.DC;
        }
 
}
