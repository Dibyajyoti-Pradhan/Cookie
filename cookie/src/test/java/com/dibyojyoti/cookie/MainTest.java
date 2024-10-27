package com.dibyojyoti.cookie;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainWithInvalidArgumentSize() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = { "-f", "filename.csv" };
        Main.main(args);

        String expectedOutput = "Usage: java Main -f <filename> -d <date>\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWithMissingFilename() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = { "random", "random", "-d", "2018-12-09" };
        Main.main(args);

        String expectedOutput = "Invalid arguments. Please ensure both -f and -d are provided.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    public void testMainWithMissingDate() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        String[] args = { "-f", "filename.csv", "random", "random" };
        Main.main(args);

        String expectedOutput = "Invalid arguments. Please ensure both -f and -d are provided.\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @ParameterizedTest
    @MethodSource("provideTestExceptionData")
    public void testMainWithUnhappyFlowThenException(String filePath, String date,
            String error) {
        outContent.reset();
        String[] args = { "-f", Paths.get(getClass().getResource(filePath).getPath()).toString(), "-d", date };
        Main.main(args);
        String content = outContent.toString();
        assertTrue(content.contains(error), "Output should contain Error.");
    }

    @ParameterizedTest
    @MethodSource("provideTestHappyFlowData")
    public void testMainWithHappyFlowThenSuccessfulResults(String filePath, String date, String[] expectedCookieIds) {
        outContent.reset();
        String[] args = { "-f", Paths.get(getClass().getResource(filePath).getPath()).toString(), "-d", date };
        Main.main(args);
        String content = outContent.toString();
        System.out.println("content = " + content);
        if (expectedCookieIds.length == 0) {
            assertTrue(content.isEmpty(), "Output should be empty for no activity.");
        } else {
            assertTrue(Arrays.stream(expectedCookieIds).allMatch(content::contains),
                    "Output should contain all the expected cookie IDs.");
        }
    }

    private static Stream<Arguments> provideTestExceptionData() {
        return Stream.of(
                Arguments.of("/test_cookies_reversed.csv", "2018-12-09", "Error Message"),
                Arguments.of("/test_cookies_corrupted_datetime.csv", "2018-12-09", "Error Message"));
    }

    private static Stream<Arguments> provideTestHappyFlowData() {
        return Stream.of(
                Arguments.of("/test_cookies_happy_flow.csv", "2018-12-09", new String[] { "AtY0laUfhglK3lC7" }),

                Arguments.of("/test_cookies_happy_flow.csv", "2018-12-19", new String[] {}),
                Arguments.of("/test_cookies_empty.csv", "2018-12-09", new String[] {}));
    }

}