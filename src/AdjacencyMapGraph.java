import java.util.HashMap;
import java.util.LinkedList;

class AdjacencyMapGraph {
    static HashMap<Integer, LinkedList<Integer>> vertices= new HashMap<>();
    public static void addVertex( int u )
    {
        vertices.put(u,new LinkedList<>());
    }
    public static void addEdge( int u,int v, boolean isBidirectional) {
        vertices.get(u).add(v);
        if (isBidirectional)
            vertices.get(v).add(u);
    }
    public static void main(String[] args) {

    }


}
