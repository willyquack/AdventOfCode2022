package d9;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Rope {
    public static void main(String[] args) {
        try {
            new Rope();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    int tx;
    int ty;
    HashMap<String,Boolean> visited;
    Coord[] knots;

    Rope() throws IOException{
        Scanner scan = new Scanner(new File("./d9/small2.txt"));
        tx = 0;
        ty = 0;
        visited = new HashMap<>();
        visited.put(tx + " " + ty,true);
        knots = new Coord[10];
        for(int i = 0; i < knots.length;i++){
            knots[i] = new Coord(0, 0);
        }
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            makeMove(line);
        }
        System.out.println(visited.size());
    }

    void makeMove(String s) {
        String[] spltres = s.split(" ");
        int numMoves = Integer.parseInt(spltres[1]);
        char dir = spltres[0].charAt(0);
        for(int i = 0;i < numMoves;i++){
            ArrayList<Coord> prev = new ArrayList<>();
            prev.add(new Coord(knots[0].x,knots[0].y));
            switch(dir){
                case 'R':
                    knots[0].x++;
                    break;
                case 'L':
                knots[0].x--;
                    break;
                case 'U': 
                knots[0].y++;
                    break;
                case 'D':
                knots[0].y--;
                    break;
            }
            
            for(int j = 1;j < knots.length;j++) {
                double dist = Math.sqrt(Math.pow(knots[j-1].x-knots[j].x,2) + Math.pow(knots[j-1].y-knots[j].y,2));
                prev.add(new Coord(knots[j].x,knots[j].y));
                if(dist == 2) {
                    switch(dir){
                        case 'R':
                            knots[j].x++;
                            break;
                        case 'L':
                        knots[j].x--;
                            break;
                        case 'U': 
                        knots[j].y++;
                            break;
                        case 'D':
                        knots[j].y--;
                            break;
                    }
                } else if (dist > 1.5){
                    knots[j].x = prev.get(prev.size() - 2).x;
                    knots[j].y = prev.get(prev.size() - 2).y;
                }
            }
            visited.put(knots[knots.length - 1].x + " " + knots[knots.length - 1].y,true);
        }
    }

    class Coord {
        int x;
        int y;
        
        Coord(int x,int y) {
            this.x = x;
            this.y = y;
        }
    }
}