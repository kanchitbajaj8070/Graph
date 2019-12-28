

public class GraphsClient {
    public static void main(String[] args) {
        //space of bfs and dfs = O(v)
        // time of bfs and dfs =O(v+e)
        Graphs< Integer> graph = new Graphs<>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph .addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5) ;
        graph.addVertex(0);

        /**
         * 1---0
         * |   |
         * 2---4
           | /
            3--5
         */
        graph.addEdge(1,0,true);
        graph.addEdge(1,2,true);
      graph.addEdge(2,4,true);
      //graph.addEdge(0,4,true);
     //graph.addEdge(4,3,true);
      //graph.addEdge(2,3,true);
      graph.addEdge(3,5,true);
       // graph.DFS(1);
        //graph.BFS(1);
       /* //graph.singleSourceShortestPath(1);
        graph.addVertex(6);
        graph.addVertex(7);
        graph.addEdge(6,7,true);
        int ans=graph.connectedComponents();
        System.out.println("\n Connected components number is "+ ans);*/
       //graph.topologicalSortUsingDFS();
       //graph.topologicalSortUsingBfs(1);
       // System.out.println(graph.isUndirectedGraphCyclic(1));
Graphs< Integer> g1= new Graphs<>();

        g1.addVertex(1);
        g1.addVertex(2);
        g1 .addVertex(3);
        g1.addVertex(4);
        g1.addVertex(5) ;
        g1.addVertex(0);
        g1.addEdge(1,0,false);
        g1.addEdge(1,2,false);
        g1.addEdge(2,4,false);
        g1.addEdge(4,0,false);
        //g1.addEdge(0,2,false);
        g1.addEdge(4,3,false);
        g1.addEdge(2,3,false);
        g1.addEdge(3,5,false);
        System.out.println( g1.isDirectedGraphCyclic());

    }
}
