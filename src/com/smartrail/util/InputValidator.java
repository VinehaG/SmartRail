package com.smartrail.util;

import com.smartrail.exception.InvalidInputException;

public class InputValidator {
    
    public static void validateName(String name) throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty.");
        }
    }

    public static void validateAge(int age) throws InvalidInputException {
        if (age < 1 || age > 120) {
            throw new InvalidInputException("Invalid age. Must be between 1 and 120.");
        }
    }

    public static void validatePhone(String phone) throws InvalidInputException {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new InvalidInputException("Invalid phone number. Must be 10 digits.");
        }
    }

    public static void validateEmail(String email) throws InvalidInputException {
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidInputException("Invalid email format.");
        }
    }
}
