package homework1;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class HW1Test {

	@Test
	public void test() {
		HW1 test = new HW1();
		String testuser = test.user("Justin");
		String testinguser = "Justin";
		assertEquals(testinguser,testuser);
		
		String testpass = test.pass("123");
		String testingpass = "123";
		assertEquals(testingpass,testpass);
		
	}
}