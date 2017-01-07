import java.util.Scanner;
import java.io.*;

public class Maze
{

	//instance variables 
	private int rows;
	private int cols;
	private int startRow;
	private int startCol;
	private int finishRow;	
	private int finishCol;
	private int currRow;
	private int currCol;
	private boolean success = false; 
	private char[][] maze;
	//constructors
	
	//methods
	public Maze()
	{
		//left blank on purpose
	}

	//checks if the method can move north and is successful. 
	//if it can't move north it will try other directions.
	//if the other directions dont work it will backtrack.
	public boolean goNorth()
	{
		if(currRow-1 >=0){
		if(maze[currRow-1][currCol] == ' ')
		{ 
			currRow--;
			maze [currRow][currCol] = 'p'; 
			if (currRow == finishRow && currCol == finishCol)
			{
				success = true;
			}
			else
			{
				success = goNorth();
				if (!success)
				{
					goWest();
					if (!success)
					{
						goEast();
						if(!success)
						{
							if(maze[currRow+1][currCol] != 'x' && maze[currRow+1][currCol] != ' '){
							maze [currRow][currCol] = 'v';
							currRow++;
							goNorth();
							}
						}
					}
						
				}
			}
		}
		}
		else
		{
			success = false;
		}
			return success;
	}

	public boolean goSouth()
	{
		if(maze[currRow+1][currCol]== ' ')
		{
			currRow++;
			maze[currRow][currCol] = 'p';
			if (currRow == finishRow && currCol == finishCol)
			{
				success = true;
			}
			else
			{
				success = goSouth();
				if (!success)
				{
					goEast();
					if(!success)
					{
						goWest();
						if(!success)
						{
							if (maze[currRow-1][currCol] != 'x' && maze[currRow-1][currCol] != ' '){
							maze [currRow][currCol] = 'v';
							currRow--;
							goSouth();
							}
						}
					}
				}
			}
		}
		else
		{
			success = false;
		}
			return success; 
	}

	public boolean goEast()
	{
		if(maze[currRow][currCol+1]== ' ')
		{
			currCol++;
			maze[currRow][currCol] = 'p';
			if (currRow == finishRow && currCol == finishCol)
			{
				success = true; 
			}
			else
			{
				success = goEast();
				if(!success)
				{
					goNorth();
					if(!success)
					{
						goSouth();
						if(!success)
						{
							if (maze[currRow][currCol-1] != 'x' && maze[currRow][currCol-1] != ' '){
							maze [currRow][currCol] = 'v';
							currCol--;
							goEast();
							}
						}
					}
				}
			}
		}
		else
		{
			success = false;
		}
			return success;
	}
	
	public boolean goWest()
	{
		if(maze[currRow][currCol-1]== ' ')
		{
			currCol--;
			maze[currRow][currCol] = 'p';
			if (currRow == finishRow && currCol == finishCol)
			{
				success = true;
			}
			else
			{
				success = goWest();
				if(!success)
				{
					goSouth();
					if(!success)
					{
						goNorth();
						if(!success)
						{
							if (maze[currRow][currCol+1] != 'x' && maze[currRow][currCol+1] != ' '){
							maze [currRow][currCol] = 'v';
							currCol++;
							goWest();
							}
						}
					}
				}
			}
		}
		else
		{
			success = false;
		}
			return success;
	}

    
    //the read maze method should be called in the tester class
    public void readMaze(){
        String inputLine;
		
		Scanner fileInput;
		File inFile = new File("Maze2.txt");
		
		try{
        fileInput = new Scanner(inFile);

        //Pulls the data at the top out to make the maze.
        //it also sets the start and end row for win.
        cols = fileInput.nextInt();
        rows = fileInput.nextInt();
        finishCol = fileInput.nextInt();
        finishRow = fileInput.nextInt();
        startCol = fileInput.nextInt();
        startRow = fileInput.nextInt();
        maze = new char[rows][cols];

        //this removes the space between the end of your previous line and the next, so you can start reading the maze properly
        String temp = fileInput.nextLine();

        //here i placed the nested loops need to populate the array
        while(fileInput.hasNext()){
            for(int i = 0; i<rows; i++)
            {
                inputLine = fileInput.nextLine();

                for(int j = 0; j<cols; j++)
                {

                    maze[i][j] = inputLine.charAt(j);
                }
                System.out.println();
            }   
        }

        currRow = startRow;
        currCol = startCol;

        fileInput.close();

    } // end try
		
		catch (FileNotFoundException e){
			System.out.println(e);
			System.exit(1);		// IO error; exit program
		} // end catch
    }
    
    
    //call this aswell in order to display the solve and array
    public void printMaze(){
        //print out important info
		System.out.println("numRows: " + rows + " numCols: " + cols);
		System.out.println("Start Loc: (" + startCol + "," + startRow + ")");
		System.out.println("Win Loc: (" + finishCol + "," + finishRow +")");
		
		//loop through maze and print its contents
		for (int i = 0; i < rows; i++) {
		    for (int j = 0; j < cols; j++) {
		        System.out.print(maze[i][j]);
		    }
            System.out.println();
		}
    }
	
}


