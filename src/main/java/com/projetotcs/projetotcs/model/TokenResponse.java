package com.projetotcs.projetotcs.model;

import java.util.UUID;

public class TokenResponse {
    private UUID token;
    private boolean isAdmin = false;

    public TokenResponse(UUID token, boolean role) {
        this.token = token;
        this.isAdmin = role;
    }

    public UUID getToken() {
        return token;
    }

    public void setToken(UUID token) {
        this.token = token;
    }
}
