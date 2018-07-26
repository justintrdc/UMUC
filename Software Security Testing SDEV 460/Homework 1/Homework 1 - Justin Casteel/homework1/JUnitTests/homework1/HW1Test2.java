package homework1;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class HW1Test2 {

	@Test
	public void test() {
		HW1 test2 = new HW1();
		String testuser = test2.user("Justin7");
		String testinguser = "Justinfdfd";
		assertEquals(testuser,testinguser);
		
		String testpass = test2.pass("123");
		String testingpass = "123";
		assertEquals(testpass,testingpass);
	}

}
