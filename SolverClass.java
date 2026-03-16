public class SolverClass implements Solver {
  private int R;
  private int C;
  private int M;
  private int N;
  private char[][] grid;
  private static final int DIVISOR = 1000000007;

  public SolverClass(int R, int C, int M, int N, char[][] grid) {
    this.R = R;
    this.C = C;
    this.M = M;
    this.N = N;
    this.grid = grid;
  }

  @Override
  //solve, process the grid and return the number of ways to reach the bottom-right corner
  public int solve() {
      int[][][][] dp = new int[R][C][M + 1][N + 1];
      dp[0][0][0][0] = 1;

      for (int r = 0; r < R; r++) {
          for (int c = 0; c < C; c++) {

              if (grid[r][c] == '#') continue;

              for (int j = 0; j <= M; j++) {
                  for (int k = 0; k <= N; k++) {

                      if (c - 1 >= 0) {
                          dp[r][c][0][k] = (dp[r][c][0][k] + dp[r][c - 1][j][k]) % DIVISOR;
                      }

                      if (r - 1 >= 0) {
                          dp[r][c][0][k] = (dp[r][c][0][k] + dp[r - 1][c][j][k]) % DIVISOR;
                      }

                      if (r - 2 >= 0 && j > 0 && k > 0) {
                          if (grid[r - 2][c] != 'J' && grid[r - 2][c] != '#') {
                              dp[r][c][j][k] = (dp[r][c][j][k] + dp[r - 2][c][j - 1][k - 1]) % DIVISOR;
                          }
                      }

                      if (r > 0 && c + 1 < C && j > 0 && k > 0) {
                          if (grid[r - 1][c + 1] != 'J' && grid[r - 1][c + 1] != 'X' && grid[r - 1][c + 1] != '#') {
                              dp[r][c][j][k] = (dp[r][c][j][k] + dp[r - 1][c + 1][j - 1][k - 1]) % DIVISOR;
                          }
                      }

                      if (r > 0 && c > 0 && j > 0 && k > 0) {
                          if (grid[r - 1][c - 1] != 'J' && grid[r - 1][c - 1] != 'X' && grid[r - 1][c - 1] != '#') {
                              dp[r][c][j][k] = (dp[r][c][j][k] + dp[r - 1][c - 1][j - 1][k - 1]) % DIVISOR;
                          }
                      }
                  }
              }
          }
      }

      int ans = 0;
      for (int j = 0; j <= M; j++) {
          for (int k = 0; k <= N; k++) {
              ans = (ans + dp[R - 1][C - 1][j][k]) % DIVISOR;
          }
      }

      return ans;
  }
}
