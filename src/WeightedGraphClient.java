public class WeightedGraphClient {
    public static void main(String[] args) {
// bellman ford algorithms
        // weights can be -ve also
       // WeightedGraph graph = new WeightedGraph();
    /*    for (int i = 1; i <= 5; i++) {//for bellman for
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
        graph.bellmanFord(1);*/
   /* WeightedGraph graphForCyclic= new WeightedGraph();
    for(int i=0;i<=4;i++)
        graphForCyclic.addVertex(i);
    graphForCyclic.addEdge(0,1,1);
    graphForCyclic.addEdge(1,2,1);
        graphForCyclic.addEdge(2,3,1);
        graphForCyclic.addEdge(2,4,1);
        graphForCyclic.addEdge(4,0,1);
        System.out.println(graphForCyclic.isCyclicUnionFindAlgo());
        System.out.println(graphForCyclic.isCyclicUnionRankCompression());*/
        /* Let us create following weighted graph
            10
        0--------1
         | \     |
        6|  5\   |15 ans =19
         |     \ |
         2--------3
            4*/
        WeightedGraph graphKrsuskal= new WeightedGraph();
        for (int i = 0; i <=3 ; i++) {
            graphKrsuskal.addVertex(i);
        }
        graphKrsuskal.addEdge(0,1,10);
        graphKrsuskal.addEdge(0,2,6);
        graphKrsuskal.addEdge(0,3,5);
        graphKrsuskal.addEdge(1,3,15);
        graphKrsuskal.addEdge(2,3,4);
        graphKrsuskal.kruskal();
    }
}
