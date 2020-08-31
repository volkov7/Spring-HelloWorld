package engine;

public class AttemptAnswer {
    protected int[] answer;

    public AttemptAnswer() {
        super();
    }

    public AttemptAnswer(int[] answer) {
        this.answer = answer;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
