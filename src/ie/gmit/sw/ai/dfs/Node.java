package ie.gmit.sw.ai.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Node
 *
 * @author John Shields - G00348436
 */

public class Node {
    private final int nodeName;
    public static List<Node> children = new ArrayList<>();
    private boolean visited = false;
    private boolean goalNode;

    // Get the node's name.
    public Node(int name) {
        this.nodeName = name;
    }

    // Get the children - Convert the Array list into an Array.
    public Node[] children() {
        return children.toArray(new Node[0]);
    }

    // Adds a child node to a given node.
    public void addChildNode(Node child) {
        children.add(child);
    }

    // The name of the node or vertex.
    public int getNodeName() {
        return nodeName;
    }

    // Marks whether a node has already been visited during a search.
    public boolean isVisited() {
        return visited;
    }

    // Set the visited nodes.
    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // Returns whether a given node is a goal node.
    public boolean isGoalNode() {
        return goalNode;
    }

    // Set goal node - Are you the goal node?
    public void setGoalNode(boolean goalNode) {
        this.goalNode = goalNode;
    }
}