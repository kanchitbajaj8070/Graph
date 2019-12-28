

public class GraphsClient {
    public static void main(String[] args) {
        Graphs< Integer> graph = new Graphs<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph .addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(6);
        /**
         * 1--=2
         * |    \
         * 3--4--5--6
         */
        graph.addEdge(1,2,true);
        graph.addEdge(1,3,true);
        graph.addEdge(3,4,true);
      graph.addEdge(4,5,true);
      graph.addEdge(5,6,true);
      graph.addEdge(5,2,true);
        graph.DFS(1);
    }
}
