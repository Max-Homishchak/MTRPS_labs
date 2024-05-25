package service;

import java.io.*;

public class HtmlGeneratorService {

    private static final String OUT_FLAG = "--out";
    private static final String FORMAT_FLAG = "--format";
    private static final String ANSI_FORMAT = "ansi";
    private static final String HTML_FORMAT = "html";

    public void generate(String[] inputArgs) {
        if (inputArgs.length < 1) {
            throw new RuntimeException("Usage: MarkdownConverter <input_file> [--out <output_file>] [--format <output_format>]");
        }

        String inputFile = null;
        String outputFile = null;
        String outputFormat = null;

        for (int i = 0; i < inputArgs.length; i++) {
            if (OUT_FLAG.equals(inputArgs[i]) && ++i < inputArgs.length) {
                outputFile = inputArgs[i];
            } else if (FORMAT_FLAG.equals(inputArgs[i]) && ++i < inputArgs.length) {
                outputFormat = inputArgs[i];
            } else {
                inputFile = inputArgs[i];
            }
        }

        if (inputFile == null) {
            throw new RuntimeException("Input file is missing.");
        }

        try {
            processMarkdownTransaction(inputFile, outputFile, outputFormat);
        } catch (IOException e) {
            throw new RuntimeException("Error reading or writing file: " + e.getMessage());
        }
    }

    private void processMarkdownTransaction(String inputFile, String outputFile, String outputFormat) throws IOException {
        String markdownText = readMarkdownFile(inputFile);
        String outputText = convertToFormat(markdownText, outputFormat);
        if (outputFile != null) {
            writeToFile(outputText, outputFile, outputFormat);
        } else {
            printResultToConsul(outputFormat, outputText);
        }
    }

    private void printResultToConsul(String outputFormat, String outputText) {
        if (ANSI_FORMAT.equalsIgnoreCase(outputFormat)) {
            printFormattedText(outputText, new PrintWriter(System.out));
        } else {
            System.out.println(outputText);
        }
    }

    private String readMarkdownFile(String inputFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private void writeToFile(String outputText, String outputFile, String outputFormat) throws IOException {
        if (ANSI_FORMAT.equalsIgnoreCase(outputFormat)) {
            try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))) {
                printFormattedText(outputText, writer);
            }
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
                writer.write(outputText);
            }
        }
    }

    private String convertToFormat(String markdownText, String outputFormat) {
        if (ANSI_FORMAT.equalsIgnoreCase(outputFormat)) {
            markdownText = convertToANSI(markdownText);
        } else if (HTML_FORMAT.equalsIgnoreCase(outputFormat)) {
            markdownText = convertToHtml(markdownText);
        }
        return markdownText;
    }

    private String convertToHtml(String markdownText) {
        markdownText = markdownText.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>")
                .replaceAll("\\_(.*?)\\_", "<i>$1</i>")
                .replaceAll("(?s)```(.*?)```", "<pre>$1</pre>")
                .replaceAll("\\`(.*?)\\`", "<tt>$1</tt>")
                .replaceAll("```(.*?)```", "<pre>$1</pre>")
                .replaceAll("(?m)\\n\\s*\\n|\\n{2,}", "</p>\n\n<p>");
        return markdownText;
    }

    public String convertToANSI(String markdownText) {
        markdownText = markdownText.replaceAll("\\*\\*(.*?)\\*\\*", "\u001B[1m$1\u001B[22m")
                .replaceAll("\\_(.*?)\\_", "\u001B[3m$1\u001B[23m")
                .replaceAll("(?s)```(.*?)```", "\u001B[7m$1\u001B[27m")
                .replaceAll("\\`(.*?)\\`", "\u001B[7m$1\u001B[27m");
        return markdownText;
    }

    private void printFormattedText(String outputText, PrintWriter writer) {
        writer.println(outputText);
        writer.flush();
    }
}
