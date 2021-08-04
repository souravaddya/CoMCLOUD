/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;

import java.util.List;
import org.cloudbus.cloudsim.Log;

/**
 *
 * @author sunitapattanayak
 */
public class Antcolonyoptimization 
{
    List<Service_Provider> SP_List;
    
    List<Multi_Tier> MT;
    
    int number_of_apps;
    
            int ants;             // Number of ants to run per generation.
            int gen;           // Number of generations.
            double evap;         // Evaporation rate of pheromones.
            int alpha;         // Impact of pheromones on decision making.
            int beta;
    
    public Antcolonyoptimization(List<Service_Provider> S, List<Multi_Tier> M, int m)
    {
            Log.printLine("------------------ANT COLONY OPTIMIZATION------------------");
            //System.out.println("Use the parameter '-p' for custom settings.");
            //System.out.println("Otherwise the default values will be: ");
            System.out.println("Ants per epoch: 15");
            System.out.println("Epochs: 100");
            System.out.println("Evaporation Rate: 0.1");
            System.out.println("Alpha (pheromone impact): 1");
            System.out.println("Beta (distance impact): 5");

            this.ants    = 15;          // Number of ants to run per generation.
            this.gen     = 100;         // Number of generations.
            this.evap = 0.1;         // Evaporation rate of pheromones.
            this.alpha   = 1;           // Impact of pheromones on decision making.
            this.beta    = 5;           // Impact of distance on decision making.
            
            
            this.SP_List = S;
            this.MT = M;
            this.number_of_apps = m;
            
    }
    
    public void run()
    {
        for (int i = 0;i<number_of_apps;i++)
        {
            for(int j=0; j<SP_List.size();j++)
            {
                Antcolonyevaluation acoeval = 
                new Antcolonyevaluation (ants, gen, evap, alpha, beta, SP_List.get(j), MT.get(i));
            }
                
        }
                  
        System.out.println("-------------------------COMPLETE--------------------------");
    }
    
}
