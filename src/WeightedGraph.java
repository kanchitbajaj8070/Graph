import java.util.LinkedList;

public class WeightedGraph<T> {

        public LinkedList<Vertex> vertices;
        WeightedGraph()
        {
            vertices= new LinkedList<>();
        }
        public class Vertex
        {
            T data;
            int cost;
            LinkedList<Vertex> neighbors;
            Vertex( T d,int cost)
            {
                data=d;
                this.cost=cost;
                neighbors= new LinkedList<>();
            }
        }

        public void addVertex( T data,int cost)
        {
            Vertex v= new Vertex(data,cost);
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

    }
