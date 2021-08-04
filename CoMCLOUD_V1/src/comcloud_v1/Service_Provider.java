/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 *
 * @author sunitapattanayak
 */
public final class Service_Provider 
{
    
    // Name of the service provider.
    public String name;
    
    // Stores the number of datacenters corresponding to a SP.
    public int no_of_datacenters;
    
    // List of DCs for the given service provider.
    public final List<DatacenterFederated> DC_List;
    
    // The average delay in sec for different DCs under this SP.
    public double [][] service_delay;
    
    
    public static final double [][] AWSPrice = 
    { 
        {0.023,  0.0248, 0.0292, 0.0304}, 
        {0.0464, 0.0496, 0.0584, 0.0608},
        {0.0928, 0.0992, 0.1168, 0.1216}, 
        {0.3712, 0.3968, 0.4672, 0.4864}
    };
    // Price of Azure
    public static final double [][] AzurePrice = 
    {
        {0.012, 0.014,  0.015, 0.015}, 
        {0.048, 0.054,  0.059, 0.059}, 
        {0.023, 0.027,  0.029, 0.029}, 
        {0.094, 0.11,   0.12,   0.12}
    };
    // Pric of google 
    public static final double [][] GooglePrice = 
    {
        {0.0475, 0.057, 0.0674,  0.061},
        {0.0950, 0.1141, 0.1348, 0.122},
        {0.19,   0.2282, 0.2697, 0.244},
        {0.38,   0.4564, 0.5393, 0.488}
    }; 
    
    
    
    
    public Service_Provider(String name, int dc_count) throws Exception
    {
            set_name(name);
            set_no_of_datacenters(dc_count);
            DC_List = new ArrayList<>();
            initilalize_delay();
            createDCsforSP();
            
    }
    
    
    public void createDCsforSP() throws Exception
    {
        DatacenterFederated DCF = null;
        
        for(int i= 0; i<get_no_of_datacenters(); i++)
        {
             
            switch(this.name)
            {
                case "Amazon":
                    
                    switch(i)
                    {
                        // Case values represent the region.
                         case 0: DCF = new DatacenterFederated("Region 1", AWSPrice[0][0], 
                                 AWSPrice[1][0], AWSPrice[2][0],AWSPrice[3][0]);
                                 DC_List.add(DCF);
                            break;
                         case 1: DCF = new DatacenterFederated("Region 2", AWSPrice[0][1], 
                                 AWSPrice[1][1], AWSPrice[2][1],AWSPrice[3][1]);
                                 DC_List.add(DCF);
                            break;
                         case 2: DCF = new DatacenterFederated("Region 3", AWSPrice[0][2], 
                                 AWSPrice[1][2], AWSPrice[2][2],AWSPrice[3][2]);
                                 DC_List.add(DCF);
                            break;     
                         case 3: DCF = new DatacenterFederated("Region 4", AWSPrice[0][3], 
                                 AWSPrice[1][3], AWSPrice[2][3],AWSPrice[3][3]);
                                 DC_List.add(DCF);
                            break;   
                          
                    }
                    
                break;   
                
             case "Azure":
                 
                 switch(i)
                    {
                        // Case values represent the region.
                         case 0: DCF = new DatacenterFederated("Region 1", AzurePrice[0][0], 
                                 AzurePrice[1][0], AzurePrice[2][0],AzurePrice[3][0]);
                                 DC_List.add(DCF);
                            break;
                         case 1: DCF = new DatacenterFederated("Region 2", AzurePrice[0][1], 
                                 AzurePrice[1][1], AzurePrice[2][1],AzurePrice[3][1]);
                                 DC_List.add(DCF);
                            break;
                         case 2: DCF = new DatacenterFederated("Region 3", AzurePrice[0][2], 
                                 AzurePrice[1][2], AzurePrice[2][2], AzurePrice[3][2]);
                                 DC_List.add(DCF);
                            break;     
                         case 3: DCF = new DatacenterFederated("Region 4", AzurePrice[0][3], 
                                 AzurePrice[1][3], AzurePrice[2][3], AzurePrice[3][3]);
                                 DC_List.add(DCF);
                            break;   
                          
                    }
                 break;
                 
             case "Google":
                 
                 switch(i)
                    {
                        // Case values represent the region.
                         case 0: DCF = new DatacenterFederated("Region 1", GooglePrice[0][0], 
                                 GooglePrice[1][0], GooglePrice[2][0], GooglePrice[3][0]);
                                 DC_List.add(DCF);
                            break;
                         case 1: DCF = new DatacenterFederated("Region 2", GooglePrice[0][1], 
                                 GooglePrice[1][1], GooglePrice[2][1], GooglePrice[3][1]);
                                 DC_List.add(DCF);
                            break;
                         case 2: DCF = new DatacenterFederated("Region 3", GooglePrice[0][2], 
                                 GooglePrice[1][2], GooglePrice[2][2], GooglePrice[3][2]);
                                 DC_List.add(DCF);
                            break;     
                         case 3: DCF = new DatacenterFederated("Region 4", GooglePrice[0][3], 
                                 GooglePrice[1][3], GooglePrice[2][3], GooglePrice[3][3]);
                                 DC_List.add(DCF);
                            break;   
                          
                    }
                 
                break;
            }
        }
    }
    
