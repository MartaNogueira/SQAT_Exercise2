
// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID:
// Finish time:

public class PlanetExplorer {

	int posiX;
	int posiY;
	String direction;
	String obstacles;
	String encounteredObstacles;
	int gridX;
	int gridY;

	public PlanetExplorer(int x, int y, String obstacles) {
		/*
		 * x and y represent the size of the grid. Obstacles is a String
		 * formatted as follows:
		 * "(obs1_x,obs1_y)(obs2_x,obs2_y)...(obsN_x,obsN_y)" with no white
		 * spaces.
		 * 
		 * Example use: For a 100x100 grid with two obstacles at coordinates
		 * (5,5) and (7,8) PlanetExplorer explorer = new
		 * PlanetExplorer(100,100,"(5,5)(7,8)")
		 * 
		 */
		posiX = 0;
		posiY = 0;
		this.setDirection("N");
		this.obstacles = obstacles;
		this.setEncounteredObstacles("");
		this.gridX = x;
		this.gridY = y;

	}

	

	public String executeCommand(String command) {

		/*
		 * The command string is composed of "f" (forward), "b" (backward), "l"
		 * (left) and "r" (right) Example: The explorer is on a 100x100 grid at
		 * location (0, 0) and facing NORTH. The explorer is given the commands
		 * "ffrff" and should end up at (2, 2) facing East.
		 * 
		 * The return string is in the format:
		 * "(pos_x,pos_y,facing)(obs1_x,obs1_y)(obs2_x,obs2_y)..(obsN_x,obsN_y)"
		 * Where pos_x and pos_y are the final coordinates, facing is the
		 * current direction the explorer is pointing to (N,S,W,E). The return
		 * string should also contain a list of coordinates of the encountered
		 * obstacles. No white spaces.
		 */
		String returnString = "";
		char[] commands = command.toCharArray();
		setEncounteredObstacles("");

		for (int i = 0; i < commands.length; i++) {
			updatePosition(String.valueOf(commands[i]));
		}

		if (encounteredObstacles.equals("")) {
			encounteredObstacles = "()";
		}

		returnString = "(" + posiX + "," + posiY + "," + direction + ")" + encounteredObstacles;

		return returnString;

	}

	public int getPositionX() {
		return posiX;
	}

	public void setPositionX(int startX) {
		this.posiX = startX;
	}

	public int getPositionY() {
		return posiY;
	}

	public void setPositionY(int startY) {
		this.posiY = startY;
	}

	public String getObstacles() {
		return obstacles;
	}

	public void setObstacles(String obstacles) {
		this.obstacles = obstacles;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getEncounteredObstacles() {
		return encounteredObstacles;
	}

	public void setEncounteredObstacles(String encounteredObstacles) {
		this.encounteredObstacles = encounteredObstacles;
	}

	public void updatePosition(String singleCommand) {
		int updatedPosiX = posiX;
		int updatedPosiY = posiY;

		String[] directions = { "N", "E", "S", "W" };

		// case FORWARD
		if (singleCommand.equals("f")) {
			if (direction.equals("N")) {
				updatedPosiY += 1;
			} else if (direction.equals("S")) {
				updatedPosiY -= 1;
			} else if (direction.equals("E")) {
				updatedPosiX += 1;
			} else if (direction.equals("W")) {
				updatedPosiX -= 1;
			}

			if (updatedPosiX == -1) {
				updatedPosiX = gridX - 1;
			} else if (updatedPosiY == -0) {
				updatedPosiY = gridY - 1;
			} else if (updatedPosiX == gridX) {
				updatedPosiX = 0;
			} else if (updatedPosiY == gridY) {
				updatedPosiY = 0;
			}

			if (validateNewPosition(updatedPosiX, updatedPosiY)) {
				posiX = updatedPosiX;
				posiY = updatedPosiY;
			}
		}

		// case BACKWARD
		if (singleCommand.equals("b")) {
			if (direction.equals("N")) {
				updatedPosiY -= 1;
			} else if (direction.equals("S")) {
				updatedPosiY += 1;
			} else if (direction.equals("E")) {
				updatedPosiX -= 1;
			} else if (direction.equals("W")) {
				updatedPosiX += 1;
			}

			if (updatedPosiX == -1) {
				updatedPosiX = gridX - 1;
			} else if (updatedPosiY == -0) {
				updatedPosiY = gridY - 1;
			} else if (updatedPosiX == gridX) {
				updatedPosiX = 0;
			} else if (updatedPosiY == gridY) {
				updatedPosiY = 0;
			}

			if (validateNewPosition(updatedPosiX, updatedPosiY)) {
				posiX = updatedPosiX;
				posiY = updatedPosiY;
			}
		}

		// case RIGHT

		if (singleCommand.equals("r")) {
			for (int i = 0; i < directions.length; i++) {
				if (direction.equals(directions[i])) {
					this.setDirection(directions[(i + 1) % 4]);
					break;
				}
			}
		}

		// case LEFT
		if (singleCommand.equals("l")) {
			for (int i = 0; i < directions.length; i++) {
				if (direction.equals(directions[i])) {
					int counter = i - 1;
					if (counter < 0) {
						counter = i + 4;
					}
					this.setDirection(directions[counter]);
					break;
				}
			}
		}
	}

	private boolean validateNewPosition(int updatedPositionX, int updatedPositionY) {
		// check obstacles
		String position = "(" + updatedPositionX + "," + updatedPositionY + ")";
		if (obstacles.contains(position)) {
			encounteredObstacles = encounteredObstacles + position;
			return false;
		}

		return true;
	}

}
