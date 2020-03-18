package course4;

import javax.lang.model.type.ArrayType;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TspHeuristic {
    static int nodeNum;

    public static void main(String[] args) throws IOException {
        ArrayList<tspNode> tspNodes = readTxt("C:\\Users\\Junyuan Tan\\Desktop\\stanford_alg\\nn.txt");
        ArrayList<tspNode> routes =new ArrayList<>();
        ArrayList<tspNode> remainNodes= (ArrayList<tspNode>)tspNodes.clone();
        double totalDis=0;
        routes.add(remainNodes.get(0));
        remainNodes.remove(0);
        while (remainNodes.size()>0){
            tspNode addNode=findNearest(remainNodes, routes.get(routes.size()-1),totalDis);
            routes.add(addNode);
            remainNodes.remove(addNode);
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
            tspNodes.add(new tspNode(id, Double.parseDouble(line.split(" ")[0]), Double.parseDouble(line.split(" ")[1])));
        }
        return tspNodes;
    }

    private static tspNode findNearest(ArrayList<tspNode> remainNodes, tspNode tail, double totalDis) {
        double shortestDis = Integer.MAX_VALUE;
        tspNode nearestNode = null;
        for (tspNode node : remainNodes) {
            if (shortestDis > calDistance(node, tail)) nearestNode = node;
            shortestDis = shortestDis < calDistance(node, tail) ? shortestDis : calDistance(node, tail);
        }
        totalDis+=shortestDis;
        return nearestNode;
    }

    private static double calDistance(tspNode node1, tspNode node2) {
        return Math.sqrt(Math.pow(node1.x - node2.x, 2) + Math.pow(node2.y - node2.y, 2));
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