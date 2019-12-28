import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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

}
