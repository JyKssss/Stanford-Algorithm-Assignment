package course4;

import javax.lang.model.type.ArrayType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TspHeuristic {
    /***
     * 每次寻找最近的点且未遍历过的点为加入点
     */
    static int nodeNum;
    static double totalDis=0;
    public static void main(String[] args) throws IOException {
        ArrayList<tspNode> tspNodes = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\nn.txt");
        ArrayList<tspNode> routes =new ArrayList<>();
        ArrayList<tspNode> remainNodes= (ArrayList<tspNode>)tspNodes.clone();
        ArrayList<Double> distance =new ArrayList<Double>();
        routes.add(remainNodes.get(0));
        remainNodes.remove(0);
        while (remainNodes.size()>0){
            tspNode addNode=findNearest(remainNodes, routes.get(routes.size()-1),distance);
            routes.add(addNode);
            remainNodes.remove(addNode);
        }
        for(double dis:distance){
            totalDis+=dis;
        }
        totalDis+=calDistance(routes.get(0), routes.get(routes.size()-1));
        System.out.println("启发式方法计算得到的道路长度为： "+totalDis);
    }

    private static ArrayList<tspNode> readTxt(String txtpath) throws IOException {
        FileReader fileReader = new FileReader(txtpath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<tspNode> tspNodes = new ArrayList<>();
        String line = bufferedReader.readLine();
        nodeNum = Integer.parseInt(line);
        int id = 0;
        while ((line = bufferedReader.readLine()) != null) {
            id++;
            tspNodes.add(new tspNode(id, Double.parseDouble(line.split(" ")[1]), Double.parseDouble(line.split(" ")[2])));
        }
        return tspNodes;
    }
    //找到目前最近的点以及距离更新
    private static tspNode findNearest(ArrayList<tspNode> remainNodes, tspNode tail,ArrayList<Double>distance) {
        double shortestDis = Integer.MAX_VALUE;
        tspNode nearestNode = null;
        for (tspNode node : remainNodes) {
            if (shortestDis > calDistance(node, tail)) nearestNode = node;
            shortestDis = shortestDis < calDistance(node, tail) ? shortestDis : calDistance(node, tail);
        }
        System.out.println(shortestDis);
        distance.add(shortestDis);
        return nearestNode;
    }

    private static double calDistance(tspNode node1, tspNode node2) {
        return Math.sqrt(Math.pow(node1.x - node2.x, 2) + Math.pow(node1.y - node2.y, 2));
    }

    private static class tspNode {
        int id;
        double x;
        double y;

        public tspNode(int id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }
    }
}