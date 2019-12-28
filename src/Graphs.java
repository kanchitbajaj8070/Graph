import java.lang.reflect.Array;
import java.util.*;

public class Graphs<T> {

    public LinkedList<Vertex> vertices;
    Graphs()
    {
        vertices= new LinkedList<>();
    }
    public class Vertex
    {
        T data;
        LinkedList<Vertex> neighbors;
        Vertex( T d)
        {
            data=d;
            neighbors= new LinkedList<>();
        }
    }

    public void addVertex( T data)
    {
        Vertex v= new Vertex(data);
        vertices.add(v);
    }

    public Vertex findVertex( T data)
    {
        for( Vertex v : vertices)
        {
            if( v.data.equals(data))
                return v;

        }
        return null;
    }
    public void addEdge( T start, T end,boolean isUndirected)
    {
        Vertex s= findVertex(start);
        Vertex e= findVertex(end);
        if( s!=null&&e!=null)
        {
            s.neighbors.add(e);
            if(isUndirected)
                e.neighbors.add(s);
        }

    }

    public void DFS(T data)//O(V+E) complexity
    {
    HashSet< Vertex> visited= new HashSet<>();
        System.out.println();
    DFS( findVertex(data),visited);
        System.out.println("\n");
    }

    private void DFS(Vertex vertex,HashSet<Vertex> visited) {

            if( vertex==null)
                return;
        System.out.print(vertex.data+"  ");
        visited.add( vertex);
        for( Vertex v: vertex.neighbors)
        {
            if(!visited.contains(v))
            DFS(v,visited);
        }
    }
    public void BFS( T data) {
//O(V+E) complexity
        Vertex start = findVertex(data);
        HashSet<Vertex> visited = new HashSet<>();
        visited.add(start);
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(start);
        System.out.println("******************");
        while (queue.size() > 0) {
            Vertex v= queue.remove();
            System.out.print(v.data+"   ");
            for( Vertex nbrs: v.neighbors)
                {
                    if(!visited.contains(nbrs)) {
                        queue.add(nbrs);
                        visited.add(nbrs);
                    }
                }
        }

        System.out.println("\n *****************");
    }
    public void singleSourceShortestPath( T data)
    {
        HashMap< Integer, ArrayList<T>> sssp= new HashMap<>();
        Vertex source=findVertex(data);
        HashSet<Vertex > visited= new HashSet<>();
        visited.add( source);
        Queue< Vertex > queue= new LinkedList<>();
        queue.add(source);
        queue.add(null);
        int depth=0;
        while(queue.size()>1)
        {
            Vertex v= queue.remove();
            if(v!=null)
            {
                if( sssp.containsKey( depth))
                {
                    sssp.get(depth).add(v.data);
                }
                else
                {
                    sssp.put(depth, new ArrayList<>());
                    sssp.get(depth).add(v.data);

                }
                //System.out.println(v.data+" at depth "+ depth);
                for( Vertex u: v.neighbors)
                {
                    if(!visited.contains(u))
                    {
                        visited.add(u);
                        queue.add(u);
                    }
                }
            }
            else
            {
                queue.add(null);
                depth++;
            }
        }
        for(Map.Entry<Integer, ArrayList<T>> depthlist : sssp.entrySet())
        {
            System.out.print(" AT DEPTH "+ depthlist.getKey()+" -> ");
            for( T u: depthlist.getValue())
            {
                System.out.print(u+" , ");
            }
            System.out.println();
        }        System.out.println();
    }
    public int connectedComponents()
    {       int k=0;
        HashSet<Vertex> visited= new HashSet<>();
        for( Vertex v : vertices)
        {   if(!visited.contains(v)) {
            DFS(v, visited);
            k++;
        }
        }
        return k;
    }
    public void topologicalSortUsingDFS( )
    {
        Stack<T> stack= new Stack<>();
        HashSet<Vertex> visited= new HashSet<>();
        topologicalSortUsingDFS(vertices.get(0),stack,visited);
        System.out.println();
        while(!stack.isEmpty())
        System.out.print(stack.pop()+"  ");
        System.out.println();
    }

