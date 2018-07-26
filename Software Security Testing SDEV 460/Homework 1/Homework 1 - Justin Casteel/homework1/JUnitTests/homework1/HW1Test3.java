package homework1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

class HW1Test3 {

	@Test
	public void test() {
		
		HW1 test3 = new HW1();
		File file = new File("C:/Users/Justin/eclipse-workspace/homework1/log.txt");
		assertTrue(file.exists());
		
	}

}
