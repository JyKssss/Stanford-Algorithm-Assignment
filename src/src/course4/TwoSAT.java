package course4;

import javax.xml.ws.soap.Addressing;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class TwoSAT {
    static int nodeNum;
    static ArrayList<node> nodes =new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ArrayList<clause>clauses=readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\2sat3.txt");
        boolean allPositive=false;
        for (int i = 0; i < nodeNum; i++) {
            ranInitialSolution(nodes);
            for (int j = 0; j < nodeNum*nodeNum*2; j++) {
                allPositive=verifyAndModifyClauses(clauses);
                if (allPositive)break;
            }
            if (allPositive)break;
        }

        System.out.println("是否存在特定的解: "+allPositive);
    }

    private static ArrayList<clause> readTxt(String txtpath) throws IOException {
        FileReader fileReader=new FileReader(txtpath);
        BufferedReader bufferedReader=new BufferedReader(fileReader);
        ArrayList<clause> clauses=new ArrayList<>();
//        ArrayList<node> nodes=new ArrayList<>();
        nodeNum=Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < nodeNum; i++) {
            nodes.add(new node(i+1));
        }
        String line;
        while ((line=bufferedReader.readLine())!=null){
            int node1,node2,n1=1,n2=1;
            node1=Integer.parseInt(line.split(" ")[0]);
            node2=Integer.parseInt(line.split(" ")[1]);
            if (node1<0){
                node1=-node1;
                n1=0;
            }
            if (node2<0){
                node2=-node2;
                n2=0;
            }
            clauses.add(new clause(nodes.get(node1-1), nodes.get(node2-1), n1, n2));
        }
        return clauses;
    }

    private static void ranInitialSolution(ArrayList<node>nodes){
        Random random=new Random(System.currentTimeMillis()%100000);
        for(node node:nodes){
            node.positive=random.nextBoolean();
        }
    }

    private static boolean verifyAndModifyClauses(ArrayList<clause> clauses){
        ArrayList<clause>negativeClauses=new ArrayList<>();
        Random random=new Random();
        boolean allPositive=true;
        for(clause clause:clauses){
            if (!clause.getPositive()){
                allPositive=false;
                negativeClauses.add(clause);
            }
        }
        if (!allPositive){
            clause ranClause= negativeClauses.get(random.nextInt(negativeClauses.size()));
            if (random.nextInt(2)==1){
                ranClause.node1.positive=!(ranClause.node1.positive);
            }
            else{
                ranClause.node2.positive=!(ranClause.node2.positive);
            }
        }
        return allPositive;
    }

    private static class node{
        int id;
        boolean positive;

        public node(int id) {
            this.id = id;
            this.positive=true;
        }
    }

    private static class clause{
        node node1;
        node node2;
        int n1;
        int n2;


        public clause(node node1, node node2, int n1,int n2) {
            this.node1 = node1;
            this.node2 = node2;
            this.n1=n1;
            this.n2=n2;
        }
        public boolean getPositive(){
            if (n1==1){
                if (n2==1)return node1.positive||node2.positive;
                else return node1.positive||!(node2.positive);
            }
            else {
                if (n2==1)return !(node1.positive)||node2.positive;
                else return !(node1.positive)||!(node2.positive);
            }
        }
    }
}
