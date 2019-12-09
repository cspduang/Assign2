import java.util.*;

/**
 * Use the adjacency table to implement the graph
 */
public class AdjacencyGraphImpl implements Graph {


    private HashMap<String, List<Node>> adjacencyMap;

    private HashMap<String, Node> nameNodeMap;


    public AdjacencyGraphImpl() {
        this.adjacencyMap = new HashMap<>();
        this.nameNodeMap = new HashMap<>();
    }

    @Override
    public List<Node> getNeighbors(String name) {
        return adjacencyMap.get(name);
    }

    @Override
    public Set<Node> getAllNode() {
        Set<Node> set = new HashSet<>(nameNodeMap.size());
        for (Map.Entry<String, Node> entry : nameNodeMap.entrySet()) {
            set.add(entry.getValue());
        }
        return set;
    }

    @Override
    public void reset() {
        Set<Node> allNodes = getAllNode();
        for (Node node : allNodes) {
            node.setPath(Integer.MAX_VALUE);
            node.setPre(null);
        }
    }

    @Override
    public List<Node> shortestPath(String source, String target) {
        reset();
        //Use Dijkstra to find the shortest path
        //Use priority queues to speed up the selection of minimum distance nodes
        PriorityQueue<Node> nodePriorityQueue = new PriorityQueue<>();
        Node sourceNode = nameNodeMap.get(source);
        sourceNode.setPath(0);
        nodePriorityQueue.add(sourceNode);
        int ic = 0;
        while (!nodePriorityQueue.isEmpty()) {
            Node node = nodePriorityQueue.remove();
            //When the target node is accessed, it can jump out of the loop.
            if (node.getName().equals(target)) {
                break;
            }
            node.setVisited(true);
            List<Node> neighbors = getNeighbors(node.getName());
            for (int i = 0; i < neighbors.size(); i++) {
                Node nodeTemp = neighbors.get(i);
                if (!nodeTemp.getVisited()) {
                    if (node.getPath() + 1 < nodeTemp.getPath()) {
                        nodeTemp.setPath(node.getPath() + 1);
                        nodeTemp.setPre(node);
                    }
                    nodePriorityQueue.add(nodeTemp);
                }
            }
        }
        LinkedList<Node> shortestPath = new LinkedList<>();
        Node targetNode = nameNodeMap.get(target);
        while (targetNode != null) {
            shortestPath.addFirst(targetNode);
            targetNode = targetNode.getPre();
        }
        return shortestPath;
    }

    @Override
    public void addNodePair(String node1, String node2) {
        Node n1 = nameNodeMap.getOrDefault(node1, new Node(node1, Integer.MAX_VALUE));
        Node n2 = nameNodeMap.getOrDefault(node2, new Node(node2, Integer.MAX_VALUE));
        List<Node> node1Neighbors = adjacencyMap.getOrDefault(node1, new ArrayList<>());
        List<Node> node2Neighbors = adjacencyMap.getOrDefault(node2, new ArrayList<>());
        if (!node1Neighbors.contains(n2)) {
            node1Neighbors.add(n2);
            node2Neighbors.add(n1);
        }
        adjacencyMap.put(node1, node1Neighbors);
        adjacencyMap.put(node2, node2Neighbors);
        nameNodeMap.put(node1, n1);
        nameNodeMap.put(node2, n2);
    }

    @Override
    public boolean containsNode(String name) {
        return nameNodeMap.containsKey(name);
    }
}
