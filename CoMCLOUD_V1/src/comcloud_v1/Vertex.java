/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comcloud_v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author sunitapattanayak
 */
public class Vertex extends Node implements Iterable<Edge>  
{

    private HashMap<Integer, Edge> hashMap = new HashMap<>();
    
    
    public ArrayList<Edge> Edgelist;
    
    public int vmId; 
    
    // Stores the DC id corresponding to the node.
    public int dcId;
    
    public Vertex(String name, int vmid, int dcid) 
    {
        super(name);
        
        this.vmId = vmid;
        this.dcId = dcid;
        
        hashMap = new HashMap<>();
        Edgelist = new ArrayList<>();
    }
   
    public void addEdge (Vertex v, Node n) 
    {
        if (n instanceof Vertex)
        {
            Edge e = new Edge(n.getname());
            hashMap.put(e.hashCode(), e);
            Edgelist.add(e);
            e.setstartvertex(v);
            e.setendvertex((Vertex) n);
        }
        else 
        {
            hashMap.put(n.hashCode(), (Edge)n);
            Edgelist.add((Edge)n);
        }
    }
    
    public Edge getEdge (Node n) 
    {
        return hashMap.get(n.hashCode());
    }
    
    public boolean contains (Node n) 
    {
        return hashMap.containsValue(n.hashCode());
    }
    
    public int getTotalEdges () 
    {
        return hashMap.size();
    }
    
    public int getvmId()
    {
        return this.vmId;
    }
    
    public int getdcId()
    {
        return this.dcId;
    }

    @Override
    public Iterator<Edge> iterator() 
    {
        return Edgelist.iterator();
        //throw new UnsupportedOperationException("Not supported yet."); 
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
