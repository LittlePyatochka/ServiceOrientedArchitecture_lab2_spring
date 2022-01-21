package kamysh.exceptions;


public class EntityEntryNotFound extends Exception {
    private Long id;

    public EntityEntryNotFound(String message, Long id) {
        super(message);
        this.id = id;
    }

    public EntityEntryNotFound(Long id) {
        super("Item not found");
        this.id = id;
    }

    public EntityEntryNotFound() {
        super("Item not found");
    }
}
