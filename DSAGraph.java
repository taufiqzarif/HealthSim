import java.io.Serializable;
import java.util.*;
import java.io.*;


public class DSAGraph {

    DSALinkedList vertices;
    private int countVertex = 0;
    private int countEdge = 0;

    private int totalSuscep = 0;
    private int totalInfected = 0;
    private int totalRecovered = 0;
    private int totalDeath = 0;
    private int simlength = 0;
    
    private double transmission_rate = 0.0;
    private double recover_rate = 0.0;
    private double death_rate = 0.0;

    private int choice = 0;

    private double min = 0;
    private double max = 1;
    private int countWeek = 0;
    

    public DSAGraph() {
        vertices = new DSALinkedList();
    }

    public void addVertex(String name, String age, String status, String id) {
        DSAGraphVertex vertex = null;

        if(!hasVertex(id)) {
                vertex = new DSAGraphVertex(name, age, status, id);
                vertices.insertLast(vertex);
                if(status.equalsIgnoreCase("s")) {
                    totalSuscep++;
                }
                else if(status.equalsIgnoreCase("i")) {
                    totalInfected++;
                }
                else if(status.equalsIgnoreCase("r")) {
                    totalRecovered++;
                }
                else if(status.equalsIgnoreCase("d")) {
                    totalDeath++;
                }
                //System.out.println("Adding Vertex: " + name + "\nID: " + id); //Check if added
                countVertex++;
        }
        else {
            //System.out.println("Skipping add Vertex: " + name + "\nID: " + id);
        }
        
    }

    public void deleteVertex(String id) {
        DSAGraphVertex vertex = null;
        
        if(hasVertex(id)) {
            vertex = getVertex(id);
            vertices.remove(vertex);
            
            //Minus status count
            if(vertex.getStatus().equalsIgnoreCase("s")) {
                totalSuscep--;
            }
            if(vertex.getStatus().equalsIgnoreCase("i")) {
                totalInfected--;
            }
            if(vertex.getStatus().equalsIgnoreCase("r")) {
                totalRecovered--;
            }
            if(vertex.getStatus().equalsIgnoreCase("i")) {
                totalDeath--;
            }

            //Remove edgelist
            Iterator iter = vertices.iterator();
            while(iter.hasNext()) {
                DSAGraphVertex temp = (DSAGraphVertex) iter.next();
                DSALinkedList eList = temp.getAdjacent();
                eList.remove(vertex);
            }
            countVertex--; //Minus count vertex
        }
        else {
            throw new IllegalArgumentException("ID: " + id + " does not exist!");
        }
    }

    public void addEdge(String id1, String id2) {
        DSAGraphVertex v1 = null;
        DSAGraphVertex v2 = null;

        v1 = getVertex(id1);
        v2 = getVertex(id2);

       
            if(v1 == v2) {
                System.out.println("Error! Vertex are duplicated!");
            }
            if(v1!=v2 && (hasVertex(v1.getID())) && (hasVertex(v2.getID())) && (!isAdjacent(id1, id2))) {
                v1.addEdge(v2);
                v2.addEdge(v1);
                System.out.println("Vertex ID " + id1 + " successfully connected with Vertex ID " + id2);
                countEdge++;
            }
            else {
                System.out.println("Skipping add edge(Edge already connected!):\n" + "ID1: " + id1 + "\nID2: " + id2);
            }
        
    }

    public void deleteEdge(String id1, String id2) {
        DSAGraphVertex v1 = null;
        DSAGraphVertex v2 = null;

        v1 = getVertex(id1);
        v2 = getVertex(id2);

        DSALinkedList list1 = null;
        DSALinkedList list2 = null;

        list1 = v1.getAdjacent();
        list2 = v2.getAdjacent();

        if(v1 == v2) {
            System.out.println("Error! Vertex are duplicated!");
        }
        else if((!hasVertex(v1.getID())) && (!hasVertex(v2.getID()))) { 
            System.out.println("A/Both Vertex does not exist!");
        }
        else if(isAdjacent(id1, id2)) {
            list1.remove(v1);
            list2.remove(v2);
            countEdge--;
        }
        else {
            throw new IllegalArgumentException("Vertex " + id1 + " and " + id2 +  " does not connected to each other!");
        }
    }

    public boolean hasVertex(String inID) {
        Iterator iter = vertices.iterator();
        boolean check = false;

        while(iter.hasNext()) {
            DSAGraphVertex temp = (DSAGraphVertex) iter.next();
            if(temp.getID().equals(inID)) {
                check = true;
            }
        }

        return check;
    }

    public boolean hasVertexName(String name) {
        Iterator iter = vertices.iterator();
        boolean check = false;
        while(iter.hasNext()) {
            DSAGraphVertex temp = (DSAGraphVertex) iter.next();
            if(temp.getName().equalsIgnoreCase(name)) {
                check = true;
            }
        }

        return check;
    }

    public int getVertexCount() {
        return countVertex;
    }

    public int getEdgeCount(String label) {
        return countEdge;
    }

