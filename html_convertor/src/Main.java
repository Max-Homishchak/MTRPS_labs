import service.HtmlGeneratorService;

public class Main {

    private final static HtmlGeneratorService htmlGeneratorService = new HtmlGeneratorService();
    public static void main(String[] args) {
        htmlGeneratorService.generate(args);
    }
}