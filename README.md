# Markdown-to-HTML

# Markdown Converter

This console application converts Markdown files into HTML fragments. It provides a convenient way to convert Markdown text into HTML markup, allowing users to either print the generated HTML to the standard output (stdout) or save it to an output file using the `--out` argument.

## Prerequisites
- **Java:** Ensure you have Java installed on your system. If not, you can download and install Java from [Java's official website](https://www.java.com/download/).
- **Maven:** Ensure you have Maven installed on your system. If not, you can download and install it from [Maven Apache Project](https://maven.apache.org/download.cgi).

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
       - To Run tests:
      ```bash
      mvn test
      //or
      mvn clean install   //will clean, rebuild and run unit tests for whole project)
      ```
    

   > **Note:** If you omit the `--out` argument, the HTML output will throw exception.

## Revert commits

### [Commit with failed build due failed tests](https://github.com/Max-Homishchak/MTRPS_labs/commit/861ed9c2a79499f6ce993416bba9b5ab667beb51)
### [Commit with fixed build due fixed unit test](https://github.com/Max-Homishchak/MTRPS_labs/commit/6b46023812fbcf78b35563c0b247a65eee2a197a)

## Summary
unit tests are usefull when you want to test separate parts of code, not whole business flow, they helps to avoid multiple bugs before they were even deployed to env.  
