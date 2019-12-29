public class WeightedGraphClient {
    public static void main(String[] args) {
// bellman ford algorithms
        // weights can be -ve also
        WeightedGraph graph = new WeightedGraph();
        for (int i = 1; i <= 5; i++) {
            graph.addVertex(i);
        }
        //output is 0 -1 2 -2 1
        graph.addEdge(1,2,-1);
        graph.addEdge(1,3,4);
        graph.addEdge(2,3,3);
        graph.addEdge(2,5,2);// change to -2 for negative cycle
        graph.addEdge(2,4,2);
        graph.addEdge(5,4,-3);
        graph.addEdge(4,2,1);
        graph.addEdge(4,3,5);
graph.bellmanFord(1);
    }
}
