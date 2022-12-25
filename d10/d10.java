package d10;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class CPU {
    public static void main(String[] args) {
        try {
            new CPU();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    CPU() throws IOException {
        Scanner scan = new Scanner(new File("./d10/small.txt"));
        int cycle = 0;
        int x = 1;
        ArrayList<Integer> sig = new ArrayList<>();
        while(scan.hasNextLine()) {
            String[] line = scan.nextLine().split(" ");
            if(line[0].equals("addx")){
                sig.add((cycle) * x);
                cycle++;
                sig.add((cycle) * x);
                cycle++;
                x += Integer.parseInt(line[1]);
                sig.add((cycle) *x);
                cycle++;
            } else {
                sig.add((cycle) * x);
                cycle++;
            }
        }
        if(sig.size() < 20) {
            return;
        }
        int strength = sig.get(20);
        for(int i = 40;i < sig.size();i+=40) {
            strength += sig.get(i);
        }
        System.out.println(strength);
    }
    
}