    private void topologicalSortUsingDFS(Vertex vertex, Stack<T> stack, HashSet<Vertex> visited)
    {
        visited.add( vertex);
        for( Vertex v : vertex.neighbors)
        {
            if(!visited.contains(v))
                topologicalSortUsingDFS(v,stack,visited);
        }
        stack.add(vertex.data);
    }
    public void topologicalSortUsingBfs( T start)
    {
        // KAHN ALGORITHM is bfs traansversal of topological sort
        Queue<Vertex> queue= new LinkedList<>();
        HashSet<Vertex> visited= new HashSet<>();
        int indegree[]= new int[vertices.size()];
        for (int i = 0; i < vertices.size(); i++)
        {
            for (int j=0;j< vertices.size();j++)
            {
                if(j==i)
                    continue;
                if( vertices.get(j).neighbors.contains(vertices.get(i)))
                    indegree[i]+=1;
            }
        }

        addVertexWithZeroIndegree(queue,indegree,visited);

while( !queue.isEmpty())
{
    Vertex v=queue.remove();
    System.out.print(v.data+" -> ");
    for (int i = 0; i < vertices.size(); i++) {
        if (v.neighbors.contains(vertices.get(i)))
            indegree[i] -= 1;
    }
    addVertexWithZeroIndegree(queue,indegree,visited);
    }
}


    private void addVertexWithZeroIndegree(Queue<Vertex> queue,int[] indegree,HashSet<Vertex> visited) {
        for( int i=0;i<indegree.length;i++)
        {
            if(indegree[i]==0)
            {
                if(!visited.contains(vertices.get(i)))
                {
                    queue.add(vertices.get(i));
                    visited.add(vertices.get(i));
                }
            }
        }
    }
    // tricky for undirected graph . we have to check that the path is not found
    // from more than one parent. if path found through only 1 parent then it is acyclic
    public boolean isUndirectedGraphCyclic( T data) {
        Vertex source = findVertex(data);
        Queue<Vertex> queue = new LinkedList<>();
        HashMap<Vertex, Vertex> parent = new HashMap<>();
        HashSet<Vertex> visited = new HashSet<>();
        queue.add(source);
        visited.add(source);
        parent.put(source, null);
        while (queue.size() > 0) {
            Vertex v = queue.remove();
            for (Vertex u : v.neighbors) {
                if (parent.get(v) == u)
                    continue;// skipping iterations where parent is the neighbour
                // trick of undirected graph cycle finding. do not consider the parent
                // in transvering the graph
                if (visited.contains(u) )//&& parent.get(v) != u) //if we skip cond. 1 use it
                    return true;
                    // here we have to skip iterations where parents( v)=u;
                else /*if (!visited.contains(u)) */
                {
                    visited.add(u);
                    queue.add(u);
                    parent.put(u, v);
                }
            }
        }
        return false;
    }
    //visited is not enough for checking a cycle. we need to have an instack set which
    // contains the elements currectly in call stack .
    //why visited not enough
    /*  1-->3
    *   |   |
    *   4-->5// THIS WILL RETURN TRUE BUT THERE IS NO CYCLE IF WE USE ONLY VISITED
    *   WE HAVE TO CHECK FOR BACK EDGES WHERE BACK EDGE IS A EDGE FROM A NEW DISCOVERED NODE
    *   TO ITS ANCESTOR OR AN ELEMENT CURRENTLY IN CALL STACK;
    * */
    public boolean isDirectedGraphCyclic()
    {       boolean ans=false;
        HashSet<Vertex> visited= new HashSet<>();
        HashSet<Vertex> inStack= new HashSet<>();
        for( Vertex v: vertices)
        {
            if( !visited.contains(v))
            {
                ans=isDirectedGraphCyclic(v,visited,inStack);
                if(ans==true)
                    break;
            }
        }
        return ans;
    }

    private boolean isDirectedGraphCyclic(Vertex v, HashSet<Vertex> visited, HashSet<Vertex> inStack) {

        visited.add(v);
        inStack.add(v);
    boolean ans=false;
    for( Vertex u:v.neighbors)
    {
        if( inStack.contains(u)||(!visited.contains(u)&& /* if already in call stack */
                isDirectedGraphCyclic(u,visited,inStack)))// or if the answer from child is true
        {
        return true;
        }
    }
    inStack.remove(v);
return false;
    }

}
