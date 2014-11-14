import java.io.*;

public class TestdisplayTopThree extends TestCase {
	TestCheckRules(String message) {
		super(message);
	}

	void TestCaseOne() {
		try {
			String s = displayTopThree();
			System.out.println(s);
			assertTrue(true);

		} catch (FileNotFoundException f) {

			System.out.println("Zephyr hasn't started");
			assertTrue(false);
		}

	}

}
