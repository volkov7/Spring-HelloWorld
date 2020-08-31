package engine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuizNotValidException extends RuntimeException{
    public QuizNotValidException(String message) {
        super(message);
    }
}
