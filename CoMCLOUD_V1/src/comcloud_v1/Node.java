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
public class Node 
{
    // Stores the name of the Node, i.e., start node, end node or intermeditate node
    String name;
    
    // Stores the vm id correspodning to the node.
    //int vmId; 
    
    // Stores the Dc id corresponding to the node.
    //int dcId;
    
    // Vertex calls this.
   /* public Node (String name, int x, int y) 
    {
        this.name = name;
        //this.vmId = x;
        //this.dcId = y;
    }*/
    
    public Node(String name)
    {
        this.name= name;
    }
    
    public String getname()      
    {
        return this.name;
    }
    
    /*public int getvmId()
    {
        return this.vmId;
    }
    
    public int getdcId()
    {
        return this.dcId;
    }*/
    
   /* @Override
    public int hashCode ()
    {
        int result = vmId;
        result = 31 * result + dcId;
        return result;
    }*/

   /* @Override
    public boolean equals(Object obj) 
    {
        if (this == obj) 
        {
            return true;
        }
        if (obj == null) 
        {
            return false;
        }
        if (getClass() != obj.getClass()) 
        {
            return false;
        }
        final Node other = (Node) obj;
        if (this.vmId != other.vmId) 
        {
            return false;
        }
        if (this.dcId != other.dcId) 
        {
            return false;
        }
        return true;
    }*/
}
