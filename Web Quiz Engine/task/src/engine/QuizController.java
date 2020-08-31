package engine;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class QuizController {
    private final ArrayList<Quiz> quiz;
    private final ArrayList<ClientQuiz> clientQuiz;
    private final Answer attempt;

    public QuizController() {
        quiz = new ArrayList<>();
        clientQuiz = new ArrayList<>();
        attempt = new Answer();
    }

    @PostMapping(path = "api/quizzes", consumes = "application/json")
    public ClientQuiz createNewQuiz(@RequestBody Quiz newQuiz) {
        if (newQuiz.title == null || newQuiz.text == null || newQuiz.title.isEmpty() || newQuiz.text.isEmpty()
                || newQuiz.getOptions() == null || newQuiz.getOptions().length < 2) {
            throw new QuizNotValidException("Not valid quiz!");
        }
        quiz.add(newQuiz);
        ClientQuiz clientNewQuiz = new ClientQuiz(newQuiz.title, newQuiz.text, newQuiz.options, quiz.size());
        clientQuiz.add(clientNewQuiz);
        return clientNewQuiz;
    }

    @PostMapping(path = "api/quizzes/{id}/solve", consumes = "application/json")
    public Answer getAttempt(@PathVariable int id, @RequestBody AttemptAnswer answer) {
        if (id - 1 < 0 || id - 1 >= clientQuiz.size()) {
            throw new QuizNotFoundException("id: " + id);
        }
        int[] rightAnswer = quiz.get(id - 1).getAnswer();
        if (Arrays.equals(rightAnswer, answer.getAnswer())) {
            attempt.setSuccess(true);
            attempt.setFeedback("Congratulations, you're right!");
        } else {
            attempt.setSuccess(false);
            attempt.setFeedback("Wrong answer! Please, try again.");
        }
        return attempt;
    }

    @GetMapping(path = "api/quizzes")
    public ArrayList<ClientQuiz> getAllQuiz() {
        return clientQuiz;
    }

    @GetMapping(path = "api/quizzes/{id}")
    public ClientQuiz getSpecQuiz(@PathVariable int id) {
        if (id - 1 < 0 || id - 1 >= clientQuiz.size()) {
            throw new QuizNotFoundException("id: " + id);
        }
        return clientQuiz.get(id - 1);
    }
}
