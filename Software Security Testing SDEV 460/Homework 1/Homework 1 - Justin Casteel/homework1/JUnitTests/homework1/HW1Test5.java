package homework1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HW1Test5 {

	@Test
	void test() {
		HW1 test5 = new HW1();
		String result = test5.combo("Justin3","123");
		assertEquals("Justin123", result);
	}

}
