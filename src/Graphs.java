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

    public void DFS(T data)
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

}
