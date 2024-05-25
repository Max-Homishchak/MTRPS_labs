# Markdown-to-HTML

# Markdown Converter

This console application converts Markdown files into HTML fragments. It provides a convenient way to convert Markdown text into HTML markup, allowing users to either print the generated HTML to the standard output (stdout) or save it to an output file using the `--out` argument.

## Prerequisites
- **Java:** Ensure you have Java installed on your system. If not, you can download and install Java from [Java's official website](https://www.java.com/download/).

## Installation
1. Clone the repository from GitHub:
   ```bash
   git clone https://github.com/Max-Homishchak/MTRPS_labs
   cd MTRPS_labs
   ```

## Usage
1. Compile the Java files:
   ```bash
   javac Main.java
   ```
2. Run the compiled Java class:
    - To convert a Markdown file and print the HTML to stdout:
      ```bash
      java Main /path/to/input_file.md
      ```
    - To convert a Markdown file and save the HTML to an output file:
      ```bash
      java Main /path/to/input_file.md --out /path/to/output_file.html
      ```

   > **Note:** If you omit the `--out` argument, the HTML output will throw exception.

## Revert commit

### [Revert commit](https://github.com/Max-Homishchak/MTRPS_labs/commit/4d029bb45411b5a8a2ae62f0446ab86539a3d4fd)