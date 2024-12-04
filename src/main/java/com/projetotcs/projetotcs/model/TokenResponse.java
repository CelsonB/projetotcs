package com.projetotcs.projetotcs.model;

import java.util.UUID;

public class TokenResponse {
    private UUID token;

    public TokenResponse(UUID token) {
        this.token = token;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
