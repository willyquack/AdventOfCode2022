package d8;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Visible {
    public static void main(String[] args) {
        try {
            new Visible();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int[][] board;

    Visible() throws IOException{
        Scanner scan = new Scanner(new File("./d8/large.txt"));
        String line = scan.nextLine();
        this.board = new int[line.length()][];
        board[0] = strToIntArr(line);
        int index = 1;
        while(scan.hasNextLine()) {
            line = scan.nextLine();
            board[index] = strToIntArr(line);
            index++;
        }
        int count = 0;
        int highScore = Integer.MIN_VALUE;
        for(int i = 0;i < board.length;i++) {
            for(int j = 0;j < board[i].length;j++) {
                int s = score(i,j);
                if(s > highScore) {
                    highScore = s;
                }
            }
        }
        System.out.println(highScore);
    }

    boolean isVisible(int x,int y) {
        if(x == 0 || y == 0 || x == board.length - 1 || y == board[x].length - 1) {
            return true;
        }
        boolean visible = true;
        for(int i = x + 1;i < board.length;i++) {
            if(board[x][y] <= board[i][y]) {
                visible = false;
            }
        }
        if(visible) {
            return visible;
        }
        visible = true;
        for(int i = x - 1;i > -1;i--) {
            if(board[x][y] <= board[i][y]) {
                visible = false;
            }
        }
        if(visible) {
            return true;
        }
        visible = true;
        for(int i = y + 1;i < board[x].length;i++) {
            if(board[x][y] <= board[x][i]) {
                visible = false;
            }
        }
        if(visible) {
            return true;
        }
        visible = true;
        for(int i = y - 1;i > -1;i--) {
            if(board[x][y] <= board[x][i]) {
                visible = false;
            }
        }
        return visible;
    }

    int score(int x,int y) {
        int score = 0;
        int i = 0;
        int dist = 0;
        for(i = x + 1;i < board.length;i++) {
            if(board[x][y] <= board[i][y]) {
                dist++;
                break;
            }
            dist++;
        }
        score = dist;
        dist = 0;
        for(i = x - 1;i > -1;i--) {
            if(board[x][y] <= board[i][y]) {
                dist++;
                break;
            }
            dist++;
        }
        score *= dist;
        dist = 0;
        for(i = y + 1;i < board[x].length;i++) {
            if(board[x][y] <= board[x][i]) {
                dist++;
                break;
            }
            dist++;
        }
        score *= dist;
        dist = 0;
        for(i = y - 1;i > -1;i--) {
            if(board[x][y] <= board[x][i]) {
                dist++;
                break;
            }
            dist++;
        }
        score *= dist;
        return score;
    }

    int[] strToIntArr(String s) {
        int[] res = new int[s.length()];
        for(int i = 0;i < s.length();i++) {
            res[i] = Integer.parseInt(s.substring(i,i+1));
        }
        return res;
    }

}
