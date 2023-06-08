public class Problem {
    private String title;
    private String description;
    private String input;
    private String output;

    public Problem(String title, String description, String input, String output) {
        this.title = title;
        this.description = description;
        this.input = input;
        this.output = output;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}
