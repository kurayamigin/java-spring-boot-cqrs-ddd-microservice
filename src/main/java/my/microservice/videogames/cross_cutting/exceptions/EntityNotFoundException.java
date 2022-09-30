package my.microservice.videogames.cross_cutting.exceptions;

import my.artifacts.models.abstractions.IEntity;
import my.microservice.videogames.cross_cutting.i18n.english.exceptions.RestErrorMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

public class EntityNotFoundException extends ResponseStatusException {
    public <TKey, T extends IEntity<TKey>> EntityNotFoundException(Class<T> entityClass, TKey id) {
        super(
                HttpStatus.NOT_FOUND,
                MessageFormat.format(RestErrorMessages.ENTITY_NOT_FOUND,
                        entityClass.getName(),
                        id)
        );
    }


}
