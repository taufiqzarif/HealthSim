import java.io.*;
import java.util.*;

public class fileIO {
    
    private String name;
    private String age;
    private String status;
    private String id;

    private DSAGraph graph;

    //Cited from Lecture 1 DSA
    public DSAGraph readFile(String fileName) {
        graph = new DSAGraph();
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
        String line;

        try {
            fileStrm = new FileInputStream(fileName);
            rdr = new InputStreamReader(fileStrm);
            bufRdr = new BufferedReader(rdr);

            line = bufRdr.readLine();
            while(line!=null) {
                processLine(line);
                line = bufRdr.readLine();
            }
            fileStrm.close();
        } catch(FileNotFoundException e) {
            System.out.println(fileName + " does not exist!");
        } catch(IOException e) {
            if(fileStrm!=null) {
                try {
                    fileStrm.close();
                } catch(IOException ex2) {}
            }
            System.out.println("Error in file processing: " + e.getMessage());
        }
        return graph;
    }



    //Cited from Lecture 1 DSA
    public void saveFile(DSAGraph updatedGraph, String fileName) {
        FileOutputStream fileStrm = null;
        PrintWriter pw;
        int total = updatedGraph.totalVertex();
        DSALinkedList list = updatedGraph.vertices;
        
        System.out.println("Total: " + total);
        try {
            fileStrm = new FileOutputStream(fileName);
            pw = new PrintWriter(fileStrm);
            Iterator iter = list.iterator();
            while(iter.hasNext()) {
                DSAGraphVertex val = (DSAGraphVertex) iter.next();
                pw.println(val.getName() + "/" + val.getAge() + "/" + val.getStatusShortForm() + "/" + val.getID());
            }
            pw.close();
            fileStrm.close();
            
        } catch(Exception e) {
            throw new IllegalArgumentException("Failed to save file!");
        }

    }
    
    //Cited from Lecture 1 DSA
    public void processLine(String inLine) {
        String[] tokens = new String[4];
        tokens = inLine.split("/");
        
        name = tokens[0];
        age = tokens[1];
        status = tokens[2];
        id = tokens[3];
        graph.addVertex(name,age,status,id);
        //graph.addEdge(label1, label2);

    }
}