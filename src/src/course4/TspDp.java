package course4;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TspDp {
static int nodeNum;
    @NotNull
    private static ArrayList<tspNode> readTxt(String txtpath) throws IOException {
        FileReader fileReader = new FileReader(txtpath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<tspNode> tspNodes = new ArrayList<>();
        String line = bufferedReader.readLine();
        nodeNum = Integer.parseInt(line);
        int id=0;
        while ((line =bufferedReader.readLine())!= null){
            id++;
            tspNodes.add(new tspNode(id,Double.parseDouble(line.split(" ")[0]),Double.parseDouble(line.split(" ")[1])));
        }
        return tspNodes;
    }

    private static double calDis(tspNode node1, tspNode node2){
        double distance = Math.sqrt(Math.pow((node1.x-node2.x),2)+Math.pow((node1.y-node2.y),2));
        return distance;
    }



    private static class tspNode{
        int id;
        double x;
        double y;
        public tspNode(int id, double x, double y) {
            this.id=id;
            this.x=x;
            this.y=y;
        }
    }
}

