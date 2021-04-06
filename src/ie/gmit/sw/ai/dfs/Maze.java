package ie.gmit.sw.ai.dfs;

import ie.gmit.sw.ai.GameWindow;

/**
 * Class Maze
 * Set up the Maze as Nodes to locate the goal node (GameWindow.mazeExit).
 *
 * @author John Shields - G00348436
 */

public class Maze {
    private static final Maze maze = new Maze();
    private final Node s;

    public static Maze getInstance() {
        return maze;
    }

    public Maze() {
        s = new Node(0);
        Node t = new Node(GameWindow.getMazeExit());
        t.setGoalNode(true);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        Node node10 = new Node(10);
        Node node11 = new Node(11);
        Node node12 = new Node(12);
        Node node13 = new Node(13);
        Node node14 = new Node(14);

        s.addChildNode(node1);
        s.addChildNode(node6);
        s.addChildNode(node8);

        node1.addChildNode(s);
        node1.addChildNode(node2);
        node1.addChildNode(node3);

        node2.addChildNode(node1);
        node2.addChildNode(node10);
        node2.addChildNode(node11);

        node11.addChildNode(node2);

        node10.addChildNode(node2);

        node3.addChildNode(node1);
        node3.addChildNode(node12);
        node3.addChildNode(node4);

        node12.addChildNode(node3);

        node4.addChildNode(node3);
        node4.addChildNode(node13);
        node4.addChildNode(node5);

        node13.addChildNode(node4);

        node5.addChildNode(node4);
        node5.addChildNode(node6);
        node5.addChildNode(node9);

        node6.addChildNode(node5);
        node6.addChildNode(s);
        node6.addChildNode(node7);

        node8.addChildNode(s);
        node8.addChildNode(node7);
        node8.addChildNode(node14);

        node14.addChildNode(node8);

        node7.addChildNode(node6);
        node7.addChildNode(node8);
        node7.addChildNode(node9);

        node9.addChildNode(node5);
        node9.addChildNode(node7);
        node9.addChildNode(t);
        t.addChildNode(node9);
    }

    public Node getStartNode() {
        return s;
    }
}