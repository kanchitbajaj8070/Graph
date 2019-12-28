import java.util.*;

public class DijkstraAlgorithm<T> {
    HashMap< T, LinkedList<pair>> vertices= new HashMap<>();
    public class pair implements Comparable<pair>
    {
        T data;
        int cost;
        pair( T data, int cost)
        {
            this.data=data;
            this.cost=cost;
        }
        @Override
        public int compareTo( pair o)
        {
            return this.cost-o.cost;
        }
    }
    public void addVertex(T v)
    {
    vertices.put(v,new LinkedList<>());
    }
    public void addEdge( T s, T e, int cost) {
        vertices.get(s).add( new pair(e,cost));
    }

    public static void main(String[] args) {
        DijkstraAlgorithm<Integer> graph = new DijkstraAlgorithm<>();
        for (int i = 1; i <= 4; i++) {
            graph.addVertex(i);
        }
        graph.addEdge(1, 4, 7);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 2);
       graph.dijkstra(graph, 1,4);
    }

    private  void dijkstra(DijkstraAlgorithm<T> graph,T src, T dest) {
        for (Map.Entry<T, LinkedList<DijkstraAlgorithm<T>.pair>> entry : graph.vertices.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (DijkstraAlgorithm<T>.pair p : entry.getValue())
                System.out.print("(" + p.data + "," + p.cost + ")");
            System.out.println();
        }
        HashMap<T, Integer> distances = new HashMap<>();
        for (T t : graph.vertices.keySet()) {
            distances.put(t, Integer.MAX_VALUE);
        }
        distances.put(src, 0);
        TreeSet<DijkstraAlgorithm<T>.pair> set = new TreeSet<>();
        set.add(new pair(src,0));
        while(!set.isEmpty())
        {   // pick minimum cost nod e, update its neighbors and remove it
            DijkstraAlgorithm.pair parent= set.first();
            int nodeDist=distances.get(parent.data);
            for( DijkstraAlgorithm<T>.pair nbrs: vertices.get(parent.data) )
            {
                if(nodeDist+ nbrs.cost<=distances.get(nbrs.data))
                {
                    distances.put( nbrs.data,nodeDist+nbrs.cost);
                    if(set.contains(nbrs)) {
                        set.remove(nbrs);
                    }
                    set.add( new pair( nbrs.data,nodeDist+nbrs.cost));
                }
            }
            set.remove(parent);
        }
        System.out.println("******************");
for( Map.Entry< T , Integer> trav: distances.entrySet())
{
    System.out.print(trav.getKey()+" dist. is -> ");
    System.out.println(trav.getValue());
    System.out.println();
}
        System.out.println("**********");
    }



    }

