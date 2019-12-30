import java.util.*;

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

// kruskal algorithm
//first find isCyclic by union find algorithm
    public boolean isCyclicUnionFindAlgo()
    {
        int parents[]= new int[vertices.size()];
        Arrays.fill(parents,-1);

        for( Integer v: vertices.keySet())
        {
            for(Pair e : vertices.get(v))
            {
                int startparent= findParent(v,parents);
                int endparent=findParent(e.data,parents);
                if(startparent==endparent)
                    return true;
                else
                    union(startparent,endparent,parents);
            }
        }
        return false;
    }

    private int findParent(Integer v,int[] parents) {
                if( parents[v]==-1)
                {
                    return v;
                }
                else
                    return findParent(parents[v],parents);

    }

    private void union(int s, int e, int[] parents) {
           // int s_set=findParent(s,parents);
            //int e_set=findParent(e,parents);
            parents[e]=s;
            return;
    }
    public boolean isCyclicUnionRankCompression()
    {//union-find normal takes O(n) in worst case
        // thois can be reduced by modifying the union funcion
        int[] parents= new int[vertices.size()];
        Arrays.fill(parents,-1);
        int[] rank= new int[vertices.size()];//intialize 0
        for( Integer v: vertices.keySet())
        {
            for( Pair e: vertices.get(v))
            {
                int x=findParent(v,parents);
                int y=findParent(e.data,parents);
                if(x==y)
                    return true;
                modifiedunion(x,y,parents,rank);
                for( int i=0;i<parents.length;i++)
                {
                    System.out.print( parents[i]+"  ");
                }
                System.out.println();
                for (int i = 0; i < rank.length; i++) {
                    System.out.print( rank[i]+"   ");
                }
                System.out.println();
            }
        }
        return false;
    }
    private void modifiedunion(int s_set, int e_set, int[] parents,int [] rank) {
        if(rank[s_set]> rank[e_set])
        {
            parents[e_set]=s_set;
        }
        else if( rank[e_set]> rank[s_set])
            parents[s_set]=e_set;
        else
        {
            parents[e_set]=s_set;
            rank[s_set]++;
        }
    }
    public class Edge
    {
        int start;
        int end;
        int cost;
        Edge(int s, int e, int c )
        {
            start=s;
            end=e;
            cost=c;
        }
        @Override
        public String toString()
        {
            String str= this.start+" -> "+ this.end+" ";
            return str;
        }
    }
    public void kruskal()
    {
        Comparator<Edge> comp=(e1,e2)->e1.cost-e2.cost;
        HashMap<Integer,Integer> parents = new HashMap<>();
        TreeSet< Edge> set= new TreeSet<>(comp);
        for( int v: vertices.keySet())
        {
            for( Pair e: vertices.get(v))
            {
                set.add( new Edge( v,e.data,e.cost));
            }
            parents.put(v,null);
        }
        int weight=0;
        HashSet<Edge> includedEdges= new HashSet<>();
        while(!set.isEmpty())
        {
            Edge e=set.pollFirst();
            if(findForKruskal(e.start,parents)==findForKruskal(e.end,parents))
                continue;
            else
            {
                includedEdges.add(e);
                weight+=e.cost;
                unionForKruskal(e.start,e.end,parents);
            }
        }
        System.out.println(weight);
        System.out.println(includedEdges);
    }
    public int findForKruskal( int i , HashMap<Integer,Integer> parents)
    {
        if( parents.get(i)==null)
        return i;
        return findForKruskal(parents.get(i),parents);
    }
    public void unionForKruskal( int s, int e, HashMap<Integer,Integer> parents)
    {
        int s_parent= findForKruskal(s,parents);
        int e_parent=findForKruskal(e,parents);
        parents.put(e_parent,s_parent);
    }


}
