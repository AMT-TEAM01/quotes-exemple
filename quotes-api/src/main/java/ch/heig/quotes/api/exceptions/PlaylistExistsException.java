package ch.heig.quotes.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PlaylistExistsException extends RuntimeException{
    public PlaylistExistsException(String name) {
        super("La playlist suivante existe déjà : " + name);
    }
}
