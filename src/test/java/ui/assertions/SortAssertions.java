package ui.assertions;

import com.codeborne.selenide.ElementsCollection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortAssertions {

	public static void assertSortedAscendingByName(ElementsCollection names) {
		List<String> actual = names.texts();
		List<String> expected = actual.stream().sorted().collect(Collectors.toList());
		assertEquals(expected, actual, "Items are not sorted ascending by name");
	}

	public static void assertSortedDescendingByName(ElementsCollection names) {
		List<String> actual = names.texts();
		List<String> expected = actual.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
		assertEquals(expected, actual, "Items are not sorted descending by name");
	}

	public static void assertSortedAscendingByPrice(ElementsCollection prices) {
		List<Double> actual = prices.texts().stream()
				.map(p -> Double.parseDouble(p.replace("$", "")))
				.collect(Collectors.toList());
		List<Double> expected = actual.stream().sorted().collect(Collectors.toList());
		assertEquals(expected, actual, "Items are not sorted ascending by price");
	}

	public static void assertSortedDescendingByPrice(ElementsCollection prices) {
		List<Double> actual = prices.texts().stream()
				.map(p -> Double.parseDouble(p.replace("$", "")))
				.collect(Collectors.toList());
		List<Double> expected = actual.stream().sorted((a, b) -> Double.compare(b, a)).collect(Collectors.toList());
		assertEquals(expected, actual, "Items are not sorted descending by price");
	}
}
