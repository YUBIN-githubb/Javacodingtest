public class ProblemData {
    private String title;
    private String description;
    private String input;
    private String output;

    public ProblemData(String title, String description, String input, String output) {
        this.title = title;
        this.description = description;
        this.input = input;
        this.output = output;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
