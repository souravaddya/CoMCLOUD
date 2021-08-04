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
public class Graph implements Iterable<Vertex>
{
    private HashMap<Integer, Vertex> hashMap;
    
    private ArrayList<Vertex> list;
    private int totalEdges;
    private int alpha, beta;
    private double evaporationRate;
    
    public Graph (double evaporationRate, int alpha, int beta) 
    {
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        clear();
    }
    
    public void addVertex (Vertex vertex) 
    {
        hashMap.put(vertex.hashCode(), vertex);
        list.add(vertex);
    }

    public Vertex getVertex (Node node) 
    {
        return hashMap.get(node.hashCode());
    }
    
    public void addEdge (Vertex vertex, Node node) 
    {
        vertex.addEdge(node);
        totalEdges++;
    }

    public Node[] getVertices () 
    {
        Node[] nodes = new Node[getTotalVertices()];
        int i = 0;
        for (Vertex v : this) 
        {
            nodes[i++] = v;
        }
        return nodes;
    }
    
    /*public void updatePheromone(Ant ant) 
    {

        double eval = ant.eval();

        double probability = (1 - evaporationRate);

        Node[] edges = ant.getTour();

        HashSet<Edge> hashSet = new HashSet<>();

        for (int i = 1; i < edges.length; i++) {
            Edge e1 = getVertex(edges[i-1]).getEdge(edges[i]);
            Edge e2 = getVertex(edges[i]).getEdge(edges[i-1]);

            // The pheromones.
            double p1 = e1.getPheromone();
            double p2 = e2.getPheromone();

            hashSet.add(e1);
            hashSet.add(e2);

            e1.setPheromone(probability*p1 + 1.0/eval);
            e2.setPheromone(probability*p2 + 1.0/eval);
        }

        // Evaporate the pheromones on all the rest of the edges.
        for (Vertex v : this) 
        {
            for (Edge e : v) {
                if (!hashSet.contains(e)) 
                {
                    double p = e.getPheromone();
                    e.setPheromone(probability*p);
                }
            }
        }

    }*/
  
    public void clear () 
    {
        hashMap = new HashMap<>();
        list = new ArrayList<>();
        totalEdges = 0;
    }
    
    public int getAlpha () 
    {
        return alpha;
    }

    public int getBeta () 
    {
        return beta;
    }
    
    public int getTotalVertices () 
    {
        return hashMap.size();
    }
    
    public int getTotalEdges () 
    {
        return totalEdges;
    }
    
    public boolean isEmpty () 
    {
        return hashMap.isEmpty();
    }
    
    @Override
    public Iterator<Vertex> iterator() 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
