package d7;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class FileSystem{
    public static void main(String[] args) {
        try{
            new FileSystem();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    FNode root;

    long spaceNeeded;

    FileSystem() throws IOException{
        Scanner scan = new Scanner(new File("./d7/large.txt"));
        root = new FNode("/");
        FNode curNode = root;
        String line = scan.nextLine();
        while(scan.hasNextLine()){
            line = scan.nextLine();
            if(line.equals("$ ls")){
                line = scan.nextLine();
                while(line.charAt(0) != '$'){
                    String fileName = line.split(" ")[1];
                    if(line.substring(0,3).equals("dir")){
                        curNode.addChild(new FNode(fileName));
                    } else {
                        String[] sArr = line.split(" ");
                        long fSize = Long.parseLong(sArr[0]);
                        String fName = sArr[1];
                        curNode.addChild(new FNode(fName,fSize));
                    }
                    if(!scan.hasNextLine()) {
                        break;
                    }
                    line = scan.nextLine();
                }
            }
            if(line.contains("cd")){
                if(line.split(" ")[2].equals("..")){
                    curNode = curNode.parent;
                } else {
                    curNode = curNode.getChild(line.split(" ")[2]);
                }
            }
        }
        calcSize();
        System.out.println(getSmallestClosest().size);
    }

    FNode getSmallestClosest() {
        return getSmallestClosest(root);
    }

    FNode getSmallestClosest(FNode n){
        if(!n.isDir) {
            return null;
        }
        FNode closest = new FNode("t",Long.MAX_VALUE);
        for(FNode c : n.children) {
            FNode res = getSmallestClosest(c);
            if(res == null) {
                continue;
            }
            if(res.size < closest.size && res.size > spaceNeeded) {
                closest = res;
            } 
        }
        if(n.size < closest.size && n.size > spaceNeeded) {
            return n;
        }
        return closest;
    }

    long numOver() {
        return numOver(root);
    }

    long numOver(FNode n) {
        if(!n.isDir) {
            return 0;
        }
        long sum = 0;
        for(FNode c : n.children) {
            sum += numOver(c);
        }
        if(n.size <= 100000) {
            return sum + n.size;
        }
        return sum;
    }

    void calcSize() {
        long size = calcSize(root);
        spaceNeeded = 30000000 - (70000000 - size);
    }

    long calcSize(FNode n) {
        if(!n.isDir){
            return n.size;
        }
        long total = 0;
        for(FNode c : n.children) {
            total += calcSize(c);
        }
        n.size = total;
        return total;
    }

    void print() {
        print(root);

    }

    void print(FNode n) {
        System.out.println(n.name + " Size: " + n.size);
        for(FNode c : n.children) {
            print(c);
        }
    }

    class FNode {
        boolean isDir;
        String name;
        long size;
        ArrayList<FNode> children;
        FNode parent;

        FNode(String name,long size) {
            isDir = false;
            this.name = name;
            this.size = size;
            children = new ArrayList<>();
        }

        FNode(String name) {
            this.name = name;
            isDir = true;
            children = new ArrayList<>();
        }

        boolean addChild(FNode n) {
            if(!contains(n.name)){
                n.parent = this;
                children.add(n);
                return true;
            }
            return false;
        }

        boolean contains(String s) {
            for(FNode n : children) {
                if(n.name.equals(s)) {
                    return true;
                }
            }
            return false;
        }

        FNode getChild(String fName) {
            for(FNode n : children) {
                if(n.name.equals(fName)) {
                    return n;
                }
            }
            return null;
        }
    }


}