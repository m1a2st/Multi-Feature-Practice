package com.example.spring.exception;

import static org.springframework.util.StringUtils.capitalize;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(Object id, String resourceName) {
        this(id, "id", resourceName);
    }

    public NotFoundException(Object id, String identifierName, String resourceName) {
        super(String.format("Resource (%s) not found (%s = %s).", capitalize(resourceName), identifierName, id));
    }

    public static NotFoundExceptionBuilder notFound(Class<?> resourceType) {
        return new NotFoundExceptionBuilder(resourceType.getSimpleName());
    }

    public static NotFoundExceptionBuilder notFound(String resourceName) {
        return new NotFoundExceptionBuilder(resourceName);
    }

    public static class NotFoundExceptionBuilder {
        private final String resourceName;

        public NotFoundExceptionBuilder(String resourceName) {
            this.resourceName = resourceName;
        }

        public NotFoundException identifiedBy(String identifierName) {
            return new NotFoundException(identifierName, resourceName);
        }

        public NotFoundException id(Object id) {
            return new NotFoundException(id, resourceName);
        }

        public NotFoundException message(Object messageObj) {
            return message(messageObj.toString());
        }

        public NotFoundException message(String message) {
            return new NotFoundException(String.format("Resource (%s) not found. %s", resourceName, message));
        }
    }
}

class main{
    public static void main(String[] args) {
        NotFoundException notFoundException =
                NotFoundException.notFound("test").id(1);

    }
}