    public DSAGraphVertex getVertex(String inID) { // Return total of Vertex that added
        DSAGraphVertex vertex = null;
        Iterator iter = vertices.iterator();
        
        while(iter.hasNext()) {
            DSAGraphVertex temp = (DSAGraphVertex) iter.next();
            if(temp.getID().equals(inID)) {
                vertex = temp;
            }
        }
        return vertex;
    }

    public DSAGraphVertex getVertexName(String name) { // Return total of Vertex that added
        DSAGraphVertex vertex = null;
        Iterator iter = vertices.iterator();
        
        while(iter.hasNext()) {
            DSAGraphVertex temp = (DSAGraphVertex) iter.next();
            if(temp.getName().equals(name)) {
                vertex = temp;
            }
        }
        return vertex;
    }

    public DSALinkedList getAdjacent(String inID) {
        DSALinkedList adjList = null;
        DSAGraphVertex vertex = null;

        vertex = getVertex(inID);
        System.out.println("HERE CHECK VERTEX\n" + vertex);
        if(hasVertex(inID)) {
            adjList = vertex.getAdjacent();
        }
        else {
            throw new IllegalArgumentException("Vertex ID: " + inID + " does not exist!");
        }
     
        return adjList;
    }

    public boolean isAdjacent(String inID1, String inID2) {
        boolean check = false;
        DSAGraphVertex v1 = null;
        DSAGraphVertex v2 = null;

        v1 = getVertex(inID1);
        v2 = getVertex(inID2);

        DSALinkedList edgeList = v1.getAdjacent();
        
        Iterator iter = edgeList.iterator();
        
        while(iter.hasNext()) {
            DSAGraphVertex temp = (DSAGraphVertex) iter.next();
            if(v2.equals(temp)) {
                check = true;
            }
        }

        return check;
    }

    public boolean isInfected(String infectedID) {
        boolean check = false;
        DSAGraphVertex temp = null;
        temp = getVertex(infectedID);
        
        if(!hasVertex(infectedID)) {
            System.out.println("Vertex ID: " + infectedID + " does not exist!");
        }
        
        if(hasVertex(infectedID) && temp.getStatus().equalsIgnoreCase("infected")) {
            check = true;
        }
        return check;
    }

    public void setNewInfection(String infectedID) {
        DSAGraphVertex temp = null;
        temp = getVertex(infectedID);

        if(!hasVertex(infectedID)) {
            System.out.println("Vertex ID" + infectedID + " does not exist!");
        }
        else if(temp.getStatus().equalsIgnoreCase("Recovered")) {
            System.out.println("This person already recovered! (Immune to the virus)");
        }
        else if(!isInfected(infectedID)) {
            temp.setInfected();
            totalInfected++;
            totalSuscep--;
            System.out.println("Status Vertex ID: " + infectedID + " has set to: " + temp.getStatus());
        }
        else if(isInfected(infectedID)) {
            System.out.println("This person already infected!");
        }
    }


    public void setTrate(double Trate) {
        transmission_rate = Trate;
    }

    public void setRrate(double Rrate) {
        recover_rate = Rrate;
    }

    public void setDrate(double Drate) {
        death_rate = Drate;
    }

    public void setChoice(int inChoice) {
        choice = inChoice;
    }

    public DSALinkedList getVertices() {
        return vertices;
    }

    public int getTotalInfected() {
        return this.totalInfected;
    }

    public int totalVertex() {
        return this.countVertex;
    }


    public void startIntervention() {
        countWeek++;
        String newInfectedResults = "";
        String newDeathResults = "";
        
        Iterator iter = vertices.iterator();
        while(iter.hasNext()) {
            DSAGraphVertex people = (DSAGraphVertex) iter.next();
            
            //Transmission Rate and Death rate
            if(people.getStatus().equalsIgnoreCase("Infected")) {
                
                //Start Death rate to existing infected people
                Random randDeath = new Random();
                Random randRecover = new Random();
                double randomToDie = min + (max - min) * randDeath.nextDouble();
                double randomToRecover = min + (max-min) * randRecover.nextDouble();
                
                if(randomToDie > randomToRecover) {
                    if(death_rate == 1) {
                        people.setDeceased();
                        totalDeath++;
                        totalInfected--;
                    }
                    else if(death_rate == 0) {
                        
                    }
                    else if(randomToDie <= death_rate) {
                        people.setDeceased();
                        totalDeath++;
                        totalInfected--;
                    }
                }
                else if(randomToRecover > randomToDie) {
                    if(recover_rate == 1) {
                        people.setRecovered();
                        totalInfected--;
                        totalRecovered++;
                    }
                    else if(recover_rate == 0) {
                        
                    }
                    else if(randomToRecover <= recover_rate) {
                        people.setRecovered();
                        totalInfected--;
                        totalRecovered++;
                    }
                }


                //Start Transmission rate to new victim(susceptibles)
                DSALinkedList edgeList = people.getAdjacent();
                
                Iterator iter2 = edgeList.iterator();
                while(iter2.hasNext()) {
                    //System.out.println("CHECK EDGELIST: " + edgeList.toString());
                    DSAGraphVertex victim = (DSAGraphVertex) iter2.next();
                    Random r = new Random();
                    double randomToInfect = min + (max - min) * r.nextDouble();
                    

                    if(victim.getStatus().equalsIgnoreCase("susceptibles")) {

                       if(transmission_rate == 1) { // 100% Will get infected to whoever Suscept meet with Infected people
                            
                            setNewInfection(victim.getID());
                            
                        }
                        else if(transmission_rate == 0) { // Any suscept will never going to get infected even meet with Infected people
                            
                            
                        }
                        else if(randomToInfect <= transmission_rate) { // The rate of the suscept to get infected depends on how many the user entered/or by default then compare with randomToInfect
                            
                            setNewInfection(victim.getID());     
                
                        }
                    }
                }
            }
            
         
        }
        System.out.println(displayStatistics());
    
    }



