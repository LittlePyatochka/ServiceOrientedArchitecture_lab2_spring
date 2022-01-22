package kamysh.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidValueException extends Exception {

    private ErrorCode errorCode;

    public InvalidValueException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
