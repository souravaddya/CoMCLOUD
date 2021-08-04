/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;
import comcloud_v1.Graph;

/**
 *
 * @author sunitapattanayak
 */
public class Antcolonyevaluation 
{
    //private Graph graph;
    private int numOfAnts; 
    
    private int generations;
    
    private Service_Provider S;
    
    private Multi_Tier M;
    
    Graph G;

    public Antcolonyevaluation(int ants, int generations, double evaporation, int alpha, 
            int beta,Service_Provider S, Multi_Tier M ) 
    {
        this.numOfAnts = ants;
        this.generations = generations;
        this.M = M;
        this.S = S;
        
        G = new Graph(evaporation, alpha, beta);
        G = createGraph(evaporation, alpha, beta);
        
    }
    
    
    public Graph createGraph(double evaporationRate, int alpha, int beta)
    {
        Graph Graph = null;
        
        Vertex v;
        String name = "start";
       
        // Add the start vertex. with vmId=-1; and dcId =-1;
        v = new Vertex(name, -1, -1);
        // Add the start vertex
        G.addVertex(v);
        
        name = "intermediate";
        
        // One Vm depicts a single level.
        for (int i=0; i<M.getVMList().size(); i++)
        {
            // Each DC of the given SP depicts the number of vertices at each level
            for(int j=0; j<S.get_dc_list().size(); j++)
            {
                v = new Vertex(name, M.getVMList().get(i).getId(),S.get_dc_list().get(j).DC.getId());
            }
        }
        
        // identify and create the vertices.
        
        
            
        
        return Graph;
    }

    void run() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
