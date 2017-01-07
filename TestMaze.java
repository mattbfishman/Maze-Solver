
public class TestMaze
{
	public static void main (String[] args)
	{
		Maze maze = new Maze();

		maze.readMaze();
		maze.goNorth();
		maze.printMaze();
	}
}