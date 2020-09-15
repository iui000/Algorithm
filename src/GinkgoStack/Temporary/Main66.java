package GinkgoStack.Temporary;

import java.util.Scanner;

public class Main66 {
    private boolean[][] visited;

    //        x-1,y
    // x,y-1  x,y    x,y+1
    //        x+1,y
    private int[][] direction = {{-1, 0}, {0, +1}, {1, 0}, {0, -1}};//顺时针转
    // 盘面上有多少行
    private int m;
    // 盘面上有多少列
    private int n;
    private String word;
    private char[][] board;

    public boolean exist(char[][] matrix, String word) {
        m = matrix.length;
        if (m == 0) {
            return false;
        }
        n = matrix[0].length;
        visited = new boolean[m][n];
        this.word = word;
        this.board = matrix;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int i, int j, int start) {
        if (start == word.length() - 1) {
            return board[i][j] == word.charAt(start);
        }
        if (board[i][j] == word.charAt(start)) {
            visited[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int newX = i + direction[k][0];
                int newY = j + direction[k][1];
                if (inArea(newX, newY) && !visited[newX][newY]) {
                    if (dfs(newX, newY, start + 1)) {
                        return true;
                    }
                }
            }
            visited[i][j] = false;
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }

    public static void main(String[] args) {

        char[][] matrix =
                {
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'C', 'S'},
                        {'A', 'D', 'E', 'E'}
                };

        Scanner scanner = new Scanner(System.in);
        String word = scanner.nextLine();

        if(word.length() == 0){
            System.out.println(false);
            return;
        }

        Main66 solution = new Main66();
        boolean exist = solution.exist(matrix, word);
        System.out.println(exist);
    }

}