    protected void set_name(String name)       
    {
        this.name = name;
    }
    
    protected void set_no_of_datacenters(int dc_count)
    {
        this.no_of_datacenters = dc_count;
    }
    
    public String get_name()
    {
        return this.name;
    }
    
    public int get_no_of_datacenters()
    {
        return no_of_datacenters;
    }
    
    public List<DatacenterFederated> get_dc_list()
    {
        return DC_List;
    }
    
    
   /* public void displayDCList()
    {
        for (Iterator<? extends Datacenter> it = DC_List.iterator(); it.hasNext();) 
        {
            Datacenter DC = it.next();
            Log.printLine("Datacenter:"+DC.getName());
        }
    }*/
    
   /* public int getbrokerId()
    {
        return this.brokerId;
    }*/
    
    public void initilalize_delay()
    {
        //Log.printLine("Inter DC Latency of SP: "+ " "  +this.get_name());
        
        
        this.service_delay = new double[this.no_of_datacenters][this.no_of_datacenters];
        
        for(int i=0; i<this.no_of_datacenters;i++)
        {
            for(int j=0; j<this.no_of_datacenters; j++)
            {
                if(j<i)
                {
                    //System.out.println("Value of i:"+i +"Value of j:"+j);
                    double x = new Random().nextDouble() + new Random().nextInt(10);
                    this.service_delay[i][j] = x;
                    this.service_delay[j][i] = x;
                }
                if(i==j)
                {
                    //System.out.println("Value of i:"+i +"Value of j:"+j);
                    this.service_delay[i][j] = new Random().nextDouble()+0.1;
                    break;
                }
            }
     }
        
     /*  for(int i=0; i<this.no_of_datacenters;i++)
        {
            for(int j=0; j<this.no_of_datacenters; j++)
            {
                Log.print(" "+this.service_delay[i][j]);
            }
            Log.printLine();
        }
        
        Log.printLine();*/
    }
   
 //   public List<DatacenterFederated> CreateServiceprovider(double[][] Price) throws Exception 
   // {
           // Before creating a host create a PE list.
           
            /* We have three types of hosts depending on the number of pes and mips rating. 
            hence, we generate three different pe lists and assign them randomly to the hosts*/ 
        
            //Base number of MIPS for type 1 hosts.
           /* int mips1 =100000;
           
            // Generate the first pe List
            List<Pe> pelist1 = new ArrayList<>();
            
            for(int pecount = 0; pecount< 16 ; pecount++)
            {
              pelist1.add(new Pe(pecount, new PeProvisionerSimple(mips1))); 
              
            }
        
            // Generate the second pe list for type 2 hosts
            int mips2 = 125000;
           
            // Generate the first pe List
            List<Pe> pelist2 = new ArrayList<>();
            
            for(int pecount = 0; pecount< 16 ; pecount++)
            {
              pelist2.add(new Pe(pecount, new PeProvisionerSimple(mips2))); 
              
            }
            
            // Generate the third pe list for type 3 hosts
            int mips3 = 135000;
           
            // Generate the first pe List
            List<Pe> pelist3 = new ArrayList<>();
            
            for(int pecount = 0; pecount< 32 ; pecount++)
            {
              pelist3.add(new Pe(pecount, new PeProvisionerSimple(mips3))); 
              
            }
            */
        // Create the Hosts of the DCs
        
        /*int hostid=0;
        List<Host> HostList = new ArrayList<>();
        // Create host of type 1 with host id 0
        
        int ram1 = 32768;
        int bw1 = 80000;
        long storage1 = 1000000;
        Host h = new Host
        (hostid, 
        new RamProvisionerSimple(ram1), 
        new BwProvisionerSimple(bw1),
        storage1, 
        //pelist1, new VmSchedulerSpaceShared(pelist1));
        pelist1, new Vm_Scheduler(pelist1));
        HostList.add(h);
        
        
        hostid++;
        // Create host of type 2 with host id 1
        int ram2 = 32768;
        int bw2= 80000;
        long storage2 = 80000000;
        h = new Host
        (hostid, 
        new RamProvisionerSimple(ram2), 
        new BwProvisionerSimple(bw2),
        storage2, 
        //pelist2, new VmSchedulerSpaceShared(pelist2));
        pelist2, new Vm_Scheduler(pelist2));        
        HostList.add(h);
        
        
        hostid++;
        // Create host of type 3 with host id 2
        int ram3 = 65536;
        int bw3= 80000;
        long storage3 = 80000000;
        h = new Host
        (hostid, 
        new RamProvisionerSimple(ram3), 
        new BwProvisionerSimple(bw3),
        storage3, 
        //pelist3, new VmSchedulerSpaceShared(pelist3));
        pelist3, new Vm_Scheduler(pelist3));        
        HostList.add(h);
        */
        
