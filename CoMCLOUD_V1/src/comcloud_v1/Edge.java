/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;

/**
 *
 * @author sunitapattanayak
 */
public class Edge extends Node  
{
    private double pheromone;
    
    public Vertex startvertex;
    
    public Vertex endvertex;
    
    public Edge(String name) 
    {
        super(name);
        
        pheromone = 0.01;
    }
    
    public void setPheromone (double pheromone) 
    {
        this.pheromone = pheromone;
    }
    
    
    public void setstartvertex(Vertex n)
    {
        this.startvertex = n;
    }
    
    
    public void setendvertex(Vertex n)
    {
        this.endvertex = n;
    }
    
    
    public Node getstratvertex()
    {
        return this.startvertex;
    }
    
    public int startvertexvmid()
    {
        return this.startvertex.getvmId();
    }
    
    public int startvertexdcId()
    {
        return this.startvertex.getdcId();
    }
    
    public Node getendvertex()
    {
        return this.endvertex;
    }

    public double getPheromone () 
    {
        return pheromone;
    }
    
}
