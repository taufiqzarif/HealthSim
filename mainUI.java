import java.text.*;
import java.util.*;

public class mainUI {

    DSAGraph graph = new DSAGraph();
    fileIO io = new fileIO();

    private double transmission_rate = 0;
    private double recover_rate = 0;
    private double death_rate = 0;


    private int intervChoice = 0;
    private boolean hasRates = false;

    private int countWeek = 0;

    public void displayUI() {
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {

            System.out.println("Menu: [Select 1 - 9]");
            System.out.println("(1) Load network");
            System.out.println("(2) Set rates/interventions");
            System.out.println("(3) Node operations (find, insert, delete)");
            System.out.println("(4) Edge operations (like/follow - add, remove)");
            System.out.println("(5) New infection");
            System.out.println("(6) Display network");
            System.out.println("(7) Display statistics");
            System.out.println("(8) Update (run a timestep)");
            System.out.println("(9) Save network");
            System.out.println("(0) Exit");

            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    //Load network from file
                    loadNetwork(); //Read from file [DONE] || Write file [X]
                    break;
                
                case 2:
                    //Set rates/interventions
                    setRates();
                    break;
                
                case 3:
                    //Node operations | InsertV[DONE] || DeleteV[DONE] | InsertE[DONE] || DeleteE[DONE]
                    nodeOp();
                    break;

                case 4:
                    //Edge operations
                    edgeOp();
                    break;

                case 5:
                    //New infection
                    newInfection();
                    break;

                case 6:
                    //Display network
                    displayNetwork(); //DONE
                    break; 

                case 7:
                    //Display statistics
                    displayStats();
                    break;

                case 8:
                    //Update timestep
                    update();
                    break;

                case 9:
                    //Save network
                    saveNetwork();
                    break;
                
                case 0:
                    System.exit(1);
                    break;
            }
        }while(choice!=0);
    }

    public void loadNetwork() {
        Scanner sc = new Scanner(System.in);
        String fileName;

        System.out.println("Enter file name:");
        fileName = sc.next();
        graph = io.readFile(fileName);
        System.out.println(graph.getVertexCount() + " people loaded!");
        //System.out.println(graph.displayAsList());
    }

    public void displayNetwork() {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Choose type of display: [Select 1-3]");
            System.out.println("(1) Display as list");
            System.out.println("(2) Display as matrix");
            System.out.println("(3) Back");
        
            choice = sc.nextInt();
            
            switch(choice) {
                case 1:
                    System.out.println(graph.displayAsList());
                    break;

                case 2:
                    System.out.println(graph.displayAsMatrix());
                    break;
                
                case 3:
                    displayUI();
                    break;

                default:
                    System.out.println("Please select 1 to 3 only!");
            }
        
        }while(choice!=3);
    }

    public void setRates() {
        Scanner sc = new Scanner(System.in);
        int choice;
        
        do {
            System.out.println("(1) Default Set Rates");
            System.out.println("(2) Custom Set Rates");
            System.out.println("(3) Back");
            choice = sc.nextInt();

        }while(choice!=3 && choice==1 && choice==2);
        
        if(choice==3) {
            displayUI();
        }
        else if(choice==1) {
            transmission_rate = 0.20;
            recover_rate = 0.50;
            death_rate = 0.30;

            System.out.println("Transmission Rate: " + transmission_rate);
            System.out.println("Recover Rate: " + recover_rate);
            System.out.println("Death Rate: " + death_rate);

            graph.setTrate(transmission_rate);
            graph.setRrate(recover_rate);
            graph.setDrate(death_rate);
        }
        else if(choice==2) {
            DecimalFormat df = new DecimalFormat("#.##");
            
            do {
                System.out.println("Transmission Rate: ");
                System.out.print("Set rates [0.00 - 1.00]");
                transmission_rate = sc.nextDouble();
                
                if(transmission_rate<0.00 || transmission_rate>1.00) {
                    System.out.println("Error! Please enter valid rate!");
                }
            }while((transmission_rate<0) || (transmission_rate>1));
            
            String sTR = df.format(transmission_rate);
            double dTR = Double.parseDouble(sTR);
            System.out.println(dTR);
            graph.setTrate(dTR);

            do {
                System.out.println("Recovery Rate: ");
                System.out.print("Set rates [0.00 - 1.00]");
                recover_rate = sc.nextDouble();
                
                if(recover_rate<0.00 || recover_rate>1.00) {
                    System.out.println("Error! Please enter valid rate!");
                }
            }while((recover_rate<0) || (recover_rate>1));
            
            String sRR = df.format(recover_rate);
            double dRR = Double.parseDouble(sRR);
            System.out.println(dRR);
            graph.setRrate(dRR);

            do {
                System.out.println("Death Rate: ");
                System.out.print("Set rates [0.00 - 1.00]");
                death_rate = sc.nextDouble();
                
                if(death_rate<0.00 || death_rate>1.00) {
                    System.out.println("Error! Please enter valid rate!");
                }
            }while((death_rate<0) || (death_rate>1));
            
            String sDR = df.format(death_rate);
            double dDR = Double.parseDouble(sDR);
            System.out.println(dDR);
            graph.setDrate(dDR);
            
        }
        hasRates = true;
    }

    public void nodeOp() { 
        Scanner sc = new Scanner(System.in);
        
        int choice;

        do {
            System.out.println("Node/Vertex Operations:");
            System.out.println("(1) Find");
            System.out.println("(2) Insert");
            System.out.println("(3) Delete");
            System.out.println("(4) Back");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    String name;
                    System.out.println("Find Vertex:");
                    DSALinkedList vertices = graph.getVertices();
                    int count = 0;
                    String data = "Vertex: ";
                    System.out.println("Vertex Name:");
                    name = sc.next();
                   
                    if(graph.hasVertexName(name)) { 
                        //System.out.println("ToString Vertices: " + vertices.toString());
                        Iterator iter = vertices.iterator(); 
                        while(iter.hasNext()) {
                            DSAGraphVertex temp = (DSAGraphVertex) iter.next();
                            if(temp.getName().equalsIgnoreCase(name)) {
                                count++;
                                data = data + "\n===================================\n" + "Name: " + temp.getName() + "\n" + "Age: " + temp.getAge() + "\n" + "Status: " + temp.getStatus() + "\n" + "ID: " + temp.getID() + "\n===================================\n";
                                temp = graph.getVertexName(name);
                            }
                        }
                        System.out.println("Results: " + count + " name(s) found");
                        System.out.println(data);
                    } else {
                        System.out.println(name + " does not exist!");
                    }
                    break;

                case 2: //Insert new vertex(name,age,status,id)
                    String inName, inAge, inStatus, inID;
                    System.out.println("Syntax: Name, Age, Status(s|r|i), ID");
                    
                    System.out.print("Name: ");
                    inName = sc.next();

                    if(isEmpty(inName)) {
                        while(isEmpty(inName)) {
                            System.out.println("Do not leave it blank!");
                            inName = sc.next();
                        }
                    }
                    else if(inName.length() < 3) {
                        while(inName.length() < 3) {
                            System.out.println("Invalid characters length!");
                            inName = sc.next();
                        }
                    }
                    
                    System.out.print("Age: ");
                    inAge = sc.next();
                    int parseIntAge = Integer.parseInt(inAge);
                    if(parseIntAge < 1 || parseIntAge > 140) {
                        while(parseIntAge<=1 || parseIntAge >=140) {
                            System.out.println("Invalid age!");
                            parseIntAge = sc.nextInt();
                        }
                    }

                    System.out.print("Status: [s|i|r] ");
                    inStatus = sc.next();
                    
                    if(inStatus != "s" || inStatus != "i" || inStatus != "r") {
                        while(inStatus == "s" || inStatus == "i" || inStatus == "r") {
                            System.out.println("Invalid status!");
                            inStatus = sc.next();
                        }
                    }

                    System.out.println("ID: ");
                    inID = sc.next();

                    if(isEmpty(inID)) {
                        while(isEmpty(inID)) {
                            System.out.println("Invalid ID!");
                            inID = sc.next();
                        }
                    }
                    if(graph.hasVertex(inID)) {
                        while(graph.hasVertex(inID)) {
                            System.out.println("ID already exist!");
                            inID = sc.next();
                        }
                    }

                    //Add vertex
                    graph.addVertex(inName, inAge, inStatus, inID);

                    break;

                case 3: //Delete
                    String deleteVertexID;
                    System.out.println("Delete Vertex using ID:");
                    deleteVertexID = sc.next();
                    graph.deleteVertex(deleteVertexID);
                    break;

                case 4: //Back
                    displayUI();
                    break;
                
                default:
                    System.out.println("Select 1-4 only");
                    break;
            }

        }while(choice!=4);
    }


    public void edgeOp() { //Add or remove edge
        Scanner sc = new Scanner(System.in);
        System.out.println("Edge Operations:\n");
        int choice;

        do {
            System.out.println("(1) Add Edge");
            System.out.println("(2) Remove Edge");
            System.out.println("(3) Back");
            choice = sc.nextInt();

            switch(choice) {
                case 1:
                    String id1, id2;
                    System.out.println("Add Edge: Enter exist Vertex only");
                    System.out.print("Vertex 1 ID [Integer only]: ");
                    id1 = sc.next();
                    System.out.print("Vertex 2 ID [Integer only]: ");
                    id2 = sc.next();

                    if(!graph.hasVertex(id1)) {
                        System.out.println("Vertex ID: " + id1 + " does not exist!");
                    }
                    if(!graph.hasVertex(id2)) {
                        System.out.println("Vertex ID:" + id2 + " does not exist!");
                    }
                    
                    if(graph.hasVertex(id1) && graph.hasVertex(id2)) {
                        graph.addEdge(id1, id2);
                        
                    }
                    break;

                case 2:
                    String delID1, delID2;
                    System.out.println("Remove edge: Enter exist edge only");
                    System.out.println("Vertex 1 ID [Integer only]: ");
                    delID1 = sc.next();
                    System.out.println("Vertex 2 ID [Integer only]:");
                    delID2 = sc.next();

                    if(!graph.hasVertex(delID1)) {
                        System.out.println("Vertex ID: " + delID1 + " does not exist!");
                    }
                    if(!graph.hasVertex(delID2)) {
                        System.out.println("Vertex ID:" + delID2 + " does not exist!");
                    }

                    if(graph.hasVertex(delID1) && graph.hasVertex(delID2)) {
                        graph.deleteEdge(delID1, delID2);
                        System.out.println("Edge successfully deleted between Vertex ID  " + delID1 + " and Vertex ID " + delID2);
                    }
                    break;

                case 3:
                    displayUI();
                    break;

                default:
                    System.out.println("Select 1-3 only");
                    break;
            }

        }while(choice!=3);

    }


    public void newInfection() { //Set the existing vertex to infected(if is not already infected)
        Scanner sc = new Scanner(System.in);
        DSAGraphVertex temp = null;
        String infectedID;
        int choice;

        do {
            System.out.println("(1) Set New Infection");
            System.out.println("(2) Back");
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    System.out.print("Set new infected Vertex ID: ");
                    infectedID = sc.next();
                    temp = graph.getVertex(infectedID);
                    int parseInfectedID = Integer.parseInt(infectedID);
                    if(parseInfectedID<0) {
                        while(parseInfectedID<0) {
                            System.out.println("Invalid ID!");
                            parseInfectedID = sc.nextInt();
                        }
                    }
                    String checkedinfectedID = Integer.toString(parseInfectedID);
                    graph.setNewInfection(checkedinfectedID);
                    break;

                case 2:
                    displayUI();
                    break;

                default:
                    System.out.println("Select 1-2 only");
                    break;
            }
        }while(choice!=2);
    }

    //Cited from https://www.programiz.com/java-programming/examples/string-empty-null
    public boolean isEmpty(String str) {
        boolean check = true;
        if((str != null) && (!str.equals(" "))) {
            check = false;
        }
        return check;
    }

    public void displayStats() {
        System.out.println(graph.displayStatistics());
    }

    public void update() {
        
        if(!hasRates) {
            setRates();
            graph.startIntervention();
            countWeek++;
            System.out.println("Update Week: " + countWeek);
        }
        else{
            graph.startIntervention();
            countWeek++;
            System.out.println("Update Week: " + countWeek);
        }
    }

    public void saveNetwork() {
        Scanner sc = new Scanner(System.in);
        String fileName;
        System.out.print("Name a file to save network: ");
        fileName = sc.next();
        io.saveFile(graph, fileName);
    }
}