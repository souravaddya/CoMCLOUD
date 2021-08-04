/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;
import org.cloudbus.cloudsim.Log;

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
    
    Vertex end; 

    public Antcolonyevaluation(int ants, int generations, double evaporation, int alpha, 
            int beta, Service_Provider S, Multi_Tier M ) 
    {
        this.numOfAnts = ants;
        this.generations = generations;
        this.M = M;
        this.S = S;
        
        G = new Graph(evaporation, alpha, beta);
        G = createGraph(evaporation, alpha, beta);
        
    }
    
    public void displaygraph()
    {
        Log.printLine("Total Vertices:"+G.getTotalVertices());
        
        for (int i=0; i< G.list.size();i++) 
            {
                Log.printLine("Edge list for vertex: "+G.list.get(i).getname()
                        +" VM ID:"+G.list.get(i).getvmId()+" DC ID:"+G.list.get(i).getdcId());
                
                Log.printLine("Total number of Edges:"+G.list.get(i).Edgelist.size());
                
                for(Edge e: G.list.get(i).Edgelist)
                {
                    Log.printLine("Name:"+e.getname()+" startvertex:"+e.getstratvertex().getname()
                            +"endvertex:"+e.getendvertex().getname());
                }
                
            }
        
    }
    
    public final Graph createGraph(double evaporationRate, int alpha, int beta)
    {
        //Graph Graph = null;
        
        Vertex v;
        String name = "start";
       
        // Add the start vertex. with vmId=-1; and dcId =-1;
        v = new Vertex(name, -1, -1);
        // Add the start vertex
        G.addVertex(v);
        
        Log.printLine("Vertex Details:"+name+ " VM ID: "+v.getvmId()+
                        " DC ID:"+v.getdcId());
        
        name = "intermediate";
        
        // One Vm depicts a single level.
        for (int i=0; i<M.getVMList().size(); i++)
        {
            // Each DC of the given SP depicts the number of vertices at each level
            for(int j=0; j<S.get_dc_list().size(); j++)
            {
                v = new Vertex(name, M.getVMList().get(i).getId(), S.get_dc_list().get(j).DC.getId());
                
                Log.printLine("Vertex Details:"+v.getname()+ " VM ID: "+v.getvmId()+
                       " DC ID:"+v.getdcId());
                
                G.addVertex(v);
                
            }
        }
        
        name = "end";
        
        end = new Vertex(name, -99, -99);
        
        G.addVertex(end);
        
        Log.printLine("Vertex Details:"+end.getname()+ " VM ID: "+end.getvmId()+
                       " DC ID:"+end.getdcId());
        
        // Add the edges
        
       /* for(int i=0; i<G.list.size();i++)
        {
            Log.printLine("vertex name: "+G.list.get(i).getname()
                    +" VM ID:"+G.list.get(i).vmId+" DC ID:"+G.list.get(i).getdcId());
        }*/
        
       for(int i=0; i<G.list.size();i++)
       {  
           switch(G.list.get(i).name)    
           {
                    case "start":
                        
                        for(int j=0; j<G.list.size();j++)
                        {
                            if (G.list.get(j).getvmId() == 0 &&
                                    G.list.get(j).getname() == "intermediate")
                            {
                                 G.addEdge(G.list.get(i), G.list.get(j));
                            }
                        }
                       
                    break;
                    
                    case "intermediate":
                        
                        //for(int k = 0; k<G.list.size();k++)
                        //{ 
                      
                        int x = G.list.get(i).getvmId();
                        
                        for(int k = 0; k<G.list.size();k++)
                        {
                            if(G.list.get(k).getvmId()==x+1)
                            {
                                G.addEdge(G.list.get(i), G.list.get(k));
                            }
                           /* else
                            {
                                // Penultimate level
                                G.addEdge(G.list.get(k), end);
                            }*/
                            else if(G.list.get(k).getvmId()== M.getVMList().size()-1)
                            {
                                G.addEdge(G.list.get(k), end);
                            }
                        }
                           
                        // }   
                           /* int x = G.list.get(k).getvmId();
                             
                            if(x == M.getVMList().size()-1 &&
                                    G.list.get(k).getname() == "intermediate")
                            {
                                G.addEdge(G.list.get(k), end);
                            }*/
                            
                           /* else
                            {
                                for(int p = 0; p<G.list.size();p++)
                                {
                                    if(G.list.get(p).getvmId()== x+1)
                                    {
                                        G.addEdge(G.list.get(k), G.list.get(p));
                                    }
                                }
                                
                            }*/
                               
                               
                        break;
                        
                   
           }
           
       }
       
  
       
       
        /*for (Vertex v1: G) 
            {
                switch(v1.getname())
                {
                    case "start": 
                        for(Vertex v2: G)
                        {
                            if (v2.getvmId() == 0 && v2.getname() == "intermediate")
                            {
                                //Add an edge 
                                  G.addEdge(v2, v1);
                            }
                        }
                        
                        break;
                        
                    case "intermediate":
                        for(Vertex v2: G)
                        {
                            int x = v2.getvmId();
                            
                            if(x == M.getVMList().size()-1)
                            {
                                // Last level before the end vertex
                                G.addEdge(v2, end);
                            }
                            
                            else
                            {
                                // Not the last level
                                for(Vertex v3: G)
                                {
                                    if(v3.getvmId()== x+1)
                                    {
                                        G.addEdge(v2, v3);
                                    }

                                }
                                
                            }
                            
                        }
                        
                }
        
            }*/
   
        
   
        
        return G;
    }

    void run() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
