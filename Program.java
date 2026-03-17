public class Program implements ProgramInterface {
    private int R;
    private int C;
    private int M;
    private int N;
    private char[][] grid;
    private static final int DIVISOR = 1000000007;

    public Program(int R, int C, int M, int N, char[][] grid) {
        this.R = R;
        this.C = C;
        this.M = M;
        this.N = N;
        this.grid = grid;
    }
    public int solve() {
        int[][][][] roll_dp = new int[3][C][M + 1][N + 1];
        roll_dp[0][0][0][0] = 1;

        for (int r = 0; r < R; r++) {
            int cur  = r % 3;
            int prev = (r - 1 + 3) % 3;
            int prev2 = (r - 2 + 3) % 3;

            if (r > 0) {
                for (int c = 0; c < C; c++) roll_dp[cur][c] = new int[M + 1][N + 1];
            }

            for (int c = 0; c < C; c++) {
                if (grid[r][c] == '#') continue;

                for (int j = 0; j <= M; j++) {
                    for (int k = 0; k <= N; k++) {
                        //RIGHT
                        if (c > 0) {
                            roll_dp[cur][c][0][k] = (roll_dp[cur][c][0][k] + roll_dp[cur][c - 1][j][k]) % DIVISOR;
                        }

                        //DOWN
                        if (r > 0) {
                            roll_dp[cur][c][0][k] = (roll_dp[cur][c][0][k] + roll_dp[prev][c][j][k]) % DIVISOR;
                        }

                        //LE(F)T DOWN
                        if (r > 0 && c + 1 < C && j > 0 && k > 0) {
                            if (grid[r - 1][c + 1] != 'J' && grid[r - 1][c + 1] != 'X' && grid[r - 1][c + 1] != '#') {
                                roll_dp[cur][c][j][k] = (roll_dp[cur][c][j][k] + roll_dp[prev][c + 1][j - 1][k - 1]) % DIVISOR;
                            }
                        }

                        //RIGHT DOWN
                        if (r > 0 && c > 0 && j > 0 && k > 0) {
                            if (grid[r - 1][c - 1] != 'J' && grid[r - 1][c - 1] != 'X' && grid[r - 1][c - 1] != '#') {
                                roll_dp[cur][c][j][k] = (roll_dp[cur][c][j][k] + roll_dp[prev][c - 1][j - 1][k - 1]) % DIVISOR;
                            }
                        }

                        //DOUBLE TROUBLE
                        if (r > 1 && j > 0 && k > 0) {
                            if (grid[r - 2][c] != 'J' && grid[r - 2][c] != '#') {
                                roll_dp[cur][c][j][k] = (roll_dp[cur][c][j][k] + roll_dp[prev2][c][j - 1][k - 1]) % DIVISOR;
                            }
                        }
                    }
                }
            }
        }

        int last = (R - 1) % 3;
        int ans = 0;
        for (int j = 0; j <= M; j++)
            for (int k = 0; k <= N; k++)
                ans = (ans + roll_dp[last][C - 1][j][k]) % DIVISOR;
        return ans;
    }
}
