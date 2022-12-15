package d2;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.Scanner;

class RPS {

    public static void main(String[] args) {
        new RPS();
    }


    RPS() {
        try {
            int score = calcScore();
            System.out.println(score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int calcScore() throws IOException {
        Scanner scan = new Scanner(new File("./d2/small.txt"));
        int total = 0;
        while(scan.hasNextLine()) {
            String line = scan.nextLine();
            int score = calcRoundScore(line);
            total += score;
        }
        return total;
    }

    int calcRoundScore(String line) {
        char opp = line.split(" ")[0].charAt(0);
        char ply = getMove(opp,line.split(" ")[1].charAt(0));
        int score = 0;
        if(ply == 'A'){ 
            score += 1; 
            ply = 'A';
        } //rock
        if(ply == 'B'){ 
            score += 2; 
            ply = 'B';
        }//paper
        if(ply == 'C'){ 
            score += 3; 
            ply = 'C';
        }//scissors
        score += outcome(opp,ply);
        return score;
    }

    int outcome(char opp,char ply) {
        if(opp == ply) {
            return 3;
        }
        if((opp == 'A' && ply == 'B') || (opp == 'B' && ply == 'C') || (opp == 'C' && ply == 'A')) return 6;
        return 0;
    }

    char getMove(char opp, char out) {
        if(out == 'Y') {
            return opp;
        }
        if(out == 'X') {
            if(opp == 'A') {
                return 'C';
            }
            if(opp == 'B') {
                return 'A';
            }
            if(opp == 'C') {
                return 'B';
            }
        }
        if(opp == 'A') {
            return 'B';
        }
        if(opp == 'B') {
            return 'C';
        }
        if(opp == 'C') {
            return 'A';
        }
        return 'A';
        
    }
}
