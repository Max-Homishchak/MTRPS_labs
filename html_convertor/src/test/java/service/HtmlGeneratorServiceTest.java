package service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class HtmlGeneratorServiceTest {

    private HtmlGeneratorService htmlGeneratorService;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        htmlGeneratorService = new HtmlGeneratorService();
    }

    @Test
    void testGenerateWithNoArguments() {
        String[] args = {};
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            htmlGeneratorService.generate(args);
        });
        assertEquals("Usage: MarkdownConverter <input_file> [--out <output_file>] [--format <output_format>]", exception.getMessage());
    }

    @Test
    void testGenerateWithMissingInputFile() {
        String[] args = {"--out", "output.html", "--format", "html"};
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            htmlGeneratorService.generate(args);
        });
        assertEquals("Input file is missing.", exception.getMessage());
    }

    @Test
    void testConvertToHtml() {
        String markdown = "**bold** _italic_ `code` ```preformatted```";
        String expectedHtml = "<b>bold</b> <i>italic</i> <tt>code</tt> <pre>preformatted</pre>";

        assertEquals(expectedHtml, htmlGeneratorService.convertToHtml(markdown));
    }

    @Test
    void testConvertToANSI() {
        String markdown = "**bold** _italic_ `code` ```preformatted```";
        String expectedAnsi = "\u001B[1mbold\u001B[22m \u001B[3mitalic\u001B[23m \u001B[7mcode\u001B[27m \u001B[7mpreformatted\u001B[27m";

        assertEquals(expectedAnsi, htmlGeneratorService.convertToANSI(markdown));
    }

}