package ie.gmit.sw.ai.dfs;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private final int nodeName;
    public static List<Node> children = new ArrayList<>();
    private boolean visited = false;
    private boolean goalNode;

    public Node(int name) {
        this.nodeName = name;
    }

    public Node[] children() {
        return children.toArray(new Node[0]);
    }

    // Adds a child node to a given node.
    public void addChildNode(Node child) {
        children.add(child);
    }


    // The name of the node or vertex
    public int getNodeName() {
        return nodeName;
    }

    // Marks whether a node has already been visited
    // during a search.
    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    // Returns whether a given node is a goal node.
    public boolean isGoalNode() {
        return goalNode;
    }

    public void setGoalNode(boolean goalNode) {
        this.goalNode = goalNode;
    }
}