/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.cloudbus.cloudsim.Cloudlet;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.UtilizationModel;
import org.cloudbus.cloudsim.UtilizationModelFull;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;
/**
 *
 * @author sunitapattanayak
 */
public class CoMCLOUD_V1 {

    /**
     * @param args the command line arguments
     */
    
    private static List<Service_Provider> SP_List = new ArrayList<>();
    
    
    private static List<Multi_Tier> MT_List = new ArrayList <>();
    
    
    private static final List<Vm> vm_list = new ArrayList<>();
    
    
    public static void main(String[] args) throws Exception 
    {
        // TODO code application logic here
        
        System.out.println("Starting CoMCLOUD");
        
        System.out.println("Welcome to CloudSim Simulation");
        
        // Follow the steps:
        // Step 1: Initialize cloudsim Simulator
        
        int numuser = 1;
        Calendar cal =  Calendar.getInstance();
        boolean trace = false;
        CloudSim.init(numuser, cal, trace);
        
        
        // Step 2: Create the service providers (SPs).
        
        // Create the first SP.
        String name = "Amazon";
        int number = 4;
        Service_Provider SP = new Service_Provider(name, number);
        //SP.createDCsforSP();
        //SP.
        SP_List.add(SP);
        
        // Create the second SP.
        name = "Azure";
        SP = new Service_Provider(name, number);
        //SP.createDCsforSP();
        SP_List.add(SP);
        
        // Create the third SP.
        name = "Google";
        SP = new Service_Provider(name, number);
        //SP.createDCsforSP();
        SP_List.add(SP); 
        
       for (Service_Provider SP1: SP_List)
        {
            Log.printLine("Service Provider Name: "+ " "+ SP1.get_name());
            
            for(DatacenterFederated DC: SP1.get_dc_list())
            {
                Log.printLine("Datacenter Region: "+DC.getRegion()+ " Name: " +DC.DC.getName());
                
                for(Host h : DC.DC.getHostList())
                {
                    
                    Log.printLine("Host ID: "+h.getId() +" #PEs: "+h.getNumberOfPes()+" RAM: "+h.getRam());
                    
                    /*for(Pe P: h.getPeList())
                    {
                        Log.printLine("PE number:"+P.getId()+"  "+"MIPS:"+P.getMips());
                    }*/
                }
            }
        }
       
      
       
      DatacenterBroker broker = new DatacenterBroker("Broker");
          
      int brokerId = broker.getId(); 
      
      
      // Define the number of multi-tier applications as inputs. 
     int no_of_apps = 1;  

     int num_of_Vms = 3;
     
     int startvmId=0;
     
     
     
     List <Cloudlet> Clist = new ArrayList<>();
     
     Cloudlet c = null;
      
          int cloudletId = 0; /* cloudlet Id */
          long cloudletLength = 4000; /* Length of the cloudlet */
          int pesnumber = 1; /* Number of processing elements reqd by a cloudlet */
          long cloudletfilesize = 500; /* Input file size of the cloudlet */
          long cloudletoutputfilesize = 500; /* Output file size of the cloudlet */
      
           UtilizationModel U = new UtilizationModelFull();
      
            for (cloudletId=0; cloudletId<40; cloudletId++)
            {
                c = new 
                Cloudlet(cloudletId, 
                     cloudletLength, 
                     pesnumber, 
                     cloudletfilesize,
                     cloudletoutputfilesize,
                     U, 
                     U,
                     U);
                //c.setVmId();
                Clist.add(c);  
                c.setUserId(brokerId);
           }
             
     
           broker.submitCloudletList(Clist);
           
           
         /*  for(Cloudlet c1: Clist)
           {
               broker.bindCloudletToVm(c1.getCloudletId(), startvmId++);
           }*/
         
         
            
     
        Log.printLine("Number of multi-tier applications Processed: " + no_of_apps);
     
     
        for(int app_id = 0; app_id <no_of_apps; app_id++)
        {
          int app_type = new Random().nextInt(3);
          
          
          Multi_Tier M = new Multi_Tier(app_id, num_of_Vms, brokerId, app_type, startvmId);
          
          M.createmultiTier();
          
          startvmId = M.getstartId();
          
          //broker.submitVmList(M.getVMList());
          
          for(Vm v:M.getVMList())
          {
              vm_list.add(v);
          }
          
          MT_List.add(M);  
                 
     }
     
        
    broker.submitVmList(vm_list);
     
     for(Multi_Tier M: MT_List)
      {
          Log.printLine("Multi Tier Aplication "+M.app_id);
          
          
          for (VM_Extended v: M.VMList)
          {
              Log.printLine("VM ID: "+ v.vmid +" PEs "+v.v.getNumberOfPes()+" RAM: "+v.v.getRam());
          }
          
      }
     
     Antcolonyoptimization a = new Antcolonyoptimization(SP_List, MT_List, no_of_apps);
     
     
        // create the cloudlet list
      
       

      
        // Start the CloudSim simulator 
        CloudSim.startSimulation();
   
    
        //Log.printLine("Datacenter Id:"+dcb.);
    
    
        CloudSim.stopSimulation();
      
      
    }
    
    
    /*public List<Vm> getvmlist()
    {
        return vm_List;
    }*/
    
}
