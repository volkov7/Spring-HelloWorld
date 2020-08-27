package engine;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class QuizController {
    private ArrayList<Quiz> quiz;
    private ArrayList<ClientQuiz> clientQuiz;
    private Answer attempt;

    public QuizController() {
        quiz = new ArrayList<>();
        clientQuiz = new ArrayList<>();
        attempt = new Answer();
    }

    @PostMapping(path = "api/quizzes", consumes = "application/json")
    public ClientQuiz createNewQuiz(@RequestBody Quiz newQuiz) {
        quiz.add(newQuiz);
        ClientQuiz clientNewQuiz = new ClientQuiz(newQuiz.title, newQuiz.text, newQuiz.options, quiz.size());
        clientQuiz.add(clientNewQuiz);
        return clientNewQuiz;
    }

    @PostMapping(path = "api/quizzes/{id}/solve")
    public Answer getAttempt(@PathVariable int id, @RequestParam int answer) {
        if (id - 1 < 0 || id - 1 >= clientQuiz.size()) {
            throw new QuizNotFoundException("id: " + id);
        }
        int rightAnswer = quiz.get(id - 1).getAnswer();
        if (rightAnswer == answer) {
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
