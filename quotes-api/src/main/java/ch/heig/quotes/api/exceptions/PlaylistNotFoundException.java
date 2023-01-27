package ch.heig.quotes.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(Integer id) {
        super("Playlist " + id + " non trouvée");
    }
}
