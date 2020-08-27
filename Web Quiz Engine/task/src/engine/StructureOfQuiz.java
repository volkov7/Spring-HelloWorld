package engine;

public class StructureOfQuiz {
    protected String title;
    protected String text;
    protected String[] options;

    public StructureOfQuiz(String title, String text, String[] options) {
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String[] getOptions() {
        return options;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
