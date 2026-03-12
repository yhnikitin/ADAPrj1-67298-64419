import java.io.*;

public class Main {
/* 
 * ---Confirm if it´s necessary to check this constraints or if it is just moonshak tests specs---
 * T->Number of Test Cases, 1<=T<=20
 * R->Number of Rows, 1<=R<=400
 * C->Number of Columns, 1<=C<=400
 * M->Number of Consecutive Jumps, 1<=M<=5
 * N->Max Jumps, 1<=N<=10
 * 
 * "." -> Tile with no constraints
 * "X" -> Tile with no diagonal jumps (LD RD)
 * "J" ->Tile with no jumps
 * "#" -> Tile that cannot be stepped on 
 * 
 * ---Hints---
 * Not supposed to store all the grids, just solve directly 1 by 1 and print out the result.
 * Time complexity may be just about one case and not the multiple grids, but we have to specify on report that it is only for 1 case
 * 
 * 
 * 
 **/
  public static void main(String[] args) throws Exception{
    BufferedReader in = new BufferedReader(new java.io.InputStreamReader(System.in));

    int T =  Integer.parseInt(in.readLine().trim());
    for(int t = 0; t < T;t++){
      String line = in.readLine().trim();
      String[] parts = line.split(" ");

      int R = Integer.parseInt(parts[0]);
      int C = Integer.parseInt(parts[1]);
      int M = Integer.parseInt(parts[2]);
      int N = Integer.parseInt(parts[3]);

      //Process the grid
      char[][] grid = new char[R][C];
      for(int i = 0; i < R;i++){
        String gridLine = in.readLine().trim();
        for(int j = 0; j < C;j++){
          grid[i][j] = gridLine.charAt(j);
        }
      }
      //Create a solver instance and get the result
      Solver solver = new SolverClass(R, C, M , N, grid);
      int result = solver.solve();
      System.out.println(result);
    }
  }
}
