import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class WeightedGraph {

        public HashMap<Integer,LinkedList<Pair>> vertices= new HashMap<>();
        public class Pair
        {
            int data;
            int cost;
            Pair( int d,int cost)
            {
                data=d;
                this.cost=cost;

            }
        }

        public void addVertex( int v)
        {
            vertices.put(v,new LinkedList<>());
        }

        public void addEdge( int start, int end,int cost)
        {
            if(vertices.get(start)!=null)
            {
                vertices.get(start).add(new Pair(end, cost));
            }
        }
        public void bellmanFord(int src) {
            HashMap<Integer, Integer> distance = new HashMap<>();
            for (Integer v : vertices.keySet()) {
                if (v != src)
                    distance.put(v, Integer.MAX_VALUE);
                else
                    distance.put(v, 0);
            }
            //relaxation- iterate v-1 times and perform the foll operation
            // if distance[v]> distance[u]+ weight(u,v) = distance[v]=distance[u]+weight(u,v)
            for (int k = 1; k <= vertices.size() - 1; k++) {
                int source = src;
                for (Integer v : vertices.keySet()) {
                    for (Pair pair : vertices.get(v)) {
                        if (distance.get(v) != Integer.MAX_VALUE && distance.get(v) + pair.cost < distance.get(pair.data)) {
                            distance.put(pair.data, distance.get(v) + pair.cost);
                        }
                    }
                }
            }
            // check for negative cycle if at next iteration also val changes
            boolean hasNegativeCycles = false;
            for (Integer v : vertices.keySet()) {
                for (Pair pair : vertices.get(v)) {
                    if (distance.get(v) != Integer.MAX_VALUE && distance.get(v) + pair.cost < distance.get(pair.data)) {
                        hasNegativeCycles = true;
                        distance.put(pair.data, distance.get(v) + pair.cost);
                        break;
                    }
                }
            }
            if (hasNegativeCycles) {
                System.out.println(" graph has negative cycle. has bellman ford cant find" +
                        " the correct shortest path");
                return;
            } else
            {
                for( Map.Entry<Integer,Integer> entry: distance.entrySet())
                {
                    System.out.println(entry.getKey()+" has distance "+entry.getValue());

                }

            }

}
    }
