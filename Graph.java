import java.util.List;
import java.util.Set;

/**
 * Graph interface:Describe the basic behavior of the graph
 */
public interface Graph {
    /**
     * Get a neighbor of a node
     *
     * @param name node name
     * @return neighbors
     */
    List<Node> getNeighbors(String name);

    /**
     * Get all the nodes of the graph
     *
     * @return all nodes
     */
    Set<Node> getAllNode();

    /**
     * Reproduce the node information in the figure
     */
    void reset();

    /**
     * Get the shortest path between two nodes
     *
     * @param source source node
     * @param target target node
     * @return shortest path
     */
    List<Node> shortestPath(String source, String target);

    /**
     * Add two adjacent nodes to the undirected graph
     *
     * @param node1 node1
     * @param node2 node2
     */
    void addNodePair(String node1, String node2);

    /**
     * Determine if a node is in the graph
     *
     * @param name node name
     * @return result
     */
    boolean containsNode(String name);

}
