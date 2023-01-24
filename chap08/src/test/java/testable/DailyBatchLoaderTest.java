package testable;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class DailyBatchLoaderTest {

	private Times mockTimes = Mockito.mock(Times.class);
	private final DailyBatchLoader loader = new DailyBatchLoader();

	@BeforeEach
	void setUp() {
		loader.setBasePath("src/test/resources");
		loader.setTimes(mockTimes);
	}

	@Test
	void loadCount() {
		given(mockTimes.today()).willReturn(LocalDate.of(2023, 1, 1));
		int ret = loader.load();
		assertEquals(3, ret);
	}

}
