/* *****************************************************************************
 *  Name:              Purab Patel
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */
public class Percolation {
    private int[][] grid;
    final private int nN;
    private int numOpen = 0;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        nN = n;
        grid = new int[3][nN * nN + 2];
        for (int i = 0; i <= nN * nN + 1; i++) {
            grid[0][i] = i;
            grid[1][i] = 1;
            grid[2][i] = 1;
        }
        for (int i = 1; i <= nN; i++) {
            union(index(1, i), nN * nN);
            union(index(nN, i), nN * nN + 1);
        }
    }

    private int index(int row, int col) {
        if (row < 1 || col < 1 || row > nN || col > nN) {
            return -1;
        }
        else {
            return ((row - 1) * nN + col - 1);
        }
    }

    private int root(int id) {
        while (grid[0][id] != id) {
            id = grid[0][id];
        }
        return id;
    }

    private void union(int id1, int id2) {
        int root1 = root(id1);
        int root2 = root(id2);
        if (grid[2][root1] >= grid[2][root2]) {
            grid[0][root2] = grid[0][root1];
            grid[2][root1] += grid[0][root2];
        }
        else {
            grid[0][root1] = grid[0][root2];
            grid[2][root2] += grid[0][root1];
        }
    }

    private boolean find(int id1, int id2) {
        if (root(id1) == root(id2)) {
            return true;
        }
        return false;

    }

    public void open(int row, int col) {
        if (row > nN || col > nN || row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            int id = index(row, col);

            grid[1][id] = 0;
            numOpen++;

            int idleft = index(row - 1, col);
            int idright = index(row + 1, col);
            int idup = index(row, col + 1);
            int iddown = index(row, col - 1);

            if (idleft != -1) {
                if (grid[1][idleft] == 0) {
                    union(id, idleft);
                }
            }
            if (idright != -1) {
                if (grid[1][idright] == 0) {
                    union(id, idright);
                }
            }
            if (idup != -1) {
                if (grid[1][idup] == 0) {
                    union(id, idup);
                }
            }
            if (iddown != -1) {
                if (grid[1][iddown] == 0) {
                    union(id, iddown);
                }
            }
        }

    }

    public boolean isOpen(int row, int col) {
        if (row > nN || col > nN || row < 1 || col < 1) {
            throw new IllegalArgumentException();
        }
        if (grid[1][index(row, col)] == 0) {
            return true;
        }
        return false;
    }

    public boolean isFull(int row, int col) {
        if (row > nN || col > nN) {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return find(index(row, col), nN * nN);
    }

    public int numberOfOpenSites() {
        return numOpen;
    }

    public boolean percolates() {
        if (nN == 1) {
            return isOpen(1, 1);
        }
        else {
            return find(nN * nN, nN * nN + 1);
        }
    }

}
