package d1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Calories {

    public static void main(String[] args) {
        new Calories();
    }


    Calories() {
        try{
            int max = findMostCals();
            System.out.println(max);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    int findMostCals() throws IOException {
        Scanner scan = new Scanner(new File("./d1/large.txt"));
        int maxTotal = Integer.MIN_VALUE;
        ArrayList<Integer> totals = new ArrayList<>();
        while(scan.hasNextLine()) {
            int total = 0;
            String line = scan.nextLine();
            while(scan.hasNextLine() && !line.equals("")){
                total += Integer.parseInt(line);
                line = scan.nextLine();
            }
            totals.add(total);
        }
        int sum = 0;
        for(int i = 0;i < 3;i++) {
            int max = Integer.MIN_VALUE;
            int index = 0;
            int maxIndex = 0;
            for(Integer j : totals) {
                if (j > max) {
                    max = j;
                    maxIndex = index;
                }
                index++;
            }
            sum += max;
            totals.remove(maxIndex);
        }
        return sum;
    }
}
