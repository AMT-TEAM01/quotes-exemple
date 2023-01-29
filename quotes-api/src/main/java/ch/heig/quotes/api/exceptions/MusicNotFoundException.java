package ch.heig.quotes.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MusicNotFoundException extends RuntimeException {
    public MusicNotFoundException(Integer id) {
        super("Morceau de musique " + id + " non trouvé.");
    }
}
