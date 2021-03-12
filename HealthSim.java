import java.util.*;

public class HealthSim {
    public static void main(String[] args) {
        DSAGraph graph = new DSAGraph();
        fileIO io = new fileIO();
        int totalSimWeek = 3;

        //Cited from https://stackoverflow.com/questions/8054703/can-i-invoke-a-java-method-other-than-mainstring-from-the-command-line
        //if(args[0].equalsIgnoreCase("-i")) {
            mainUI mainUI = new mainUI();
            mainUI.displayUI();
        //}
        if(args[0].equalsIgnoreCase("-s")) {
            
            if(args.length == 5) {
                String fileName = args[1];
                double transmission_rate = Double.valueOf(args[2]);
                double recover_rate = Double.valueOf(args[3]);
                double death_rate = Double.valueOf(args[4]);

                if(transmission_rate<0.00 || transmission_rate>1.00) {
                    System.out.println("Error! Please enter valid rate!");
                }
                else if(recover_rate<0.00 || recover_rate>1.00) {
                    System.out.println("Error! Please enter valid rate!");
                }
                else if(death_rate<0.00 || death_rate>1.00) {
                    System.out.println("Error! Please enter valid rate!");
                }
                else {
                    graph = io.readFile("people.csv");
                    graph.setTrate(transmission_rate);
                    graph.setRrate(recover_rate);
                    graph.setDrate(death_rate);
                    
		

                    int totalVertexAdded = graph.totalVertex();
                    int min = 1;
                    int max = totalVertexAdded;
			int max2 = totalVertexAdded * 2;
                    int randomID;
		    int randomID2;
                    int count = 1;
                    
			
                    
                        do {
			    Random r = new Random();
                            Random r2 = new Random();
			    randomID = r.nextInt(totalVertexAdded) + 1;
                            
                            randomID2 = r2.nextInt(totalVertexAdded) + 1;

                            
                            // System.out.println("randomID1: " + randomID);
                            // System.out.println("randomID2: " + randomID2);

	                    while(randomID==randomID2) {
			    	randomID = r.nextInt(totalVertexAdded) + 1;
                            
                                randomID2 = r2.nextInt(totalVertexAdded) + 1;
                            // System.out.println("randomID1: " + randomID);
                            // System.out.println("randomID2: " + randomID2);
                            }

			    String str1 = Integer.toString(randomID);
                            String str2 = Integer.toString(randomID2);
                            graph.addEdge(str1, str2);
				count++;
                        }while(count!=8);
                    
                    
                        //for(int i=0; i<max2; i++) { //Add edge to all vertex
                            //String r1 = Integer.toString(randomID);
                            //String r2 = Integer.toString(randomID2);
                            //graph.addEdge(r1, r2);
                        //}

                        for(int j=0; j<totalSimWeek; j++) {
                            graph.startIntervention();
                            System.out.println("");
                            
                        }

                        io.saveFile(graph, fileName);
                        System.out.println("\nSimulation saved to file: SimulationData.csv");
                    
                    
                }
                
                
            }
            else if(args.length != 6) {
                System.out.println("Usage HealthSim: java HealthSim -s File_Name transmission_rate recover_rate death_rate");
                System.out.println("File_Name: String");
                System.out.println("transmission_rate: Decimals(0.00-1.00)");
                System.out.println("recover_rate: Decimals(0.00-1.00)");
                System.out.println("death_rate: Decimals(0.00-1.00)g");
            }
        }
    }
}