    public String displayAsList() {
        String adj = "Adj List: \n";
        String data = "";
        String full = "";
        DSAGraphVertex temp = null;

        if(vertices.isEmpty()) {
            Iterator iter = vertices.iterator();
            //data = "null";
            while(iter.hasNext()) {
                temp = (DSAGraphVertex) iter.next();
                data = data + temp.toString() + "\n";
                
            }
        }
        else {
            Iterator iter = vertices.iterator();
            while(iter.hasNext()) {
                temp = (DSAGraphVertex) iter.next();
                data = data + temp.toString() + "\n";
            }
        }
        full = adj + data;
        return full;
    }

    public String displayAsMatrix() {
        String adjMatrix = "Adj Matrix: \n";
        String data = "";
        String result = "";
        DSAGraphVertex temp = null;
        DSAGraphVertex temp2 = null;
        DSAGraphVertex compareTemp = null;

        if(vertices.isEmpty()) {
            data = "null";
        }
        else {
            Iterator iterFirst = vertices.iterator();
            while(iterFirst.hasNext()) {
                temp = (DSAGraphVertex) iterFirst.next();
                data = data + "\t" + temp.getName().charAt(0) + "[" + temp.getID() + "]";
            }
            data = data + "\n";
            Iterator iterSec = vertices.iterator();
            while(iterSec.hasNext()) {
                temp2 = (DSAGraphVertex) iterSec.next(); 
                data = data + temp2.getName().charAt(0) + "[" + temp2.getID() + "]" + " ";
                Iterator iterCheck = vertices.iterator();
                while(iterCheck.hasNext()) {
                    compareTemp = (DSAGraphVertex) iterCheck.next();
                    if(isAdjacent(temp2.getID(), compareTemp.getID())) {
                        data = data + ("\t1");
                    }
                    else {
                        data = data + ("\t0");
                    }
                }
                data = data + "\n";
            }
        }
        result = (adjMatrix + data);
        return result;
    }

    public String displayStatistics() {
        String title = "Update Week: "+ countWeek + "\nStatistics: \n";
        String result = "";
        String dataSuscept = "\n===================================\nSusceptibles: \n\n";
        String dataInfected = "\n===================================\nInfected: \n\n";
        String dataRecovered = "\n===================================\nRecovered: \n\n";
        String dataDeceased = "\n===================================\nDeceased: \n\n";
        DSAGraphVertex temp = null;

        if(vertices.isEmpty()) {
            System.out.println("null");
        }
        else {
            Iterator iter = vertices.iterator();
            while(iter.hasNext()) {
                temp = (DSAGraphVertex) iter.next();
                if(temp.getStatus().equalsIgnoreCase("Susceptibles")) {
          
                    dataSuscept = dataSuscept + "Name: " + temp.getName() + "\n" + "Age: " + temp.getAge() + "\n" + "ID: " + temp.getID() + "\n\n" ;
                }
                else if(temp.getStatus().equalsIgnoreCase("Recovered")) {
                  
                    dataRecovered = dataRecovered + "Name: " + temp.getName() + "\n" + "Age: " + temp.getAge() + "\n" + "ID: " + temp.getID() + "\n\n";
                }
                else if(temp.getStatus().equalsIgnoreCase("Infected")) {
                  
                    dataInfected = dataInfected + "Name: " + temp.getName() + "\n" + "Age: " + temp.getAge() + "\n" + "ID: " + temp.getID() + "\n\n";
                }
                else if(temp.getStatus().equalsIgnoreCase("Deceased")) {
                  
                    dataDeceased = dataDeceased + "Name: " + temp.getName() + "\n" + "Age: " + temp.getAge() + "\n" + "ID: " + temp.getID() + "\n\n";
                }
            }
        }
        result = result + title + dataSuscept + "\n===================================\n" + dataRecovered + "\n===================================\n" + dataInfected + "\n===================================\n" + dataDeceased + "\n===================================\n" + ("\nTotal Susceptible: " + totalSuscep + "\nTotal Recovered: " + totalRecovered + "\nTotal Infected: " + totalInfected + "\nTotal Deceased: " + totalDeath);
        return result;
    }
    
}
