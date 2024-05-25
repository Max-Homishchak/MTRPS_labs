import java.io.*;

public class Main {

    private static final String OUT_FLAG = "--out";

    public static void main(String[] args) {
        if (args.length < 1) {
            throw new RuntimeException("Usage: MarkdownConverter <input_file> [--out <output_file>]");
        }

        String inputFile = args[0];
        String outputFile = null;

        if (args.length > 2 && OUT_FLAG.equals(args[1])) {
            outputFile = args[2];
        }

        try {
            processMarkdownTransaction(inputFile, outputFile);
        } catch (IOException e) {
            throw new RuntimeException("Error reading or writing file: " + e.getMessage());
        }
    }

    private static void processMarkdownTransaction(String inputFile, String outputFile) throws IOException {
        String markdownText = readMarkdownFile(inputFile);
        String htmlText = convertToHtml(markdownText);
        if (outputFile != null) {
            writeHtmlToFile(htmlText, outputFile);
        } else {
            System.out.println(htmlText);
        }
    }


    private static String readMarkdownFile(String inputFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
        }
        return sb.toString();
    }

    private static void writeHtmlToFile(String htmlText, String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write(htmlText);
        }
    }

    private static String convertToHtml(String markdownText) {
        markdownText = markdownText.replaceAll("\\*\\*(.*?)\\*\\*", "<b>$1</b>")
                .replaceAll("\\_(.*?)\\_", "<i>$1</i>")
                .replaceAll("(?s)```(.*?)```", "<pre>$1</pre>")
                .replaceAll("\\`(.*?)\\`", "<tt>$1</tt>")
                .replaceAll("```(.*?)```", "<pre>$1</pre>")
                .replaceAll("(?m)\\n\\s*\\n|\\n{2,}", "</p>\n\n<p>");
        return markdownText;
    }
}