package ie.gmit.sw.ai.dfs;

/**
 * Class RecursiveDFS - Called in GameWindow.
 * Uninformed Searching – Depth-First Search
 * Use Recursion to find the Goal Node (GameWindow.mazeExit).
 *
 * @author John Shields - G00348436
 */

public class RecursiveDFS {
    public static int goalNode;

    public RecursiveDFS(Maze maze) {
        search(maze.getStartNode());
    }

    // Search through the Maze's Nodes.
    public void search(Node node) {
        if (node.isGoalNode()) {
            goalNode = node.getNodeName();
        } else {
            if (!node.isVisited()) {
                node.setVisited(true);
                // Children of the current node.
                Node[] children = node.children();
                // loop over the children then
                // call the search on the child.
                for (Node child : children) {
                    search(child);
                }
            }
        }
    }

    // Run the search - Called in GameWindow - used in CharacterTask.
    public static void runSearch() {
        Maze maze = Maze.getInstance();
        RecursiveDFS search = new RecursiveDFS(maze);
    }
}
