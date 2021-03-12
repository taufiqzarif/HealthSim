public class TestHarness {
    public static void main(String[] args) {
        fileIO io = new fileIO();
        DSAGraph graph1 = new DSAGraph();
        int totalVertexAdded;

        String[] name = new String[] {"Alip", "Boon", "Carly"};
        String[] age = new String[] {"23" , "40", "30"};
        String[] status = new String[] {"s", "i", "r"};
        String[] id = new String[] {"1", "2", "3"};

        for(int i = 0; i<id.length; i++) {
            graph1.addVertex(name[i], age[i], status[i], id[i]);
        }
        totalVertexAdded = graph1.getVertexCount();
        assert name[0] != "Ali" : "Fail";
    }
}