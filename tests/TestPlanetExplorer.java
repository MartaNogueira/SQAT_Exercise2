 import static org.junit.Assert.*;

import org.junit.Test;

public class TestPlanetExplorer {

	@Test
	public void testUpdatePositionF() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "(1,1)");
		planetExplorer.updatePosition("f");

		assertEquals(0, planetExplorer.getPositionX());
		assertEquals(1, planetExplorer.getPositionY());
	}

	@Test
	public void testExecuteCommandFFRF() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "(1,1)");
		String testString = planetExplorer.executeCommand("ffrf");

		assertEquals("(1,2,E)()", testString);
	}

	@Test
	public void testExecuteCommandFFLLLF() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "(1,1)");
		String testString = planetExplorer.executeCommand("ffrf");

		assertEquals("(1,2,E)()", testString);
	}

	@Test
	public void testExecuteCommandFBFFRF() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "(1,1)");
		String testString = planetExplorer.executeCommand("ffrf");

		assertEquals("(1,2,E)()", testString);
	}

	@Test
	public void testExecuteCommandFFR() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "(1,1)");
		String testString = planetExplorer.executeCommand("ffr");

		assertEquals("(0,2,E)()", testString);
	}

	@Test
	public void testExecuteCommandFFRFwithObstacle02() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "(1,5)");
		String testString = planetExplorer.executeCommand("ffrf");

		assertEquals("(1,1,E)(1,5)", testString);
	}

	@Test
	public void testExecuteCommandFFRFwithObstacle02and11() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "(0,2)(1,1)");
		String testString = planetExplorer.executeCommand("ffrf");

		assertEquals("(0,1,E)(0,2)(1,1)", testString);
	}

	@Test
	public void testExecuteCommandFFFtopBorder() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "");
		String testString = planetExplorer.executeCommand("fff");

		assertEquals("(0,0,N)()", testString);
	}

	@Test
	public void testExecuteCommandRFFFRrightBorder() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "");
		String testString = planetExplorer.executeCommand("rfff");

		assertEquals("(0,2,E)()", testString);
	}

	@Test
	public void testGetObstacles() {
		PlanetExplorer planetExplorer = new PlanetExplorer(3, 3, "(1,1)(2,2)(2,0)");
		String obstaclesString = planetExplorer.getObstacles();

		assertEquals(true, obstaclesString.contains("1,1"));
		assertEquals(true, obstaclesString.contains("2,2"));
		assertEquals(true, obstaclesString.contains("2,0"));
		assertEquals(false, obstaclesString.contains("2,1"));
	}
}
