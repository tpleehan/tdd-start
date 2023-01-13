package math;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class MathUtils {
	public static long sum(File dataFail) {
		try {
			return Files.readAllLines(dataFail.toPath()).stream()
				.mapToLong(Long::parseLong)
				.sum();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
