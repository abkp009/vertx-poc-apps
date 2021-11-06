package com.example.nelsdemo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

public class CSVHelper {
	private CSVHelper() {
	}

	public static ByteArrayInputStream tutorialsToCSV(List<User> tutorials) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (User tutorial : tutorials) {
				List<String> data = Arrays.asList(tutorial.getId(), tutorial.getEmail());
				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}