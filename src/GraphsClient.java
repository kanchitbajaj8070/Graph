

public class GraphsClient {
    public static void main(String[] args) {
        //space of bfs and dfs = O(v)
        // time of bfs and dfs =O(v+e)
        Graphs< Integer> graph = new Graphs<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph .addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addVertex(0);
        /**
         * 1--=0
         * |   |
         * 2---4
           | /
            3--5
         */
        graph.addEdge(1,0,true);
        graph.addEdge(1,2,true);
        graph.addEdge(2,4,true);
      graph.addEdge(4,0,true);
      graph.addEdge(2,3,true);
      graph.addEdge(3,4,true);
      graph.addEdge(3,5,true);
       // graph.DFS(1);
        //graph.BFS(1);
       /* //graph.singleSourceShortestPath(1);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addEdge(6,7,true);
        int ans=graph.connectedComponents();
        System.out.println("\n Connected components number is "+ ans);*/
    }
}
