import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Startup class for the entire program
 */
public class Starter {

    /**
     * Print the shortest path by standard
     *
     * @param shortestPath Shortest path
     */
    public static void printPath(List<Node> shortestPath) {
        System.out.print("Path between " + shortestPath.get(0).getName() + " and " + shortestPath.get(shortestPath.size() - 1).getName() + ": ");
        for (int i = 0; i < shortestPath.size(); i++) {
            Node node = shortestPath.get(i);
            if (i != shortestPath.size() - 1) {
                System.out.print(node.getName() + " --> ");
            } else {
                System.out.println(node.getName());
            }
        }
    }


    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        FileAnalyse fileAnalyse = new FileAnalyse();
        Graph graph = fileAnalyse.analyseFileAndBuildGraph(filePath);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String actor1 = "";
            while (true) {
                System.out.print("Actor 1 name:");
                actor1 = scanner.nextLine().trim();
                if (!graph.containsNode(actor1)) {
                    System.out.println("No such actor.");
                } else {
                    break;
                }
            }
            String actor2 = "";
            while (true) {
                System.out.print("Actor 2 name:");
                actor2 = scanner.nextLine().trim();
                if (!graph.containsNode(actor1)) {
                    System.out.println("No such actor.");
                } else {
                    break;
                }
            }
            printPath(graph.shortestPath(actor1, actor2));
        }
    }


}
