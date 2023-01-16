package Tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestSituation_FlereSlagSymflowerTest {
	@Test
	public void TestSituation_FlereSlag1() throws IOException {
		int[] pos_pl1 = null; // TODO This is a fallback value due to incomplete analysis.
		int[] pospl2 = null; // TODO This is a fallback value due to incomplete analysis.
		int[] pos_pl3 = null; // TODO This is a fallback value due to incomplete analysis.
		int[] foerste_terning_slag = null; // TODO This is a fallback value due to incomplete analysis.
		int[] chancekort_nr = null; // TODO This is a fallback value due to incomplete analysis.
		int antal_slag = 0; // TODO This is a fallback value due to incomplete analysis.
		int bal1 = 0; // TODO This is a fallback value due to incomplete analysis.
		int bal2 = 0; // TODO This is a fallback value due to incomplete analysis.
		int bal3 = 0; // TODO This is a fallback value due to incomplete analysis.
		TestSituation_FlereSlag expected = null; // TODO This is a fallback value due to incomplete analysis.
		TestSituation_FlereSlag actual = new TestSituation_FlereSlag(pos_pl1, pospl2, pos_pl3, foerste_terning_slag, chancekort_nr, antal_slag, bal1, bal2, bal3);

		assertTrue(EqualsBuilder.reflectionEquals(expected, actual, false, null, true));
	}

	@Test
	public void txtReadAndReturn2() throws FileNotFoundException {
		File file = null; // TODO This is a fallback value due to incomplete analysis.
		String LineNR = null; // TODO This is a fallback value due to incomplete analysis.
		String expected = null; // TODO This is a fallback value due to incomplete analysis.
		String actual = TestSituation_FlereSlag.txtReadAndReturn(file, LineNR);

		assertEquals(expected, actual);
	}
}
