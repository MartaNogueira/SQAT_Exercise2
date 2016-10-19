
// Before submitting write your ID and finish time here. Your ID is written on project description sheets.
// ID:
// Finish time:

public class PlanetExplorer {

	int positionX;
	int positionY;
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
		positionX = 0;
		positionY = 0;
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

		returnString = "(" + positionX + "," + positionY + "," + direction + ")" + encounteredObstacles;

		return returnString;

	}

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int startingX) {
		this.positionX = startingX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int startingY) {
		this.positionY = startingY;
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
		int updatedPositionX = positionX;
		int updatedPositionY = positionY;

		String[] directions = { "N", "E", "S", "W" };

		// case FORWARD
		if (singleCommand.equals("f")) {
			if (direction.equals("N")) {
				updatedPositionY += 1;
			} else if (direction.equals("S")) {
				updatedPositionY -= 1;
			} else if (direction.equals("E")) {
				updatedPositionX += 1;
			} else if (direction.equals("W")) {
				updatedPositionX -= 1;
			}

			if (updatedPositionX == -1) {
				updatedPositionX = gridX - 1;
			} else if (updatedPositionY == -0) {
				updatedPositionY = gridY - 1;
			} else if (updatedPositionX == gridX) {
				updatedPositionX = 0;
			} else if (updatedPositionY == gridY) {
				updatedPositionY = 0;
			}

			if (validateNewPosition(updatedPositionX, updatedPositionY)) {
				positionX = updatedPositionX;
				positionY = updatedPositionY;
			}
		}

		// case BACKWARD
		if (singleCommand.equals("b")) {
			if (direction.equals("N")) {
				updatedPositionY -= 1;
			} else if (direction.equals("S")) {
				updatedPositionY += 1;
			} else if (direction.equals("E")) {
				updatedPositionX -= 1;
			} else if (direction.equals("W")) {
				updatedPositionX += 1;
			}

			if (updatedPositionX == -1) {
				updatedPositionX = gridX - 1;
			} else if (updatedPositionY == -0) {
				updatedPositionY = gridY - 1;
			} else if (updatedPositionX == gridX) {
				updatedPositionX = 0;
			} else if (updatedPositionY == gridY) {
				updatedPositionY = 0;
			}

			if (validateNewPosition(updatedPositionX, updatedPositionY)) {
				positionX = updatedPositionX;
				positionY = updatedPositionY;
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
