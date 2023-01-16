package Tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class TestSituationSymflowerTest {
	@Test
	public void TestSituation1() throws IOException {
		int pos_pl1 = 0; // TODO This is a fallback value due to incomplete analysis.
		int pospl2 = 0; // TODO This is a fallback value due to incomplete analysis.
		int pos_pl3 = 0; // TODO This is a fallback value due to incomplete analysis.
		int foerste_terning_slag = 0; // TODO This is a fallback value due to incomplete analysis.
		int chancekort_nr = 0; // TODO This is a fallback value due to incomplete analysis.
		int bal1 = 0; // TODO This is a fallback value due to incomplete analysis.
		int bal2 = 0; // TODO This is a fallback value due to incomplete analysis.
		int bal3 = 0; // TODO This is a fallback value due to incomplete analysis.
		TestSituation expected = null; // TODO This is a fallback value due to incomplete analysis.
		TestSituation actual = new TestSituation(pos_pl1, pospl2, pos_pl3, foerste_terning_slag, chancekort_nr, bal1, bal2, bal3);

		assertTrue(EqualsBuilder.reflectionEquals(expected, actual, false, null, true));
	}

	@Test
	public void txtReadAndReturn2() throws FileNotFoundException {
		File file = null; // TODO This is a fallback value due to incomplete analysis.
		String LineNR = null; // TODO This is a fallback value due to incomplete analysis.
		String expected = null; // TODO This is a fallback value due to incomplete analysis.
		String actual = TestSituation.txtReadAndReturn(file, LineNR);

		assertEquals(expected, actual);
	}
}
