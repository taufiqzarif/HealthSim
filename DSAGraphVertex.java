import java.util.*;

public class DSAGraphVertex {
    
    private String name;
    private String age;
    private String status;
    private String id; //Unique
    private String suscept;
    private String infected;
    private String recovered;
    private String death;
    private DSALinkedList edgeList;
    private boolean visited;

    public DSAGraphVertex(String inName, String inAge, String inStatus, String inID) {
        this.name = inName;
        this.age = inAge;
        this.status = inStatus;
        this.id = inID;
        this.edgeList = new DSALinkedList();
        visited = false;
    }

    public String getName() {
        return this.name;
    }

    public String getAge() {
        return this.age;
    }

    public String getStatus() {
        String fullStatus = "";
        if(this.status.equalsIgnoreCase("s")) {
            fullStatus = "Susceptibles";
        }
        if(this.status.equalsIgnoreCase("i")) {
            fullStatus = "Infected";
        }
        if(this.status.equalsIgnoreCase("r")) {
            fullStatus = "Recovered";
        }
        if(this.status.equalsIgnoreCase("d")) {
            fullStatus = "Deceased";
        }
        return fullStatus;
    }

    public String getStatusShortForm() {
        return this.status;
    }

    public String getID() {
        return this.id;
    }

    public String getSuscept() {
        return this.suscept;
    }

    public String getInfected() {
        return this.infected;
    }

    public String getRecovered() {
        return this.recovered;
    }

    public String getDeath() {
        return this.death;
    }

    public DSALinkedList getAdjacent() {
        return this.edgeList;
    }

    public void addEdge(DSAGraphVertex vertex) {
        edgeList.insertLast(vertex);
    }

    public void setVisited() {
        visited = true;
    }

    public void setInfected() {
        this.status = "i";
    }

    public void setDeceased() {
        this.status = "d";
    }

    public void setRecovered() {
        this.status = "r";
    }

    public void clearVisited() {
        visited = false;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public String toString() {
        Iterator iter = edgeList.iterator();
        String data = getName() + "[" + getID() + "]" + " "; //Main vertex
        
        if(edgeList.isEmpty()) {
            //throw new IllegalArgumentException("No edgeList");
        }
        else {
            while(iter.hasNext()) {
                DSAGraphVertex val = (DSAGraphVertex) iter.next();
                data = data + val.getName() + "[" + val.getID() + "]" + " "; //Connected to main vertex
            }
        }
        return data;
    }

    
}