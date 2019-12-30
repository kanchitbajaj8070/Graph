public class FloodfillAlgorithms {
    /*
    Input:
       screen[M][N] = {{1, 1, 1, 1, 1, 1, 1, 1},
                      {1, 1, 1, 1, 1, 1, 0, 0},
                      {1, 0, 0, 1, 1, 0, 1, 1},
                      {1, 2, 2, 2, 2, 0, 1, 0},
                      {1, 1, 1, 2, 2, 0, 1, 0},
                      {1, 1, 1, 2, 2, 2, 2, 0},
                      {1, 1, 1, 1, 1, 2, 1, 1},
                      {1, 1, 1, 1, 1, 2, 2, 1},
                      };
    x = 4, y = 4, newColor = 3
The values in the given 2D screen indicate colors of the pixels.
x and y are coordinates of the brush, newColor is the color that
should replace the previous color on screen[x][y] and all surrounding
pixels with same color.

Output:
Screen should be changed to following.
       screen[M][N] = {{1, 1, 1, 1, 1, 1, 1, 1},
                      {1, 1, 1, 1, 1, 1, 0, 0},
                      {1, 0, 0, 1, 1, 0, 1, 1},
                      {1, 3, 3, 3, 3, 0, 1, 0},
                      {1, 1, 1, 3, 3, 0, 1, 0},
                      {1, 1, 1, 3, 3, 3, 3, 0},
                      {1, 1, 1, 1, 1, 3, 1, 1},
                      {1, 1, 1, 1, 1, 3, 3, 1},
                      };
     */
    public static void main(String[] args) {
        int screen[][] = {{1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
        };
        int m=8;
        int n=8;
        int oldcolor=2;
        int newcolor=3;
        int[]dx={-1,0,1,0};//wnes
        int[]dy={0,-1,0,1};
        floodFill( screen,m,n,3,3,oldcolor,newcolor,dx,dy);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n ; j++) {
                System.out.print(screen[i][j]+" ");
            }
            System.out.println();
        }
    }

    private static void floodFill(int[][] screen, int m, int n, int i, int j, int oldcolor, int newcolor,int[]dx,int[]dy)
    {
    if( i>=m ||i<0 || j<0||j>=n)
        return;
    if( screen[i][j]!=oldcolor)
        return;
    screen[i][j]=newcolor;
        for (int k = 0; k <4; k++) {
            floodFill(screen,m,n,i+dx[k],j+dy[k],oldcolor,newcolor,dx,dy);
        }

    }

}
