package d11;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class KeepAway {
    public static void main(String[] args) {
        try {
            new KeepAway();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    ArrayList<Monkey> monkeys;

    KeepAway() throws IOException {
        Scanner scan = new Scanner(new File("./d11/small.txt"));
        monkeys = new ArrayList<>();
        while(scan.hasNextLine()) {
            scan.nextLine();
            String[] items = scan.nextLine().split(": ")[1].split(", ");
            LinkedList<Long> itemList = new LinkedList<Long>();
            for(String s : items) {
                itemList.add(Long.parseLong(s));
            }
            String op = scan.nextLine().split(" = ")[1];
            String d = scan.nextLine().split("by ")[1];
            int divisBy = Integer.parseInt(d);
            d = scan.nextLine().split(" ")[9];
            int trueMonk = Integer.parseInt(d);
            d = scan.nextLine().split(" ")[9];
            int falseMonk = Integer.parseInt(d);
            monkeys.add(new Monkey(itemList, op, divisBy, trueMonk, falseMonk));
            if(scan.hasNextLine()) {
                scan.nextLine();
            }
        }
        long numRounds = 9999;
        for(long i = 0;i < numRounds;i++) {
            for(Monkey m : monkeys) {
                while(!m.items.isEmpty()){
                    test(m);
                }
            }
        }
        ArrayList<Long> inspected = new ArrayList<>();
        for(Monkey m : monkeys) {
            inspected.add(m.numInspections);
        }
        int[] top = new int[2];
        long[] topVals = new long[2];
        for(int i = 0;i < 2;i++) {
            int topIndex = 0;
            long topVal = Integer.MIN_VALUE;
            for(int j = 0;j < monkeys.size();j++) {
                if(monkeys.get(j).numInspections > topVal) {
                    topVal = monkeys.get(j).numInspections;
                    topIndex = j;
                }
            }
            monkeys.remove(topIndex);
            top[i] = topIndex;
            topVals[i] = topVal;
        }
        System.out.println(top[0] + ", " + topVals[0] + ", " + top[1] + ", " + topVals[1]);
        System.out.println(topVals[0] * topVals[1]);
    }

    void test(Monkey m) {
        m.inspect(0);
        long item = m.items.removeFirst();
        if(item % m.divisBy == 0) {
            monkeys.get(m.trueMonkey).items.add(item);
        } else {
            monkeys.get(m.falseMonkey).items.add(item);
        }
    }

    class Monkey {
        LinkedList<Long> items;
        String operation;
        int divisBy;
        int trueMonkey;
        int falseMonkey;
        long numInspections;
        
        Monkey(LinkedList<Long> items,String op,int divis,int trueMonk,int falseMonk) {
            operation = op;
            divisBy = divis;
            trueMonkey = trueMonk;
            falseMonkey = falseMonk;
            this.items = items;
            numInspections = 0;
        }

        void inspect(int item) {
            numInspections++;
            long a = 0;
            long b = 0;
            String[] op = operation.split(" ");
            if(Character.isAlphabetic(op[0].charAt(0))){
                a = items.peek();
            } else {
                a = Integer.parseInt(op[0]);
            }
            if(Character.isAlphabetic(op[2].charAt(0))){
                b = items.peek();
            } else {
                b = Integer.parseInt(op[2]);
            }
            switch(op[1]) {
                case "*":
                    items.removeFirst();
                    items.add(item,(a*b)/3);
                    break;
                case "+":
                    items.removeFirst();
                    items.add(item,(a+b)/3);
                    break;
            }
        }
    }
    
}
