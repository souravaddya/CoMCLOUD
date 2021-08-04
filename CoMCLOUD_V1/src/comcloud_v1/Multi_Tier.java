/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Vm;
/**
 *
 * @author sunitapattanayak
 */
public class Multi_Tier 
{
    
   // Denotes the application number
    public int app_id;
    
    // number of Vms in this app
    
    //public int number;
    
    // List of VMs corresponding to a multi-tier application.
    
    public List<VM_Extended> VMList;
       
    // Save the brokerId.
    public int brokerId;
    
    // APP type (Basically there are three type of aplications)(0, 1, 2)
    
    int app_type;
    
    
    int startvmId;

    public Multi_Tier(int app_id, int number, int brokerId, int app_type, int start)       
    {
        this.app_id = app_id;
        
        this.brokerId = brokerId;
        
        //this.number = number;
        
        VMList = new ArrayList<>();
        
        this.app_type = app_type;
        
        this.startvmId = start;
    }
    
    public List <Vm> getVMList()
    {
        List <Vm> VList = new ArrayList<>();
        
        for (VM_Extended v : VMList)
        {
            VList.add(v.getvm());
        }
        
        return VList;
    }
    
    public Multi_Tier getapp()
    {
        return this;
    }
    
   /* public int getnumofVms()
    {
        return this.number;
    }*/
         
    public int getstartId()
    {
        return this.startvmId;
    }
    public int getappId()
    {
        return this.app_id; 
    }
    
    public List<VM_Extended> getvmList()
    {
        return this.VMList;
    }
    
    public int getbrokerId()
    {
        return this.brokerId;
    }
    
    public int getapp_type()
    {
        return this.app_type;
    }
    
   public void createmultiTier()
    {
        int rand = new Random().nextInt(3);
        
        switch(this.app_type)
        {
            
                case 0: // Application of type 1
                
                
                switch(rand)
                {
                    case 0: // Hosting at amazon
                            
                          // Log.printLine("Amazon");
                            VM_Extended vm = new VM_Extended(1, "Amazon", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(3, "Amazon", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(4, "Amazon", startvmId, brokerId);
                            startvmId++;
                            VMList.add(vm);
                            
                     break;
                     
                    case 1: // Hosting at azure 
                      //  Log.printLine("Azure");
                            vm = new VM_Extended(1, "Azure", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(3, "Azure", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(4, "Azure", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                      break;      
                            
                    case 2: // Hosting at google 
                     // Log.printLine("Google");
                            vm = new VM_Extended(1, "Google", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(3, "Google", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(4, "Google", startvmId, brokerId);
                            VMList.add(vm);  
                            startvmId++;
                            
                      break;      
                }
                
            break;
                
            case 1: // Application type 2
                switch(rand)
                {
                    case 0: // Hosting at amazon
                            
                            //Log.printLine("Amazon");
                            VM_Extended vm = new VM_Extended(2, "Amazon", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(3, "Amazon", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(4, "Amazon", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            
                     break;
                     
                    case 1: // Hosting at azure 
                        //Log.printLine("Amazon");
                            vm = new VM_Extended(2, "Azure", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(3, "Azure", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(4, "Azure", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                      break;      
                            
                    case 2: // Hosting at google 
                     // Log.printLine("Google");
                            vm = new VM_Extended(2, "Google", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(3, "Google", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(4, "Google", startvmId, brokerId);
                            VMList.add(vm);  
                            startvmId++;
                            
                      break;      
                }
                
                break;
                
            case 2: // Application type 3 
                
                switch(rand)
                {
                    case 0: // Hosting at amazon
                            
                           // Log.printLine("Amazon");
                            VM_Extended vm = new VM_Extended(1, "Amazon", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(2, "Amazon", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(4, "Amazon", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            
                     break;
                     
                    case 1: // Hosting at azure 
                       // Log.printLine("Amazon");
                            vm = new VM_Extended(1, "Azure", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(2, "Azure", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(4, "Azure", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                      break;      
                            
                    case 2: // Hosting at google 
                    //  Log.printLine("Google");
                            vm = new VM_Extended(1, "Google", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(2, "Google", startvmId, brokerId);
                            VMList.add(vm);
                            startvmId++;
                            vm = new VM_Extended(4, "Google", startvmId, brokerId);
                            VMList.add(vm);  
                            startvmId++;
                            
                      break;      
                }
             
            break;  
        }
                      
    }
}

