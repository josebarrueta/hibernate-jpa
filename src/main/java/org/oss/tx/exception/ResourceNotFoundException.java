package org.oss.tx.exception;

public class ResourceNotFoundException extends RuntimeException {
    private final Class<?> type;
    private final String id;

    public ResourceNotFoundException(Class<?> type, String id) {
        super("Unable to find entity=" + type.getSimpleName() + " with id=" + id);
        this.type = type;
        this.id = id;
    }

    public Class<?> getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}

