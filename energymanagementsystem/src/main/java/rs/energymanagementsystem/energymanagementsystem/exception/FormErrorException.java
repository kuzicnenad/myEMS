package rs.energymanagementsystem.energymanagementsystem.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FormErrorException extends DuplicateKeyException {
    private String resourceName;

    public FormErrorException(String resourceName){
        super(String.format("%s already exists in system.", resourceName));
        this.resourceName = resourceName;
    }

    public String getResourceName(){
        return resourceName;
    }
}
