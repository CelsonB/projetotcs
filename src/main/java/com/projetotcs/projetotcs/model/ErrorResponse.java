package com.projetotcs.projetotcs.model;


public class ErrorResponse {
    private String mensagem;

    public ErrorResponse(String message) {
        this.mensagem = message;
    }

    public String getMessage() {
        return mensagem;
    }

    public void setMessage(String message) {
        this.mensagem = message;
    }
}

 