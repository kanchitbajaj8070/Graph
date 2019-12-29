import java.util.*;
/*   int graph[V][V] = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                        { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                        { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                        { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                        { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                        { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                        { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                        { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                        { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
Vertex   Distance from Source
0                0
1                4
2                12
3                19
4                21
5                11
6                9
7                8
8                14
  */
public class DijkstraAlgorithm {
    HashMap< Integer, LinkedList<pair>> vertices= new HashMap<>();
    public class pair implements Comparable<pair>
    {
        int data;
        int cost;
        pair( int data, int cost)
        {
            this.data=data;
            this.cost=cost;
        }
      @Override
        public int compareTo( pair o) {
            if(this.cost!=o.cost)
          return Integer.compare(this.cost, o.cost);
            else
                return Integer.compare(this.data,o.data);

      }

    }
    public void addVertex(Integer v)
    {

    vertices.put(v,new LinkedList<>());
    }
    public void addEdge( Integer s, Integer e, int cost) {
        vertices.get(s).add( new pair(e,cost));
       // vertices.get(e).add(new pair(s,cost));
    }

    public static void main(String[] args) {
        DijkstraAlgorithm graph = new DijkstraAlgorithm();
        int V=9;
        int graphmat[][] = { { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
            { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
            { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
            { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
            { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
            { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
            { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
            { 0, 0, 2, 0, 0, 0, 6, 7, 0 } };
        for (int i = 0; i <V ; i++) {
            graph.addVertex(i);
        }
        for (int i = 0; i <V ; i++) {
            for (int j = 0; j <V ; j++) {
                if( graphmat[i][j]!=0)
                {
                    graph.addEdge(i,j,graphmat[i][j]);
                }

            }
        }
        graph.dijkstraUsingTreeset(graph,0,8);

/*        for (int i = 1; i <= 4; i++) {
            graph.addVertex(i);
        }
        graph.addEdge(1, 4, 7);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 3, 1);
        graph.addEdge(3, 4, 2);
       graph.dijkstra(graph, 1,4);*/
    }

    private  void dijkstra(DijkstraAlgorithm graph,Integer src, Integer dest) {
        for (Map.Entry<Integer, LinkedList<DijkstraAlgorithm.pair>> entry : graph.vertices.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (DijkstraAlgorithm.pair p : entry.getValue())
                System.out.print("(" + p.data + "," + p.cost + ")");
            System.out.println();
        }
        HashMap<Integer, Integer> distances = new HashMap<>();
        for (Integer t : graph.vertices.keySet()) {
            distances.put(t, Integer.MAX_VALUE);
        }
        distances.put(src, 0);
        System.out.println(distances);
        PriorityQueue<pair> queue=new PriorityQueue<>();
        queue.add(new pair(src,0));
        while(!queue.isEmpty())
        {   // pick minimum cost nod e, update its neighbors and remove it
            DijkstraAlgorithm.pair parent= queue.poll();
            System.out.println(" removed element is -> "+ parent.data+" "+ ((parent.cost)));
            int nodeDist=distances.get(parent.data);
            for( DijkstraAlgorithm.pair nbrs: vertices.get(parent.data) )
            {
                if(nodeDist+ nbrs.cost<=distances.get(nbrs.data))
                {
                    distances.put( nbrs.data,nodeDist+nbrs.cost);
                    if(queue.contains(nbrs))
                        queue.remove(nbrs);
                    queue.add( new pair( nbrs.data,nodeDist+nbrs.cost));

                    System.out.println(" newly added element is -> "+ nbrs.data+" "+ distances.get(nbrs.data));
                }

            }
            for( pair pa: queue) {
                System.out.println(pa.data + "    " + pa.cost);

            }
        }
        System.out.println("******************");
        for( Map.Entry< Integer , Integer> trav: distances.entrySet())
        {
            System.out.print(trav.getKey()+" dist. is -> ");
            System.out.println(trav.getValue());
            System.out.println();
        }
        System.out.println("**********");
    }

 public void dijkstraUsingTreeset(DijkstraAlgorithm graph,Integer src, Integer dest) {
        for (Map.Entry<Integer, LinkedList<DijkstraAlgorithm.pair>> entry : graph.vertices.entrySet()) {
            System.out.print(entry.getKey() + " -> ");
            for (DijkstraAlgorithm.pair p : entry.getValue())
                System.out.print("(" + p.data + "," + p.cost + ")");
            System.out.println();
        }
        HashMap<Integer, Integer> distances = new HashMap<>();
        for (Integer t : graph.vertices.keySet()) {
            distances.put(t, Integer.MAX_VALUE);
        }
        distances.put(src, 0);
        System.out.println(distances);
      TreeSet<pair> set= new TreeSet<>();
        set.add(new pair(src,0));
        while(!set.isEmpty())
        {   // pick minimum cost nod e, update its neighbors and remove it
            DijkstraAlgorithm.pair parent= set.pollFirst();
            System.out.println(" removed element is -> "+ parent.data+" "+ (parent.cost));
            int nodeDist=distances.get(parent.data);
            for( DijkstraAlgorithm.pair nbrs: vertices.get(parent.data) )
            {
                if(nodeDist+ nbrs.cost<=distances.get(nbrs.data))
                {
                    distances.put( nbrs.data,nodeDist+nbrs.cost);
                        if(set.contains(nbrs))
                            set.remove(nbrs);
                    set.add( new pair( nbrs.data,nodeDist+nbrs.cost));
                    System.out.println(" newly added element is -> "+ nbrs.data+" "+ (nbrs.cost+nodeDist));
                }

            }
            for( pair pa: set) {
                System.out.println(pa.data + "    " + distances.get(pa.data));

            }
        }
        System.out.println("******************");
        for( Map.Entry< Integer , Integer> trav: distances.entrySet())
        {
            System.out.print(trav.getKey()+" dist. is -> ");
            System.out.println(trav.getValue());
            System.out.println();
        }
        System.out.println("**********");
    }
    }

