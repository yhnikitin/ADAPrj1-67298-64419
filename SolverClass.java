public class SolverClass implements Solver {
  private int R;
  private int C;
  private int M;
  private int N;
  private char[][] grid;

  public SolverClass(int R, int C, int M, int N, char[][] grid) {
    this.R = R;
    this.C = C;
    this.M = M;
    this.N = N;
    this.grid = grid;
  }

  @Override
  //Method to solve the problem, currently returns 0 as a placeholder
  public int solve() {
    return 0 ;
  }
}
