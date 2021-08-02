public class DFSQuestion {

    // 200. Number of Islands

    public void dfs_numIslands(char[][] grid, int i, int j, int[][] dir) {

        int m = grid.length;
        int n = grid[0].length;

        grid[i][j] = '0';
        for (int k = 0; k < dir.length; k++) {

            int r = i + dir[k][0];
            int c = j + dir[k][1];

            if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == '1')
                dfs_numIslands(grid, r, c, dir);

        }

    }

    public int numIslands(char[][] grid) {

        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        int components = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    components++;
                    dfs_numIslands(grid, i, j, dir);
                }
            }
        }

        return components;

    }

    // 695. Max Area of Island

    public int dfs_maxAreaOfIsland(int[][] grid, int i, int j, int[][] dir) {

        int m = grid.length;
        int n = grid[0].length;
        grid[i][j] = 0;

        int size = 0;

        for (int k = 0; k < dir.length; k++) {

            int r = i + dir[k][0];
            int c = j + dir[k][1];

            if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1)
                size += dfs_maxAreaOfIsland(grid, r, c, dir);

        }

        return size + 1;

    }

    public int maxAreaOfIsland(int[][] grid) {

        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

        int maxArea = 0;
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {
                    int max = dfs_maxAreaOfIsland(grid, i, j, dir);
                    maxArea = Math.max(maxArea, max);
                }
            }
        }
        return maxArea;
    }

    // 463. Island Perimeter

    public int islandPerimeter(int[][] grid) {

        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

        int m = grid.length;
        int n = grid[0].length;
        int onesCount = 0;
        int nbrs = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == 1) {

                    onesCount++;

                    for (int d = 0; d < dir.length; d++) {

                        int r = i + dir[d][0];
                        int c = j + dir[d][1];

                        if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 1)
                            nbrs++;

                    }
                }

            }
        }

        return 4 * onesCount - nbrs;

    }

    // 130. Surrounded Regions

    public void dfs_surrounded(char[][] grid, int i, int j, int[][] dir) {

        int m = grid.length;
        int n = grid[0].length;

        grid[i][j] = '$';

        for (int d = 0; d < dir.length; d++) {

            int r = i + dir[d][0];
            int c = j + dir[d][1];

            if (r >= 0 && r < m && c >= 0 && c < n && grid[r][c] == 'O')
                dfs_surrounded(grid, r, c, dir);

        }

    }

    public void solve(char[][] grid) {

        int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {

                    if (grid[i][j] == 'O')
                        dfs_surrounded(grid, i, j, dir);
                }

            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (grid[i][j] == '$')
                    grid[i][j] = 'O';
                else if (grid[i][j] == 'O')
                    grid[i][j] = 'X';

            }
        }

    }

    public static void main(String[] args) {

    }

}
