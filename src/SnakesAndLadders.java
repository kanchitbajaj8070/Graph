import java.lang.reflect.Array;
import java.util.*;

public class SnakesAndLadders {
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
        // we have a board array representing all positions of the board
        // for each cell we store and increment in it that how much forward we have to go on
// reacing the cell, for ladders we have +ve incremenent and for snakes we have -ve( decremrnt)
// cost of each die throw is 1.
// we will use bfs/single source shortest path to implement it.
        // graph is unweighted and directed.
        int cells = 36;
        int[] board = new int[cells + 1];
        Arrays.fill(board, 0);
        board[2] = 13;
        board[5] = 2;
        board[9] = 18;
        board[18] = 11;
        board[17] = -13;
        board[20] = -14;
        board[24] = -8;
        board[25] = -10;
        board[32] = -2;
        board[34] = -22;

        solve(cells,board);
    }

    public static void solve( int cells, int[] board) {
        for (int i = 0; i <= cells; i++) {
            addVertex(i);
            for (int dice = 1; dice <= 6; dice++) {

                if (i + dice <= cells)
                    addEdge(i, i + dice + board[i + dice], false);
            }

        }
        System.out.println(vertices);
            int k=0;
        int [] cost= new int [cells+1];
        int [] parents=new int [ cells+1];
        Queue<Integer> queue= new LinkedList<>();
       queue.add( 0);
       queue.add(null);
            HashSet<Integer> visited= new HashSet<>();
            visited.add(0);
            while (queue.size()>1)
            {
                Integer v =queue.remove();
                if(v!=null)
                {
               /*     if(cost[v]!=0)
                cost[v]=Math.min(k,cost[v]);// this statement is ignored because we
                //always put the first coming value in cost which will be the shortest in itself
                    else*/
                        cost[v]=k;
                for(int nbrs: vertices.get(v))
                {
                    if(!visited.contains(nbrs))
                    {
                        queue.add(nbrs);
                        visited.add(nbrs);
                        parents[nbrs]=v;
                    }
                }
            }
            else
                { queue.add(null);
                k++;
                }
            }
            System.out.println("Minimum  throws is "+cost[cells]);
            int dest=cells;
        System.out.print(dest+" <-- ");
            while( dest!=0)
            {
                System.out.print(parents[dest]+" <-- " );
                dest=parents[dest];

            }


    }

}
