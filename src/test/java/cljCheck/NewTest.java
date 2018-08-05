package cljCheck;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class NewTest {
	private static List<String> b = Arrays.asList();
	@Test
	public void f() {
		List<String> a = Arrays.asList("mpu0", "mpu1", "mpu2", "mpu3");
		

		for (int i = 0; i < 4; i++) {
			b.add("mpu" + i);

		}

		System.out.println("size of b" + b.size());

		for (String e : a) {
			if (b.contains(e)) {

			} else {
				System.out.println(e);
			}
		}

	}
}
