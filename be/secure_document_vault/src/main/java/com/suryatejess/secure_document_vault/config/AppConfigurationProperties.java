package com.suryatejess.secure_document_vault.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.suryatejess.securedocvault")
public class AppConfigurationProperties {
    private JwtConfiguration jwt;
    private CookieConfiguration cookie;

    public static class JwtConfiguration {
        private String secret;
        private int expiresIn;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }
    }

    public static class CookieConfiguration {
        private String name;
        private int expiresIn;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getExpiresIn() {
            return expiresIn;
        }

        public void setExpiresIn(int expiresIn) {
            this.expiresIn = expiresIn;
        }
    }

    public JwtConfiguration getJwt() {
        return jwt;
    }

    public void setJwt(JwtConfiguration jwt) {
        this.jwt = jwt;
    }

    public CookieConfiguration getCookie() {
        return cookie;
    }

    public void setCookie(CookieConfiguration cookie) {
        this.cookie = cookie;
    }
}
