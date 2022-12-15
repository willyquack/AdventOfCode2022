package d14;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class Cave{
    public static void main(String[] args) {
        new Cave();
    }

    Cave() {
        try {
            int numDrops = calcNumSand("./d14/large.txt");
            System.out.println(numDrops);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    int floor = 0;

    int calcNumSand(String fName) throws IOException {
        Scanner scan = new Scanner(new File(fName));
        Space[][] board = new Space[1000][200];
        for(int i = 0;i < board.length;i++) {
            for(int j = 0;j < board[i].length;j++) {
                board[i][j] = Space.AIR;
            }
        }
        while(scan.hasNextLine()) {
            String[] line = scan.nextLine().split(" -> ");
            int x = Integer.parseInt(line[0].split(",")[0]);
            int y = Integer.parseInt(line[0].split(",")[1]);
            int lastX = x;
            int lastY = y;
            for(int i = 1;i < line.length;i++) {
                String[] coord = line[i].split(",");
                lastX = x;
                lastY = y;
                x = Integer.parseInt(coord[0]);
                y = Integer.parseInt(coord[1]);
                if(y > floor) {
                    floor = y;
                }
                if(lastX <= x) {
                    for(int j = lastX;j <= x;j++) {
                        board[j][y] = Space.ROCK;
                    }
                }
                if(lastY <= y) {
                    for(int j = lastY;j <= y;j++) {
                        board[x][j] = Space.ROCK;
                    }
                }    
                if(lastX > x) {
                    for(int j = x;j <= lastX;j++) {
                        board[j][y] = Space.ROCK;
                    }
                }
                if(lastY > y) {
                    for(int j = y;j <= lastY;j++) {
                        board[x][j] = Space.ROCK;
                    }
                }                            
            }

        }
        int numDrops = dropSand(board);
        return numDrops;
    }

    int dropSand(Space[][] board) {
        int voidX = getVoid(board);
        int count = 0;
        while(board[500][0] != Space.SAND) {
            count++;
            Coord curCord = new Coord(500, 0);
            while(canMove(board, curCord.x, curCord.y)) {
                curCord = makeMove(board,curCord);
            }
            //printBoard(board);
            if(curCord.y == board[curCord.x].length - 1) break;
            board[curCord.x][curCord.y] = Space.SAND;
            //printBoard(board);
            if(count > board.length * board[0].length) {
                printBoard(board);
                return -1;
            }
        }
        return count;
    }

    Coord makeMove(Space[][] board,Coord curPos) {
        //printBoard(board);
        if(board[curPos.x][curPos.y + 1] == Space.AIR) {
            curPos.y += 1;
            return curPos;
        }
        if(board[curPos.x-1][curPos.y + 1] == Space.AIR) {
            curPos.y += 1;
            curPos.x -= 1;
            return curPos;
        }
        if(board[curPos.x + 1][curPos.y + 1] == Space.AIR) {
            curPos.y += 1;
            curPos.x += 1;
            return curPos;
        }
        return curPos;
    }

    boolean canMove(Space[][] board,int x,int y) {
        if(y == floor + 1) return false;
        return board[x-1][y+1] == Space.AIR || board[x][y+1] == Space.AIR || board[x+1][y+1] == Space.AIR;
    }

    boolean checkCol(Space[][] board,int x) {
        for(int i = 0;i < board[x].length;i++) {
            if(board[x][i] == Space.SAND) {
                return true;
            }
        }
        return false;
    }

    int getVoid(Space[][] board) {
        int leastX = Integer.MAX_VALUE;
        for(int i = 0;i < board.length;i++) {
            for(int j = 0;j < board[i].length;j++) {
                if(board[i][j] == Space.ROCK && i < leastX){
                    leastX = i;
                }
            }
        }
        return leastX;
    }

    void printBoard(Space[][] board) {
        for(int i = 494;i < 504;i++) {
            System.out.print(i + " ");
            for(int j = 0;j < 10;j++) {
                Space s = board[i][j];
                if(s == Space.AIR) {
                    System.out.print(" . ");
                }
                if(s == Space.ROCK) {
                    System.out.print(" # ");
                }
                if(s == Space.SAND) {
                    System.out.print(" O ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    class Coord {
        int x;
        int y;
        Coord(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }

    enum Space {
        ROCK,
        AIR,
        SAND
    }
}