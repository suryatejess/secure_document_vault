package com.suryatejess.secure_document_vault.exceptions;

public class WrongUserCredentials extends RuntimeException {

    public WrongUserCredentials(String message) {
        super(message);
    }

}