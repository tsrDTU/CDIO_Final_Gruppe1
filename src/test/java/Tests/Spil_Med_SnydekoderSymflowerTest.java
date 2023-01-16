package Tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class Spil_Med_SnydekoderSymflowerTest {
	@Test
	public void main1() throws IOException {
		String[] args = null; // TODO This is a fallback value due to incomplete analysis.
		Spil_Med_Snydekoder.main(args);
	}

	@Test
	public void txtReadAndReturn2() throws FileNotFoundException {
		File file = null; // TODO This is a fallback value due to incomplete analysis.
		String LineNR = null; // TODO This is a fallback value due to incomplete analysis.
		String expected = null; // TODO This is a fallback value due to incomplete analysis.
		String actual = Spil_Med_Snydekoder.txtReadAndReturn(file, LineNR);

		assertEquals(expected, actual);
	}
}
