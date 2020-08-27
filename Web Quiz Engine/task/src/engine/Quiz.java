package engine;

public class Quiz extends StructureOfQuiz{
    protected int answer;

    public Quiz(String title, String text, String[] options, int answer) {
        super(title, text, options);
        this.answer = answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getAnswer() {
        return answer;
    }
}