      /*  for (int host_count = hostid+1; host_count<16;host_count++)
        {
            int rand= new Random().nextInt(3);
            //System.out.println("random number generated"+rand);
            switch(rand)
            {
                case 0: h =new Host
                        (host_count, 
                        new RamProvisionerSimple(ram1), 
                        new BwProvisionerSimple(bw1),
                        storage1, 
                        //pelist1, new VmSchedulerSpaceShared(pelist1));
                          pelist1, new Vm_Scheduler(pelist1));      
                        HostList.add(h);
                        break;
                case 1: h =new Host
                        (host_count, 
                        new RamProvisionerSimple(ram2), 
                        new BwProvisionerSimple(bw2),
                        storage2, 
                        //pelist2, new VmSchedulerSpaceShared(pelist2));
                        pelist2, new Vm_Scheduler(pelist2));
                        HostList.add(h);
                        break;
                case 2: h =new Host
                        (host_count, 
                        new RamProvisionerSimple(ram3), 
                        new BwProvisionerSimple(bw3),
                        storage3, 
                        //pelist3, new VmSchedulerSpaceShared(pelist3));
                        pelist3, new Vm_Scheduler(pelist3));
                        HostList.add(h);  
                        break;
            }
        }
       */
      // Use it for printing the details. 
       /* for (Host h1: HostList)
        {
            System.out.println("The host id is:"+h1.getId()+"Pe:"+h1.getNumberOfPes()+"Ram:"+h1.getRam());
        }*/
        
        // define the datacenter characteretics before creating an object of the DC class.
        /*String architecture = "X86"; 
        String os = "Linux"; 
        String vmm = "XEN"; 
        double timezone = 5.0; 
        double costPerSec = 3.0;
        double costPerMem = 1.0;
        double costPerStorage = 0.05;
        double costPerBw = 0.10;
        
        DatacenterCharacteristics dcs = new DatacenterCharacteristics(
                architecture, os, vmm, HostList, timezone, costPerSec, 
                costPerMem, costPerStorage, costPerBw);
        */
        
        // Create the DCs now
        
        /*String region ="Region 1";
        DatacenterFederated DCF = null;
        LinkedList<Storage> SANStore = new LinkedList<>();
        DCF = new DatacenterFederated(
                region, Price[0][0], Price[1][0], Price[2][0], Price[3][0],
                "Datacenter_0", dcs, new VmAllocationPolicySimple_Extended(HostList), SANStore, 0);*/
      
       /* DC_List.add(DCF);
      
         region ="Region 2";
        //DatacenterFederated DCF = null;
        //LinkedList<Storage> SANStore = new LinkedList<>();
        DCF = new DatacenterFederated(
                region, Price[0][1], Price[1][1], Price[2][1], Price[3][1],
                "Datacenter_1", dcs, new VmAllocationPolicySimple_Extended(HostList), SANStore, 0);
        //dcb = new DatacenterBroker("DatacenterBroker_1");
        DC_List.add(DCF);
        
        region ="Region 3";
        //DatacenterFederated DCF = null;
        //LinkedList<Storage> SANStore = new LinkedList<>();
        DCF = new DatacenterFederated(
                region, Price[0][2], Price[1][2], Price[2][2], Price[3][2],
                "Datacenter_2", dcs, new VmAllocationPolicySimple_Extended(HostList), SANStore, 0);
        //dcb = new DatacenterBroker("DatacenterBroker_2");
        DC_List.add(DCF);
        
        region ="Region 4";
        //DatacenterFederated DCF = null;
        //LinkedList<Storage> SANStore = new LinkedList<>();
        DCF = new DatacenterFederated(
                region, Price[0][3], Price[1][3], Price[2][3], Price[3][3],
                "Datacenter_3", dcs, new VmAllocationPolicySimple_Extended(HostList), SANStore, 0);
        //dcb = new DatacenterBroker("DatacenterBroker_3");
        DC_List.add(DCF);
             
        return DC_List;
        
    }*/
    
}