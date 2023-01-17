package UserStory_Tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class K9_en_spiller_fallit_flere_ikkeSymflowerTest {
	@Test
	public void main1() throws IOException {
		String[] args = null; // TODO This is a fallback value due to incomplete analysis.
		K9_en_spiller_fallit_flere_ikke.main(args);
	}

	@Test
	public void txtReadAndReturn2() throws FileNotFoundException {
		File file = null; // TODO This is a fallback value due to incomplete analysis.
		String LineNR = null; // TODO This is a fallback value due to incomplete analysis.
		String expected = null; // TODO This is a fallback value due to incomplete analysis.
		String actual = K9_en_spiller_fallit_flere_ikke.txtReadAndReturn(file, LineNR);

		assertEquals(expected, actual);
	}
}
