package ie.gmit.sw.ai.dfs;

public class RecursiveDFS {

    public static int goalNode;

    public RecursiveDFS(Maze maze) {
        search(maze.getStartNode());
    }

    public void search(Node node) {
        if (node.isGoalNode()) {
            goalNode = node.getNodeName();
        } else {
            if (!node.isVisited()) {
                node.setVisited(true);
                Node[] children = node.children();
                for (Node child : children) {
                    search(child);
                }
            }
        }
    }

    public static void runSearch() {
        Maze maze = Maze.getInstance();
        RecursiveDFS search = new RecursiveDFS(maze);
    }
}
