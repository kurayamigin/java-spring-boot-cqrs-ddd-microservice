package my.microservice.videogames.cross_cutting.exceptions;

import my.microservice.videogames.cross_cutting.i18n.english.exceptions.RestErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

public class PatchException extends ResponseStatusException {
    public PatchException(Class<?> aClass, String format) {
        super(HttpStatus.BAD_REQUEST, MessageFormat.format(RestErrorMessages.PATCH_EXCEPTION, aClass.getName(), format));
    }
}
