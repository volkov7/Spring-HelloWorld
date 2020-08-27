package engine;

public class ClientQuiz extends StructureOfQuiz{

    protected int id;

    public ClientQuiz(String title, String text, String[] options, int id) {
        super(title, text, options);
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
