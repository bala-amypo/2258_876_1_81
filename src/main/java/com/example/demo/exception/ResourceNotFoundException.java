// package com.example.demo.exception;

// public class ResourceNotFoundException extends RuntimeException {
//     public ResourceNotFoundException(String message) {
//         super(message);
//     }
// }

// File: src/main/java/com/example/demo/exception/ResourceNotFoundException.java
package com.example.demo.exception;
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) { super(message); }
}

// File: src/main/java/com/example/demo/exception/ValidationException.java
package com.example.demo.exception;
public class ValidationException extends RuntimeException {
    public ValidationException(String message) { super(message); }
}