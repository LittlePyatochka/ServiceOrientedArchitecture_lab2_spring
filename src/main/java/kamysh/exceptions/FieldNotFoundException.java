package kamysh.exceptions;

import lombok.Data;

@Data
public class FieldNotFoundException extends Exception{
    private String field;

    public FieldNotFoundException(String field) {
        super("Field not found");
        this.field = field;
    }
}
