package com.example.gradlespringbootrestcaptchav2.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class ReCaptchaDTOResponse {
    private boolean success;
    private LocalDateTime challenge_ts;
    private String hostname;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public LocalDateTime getChallenge_ts() {
        return challenge_ts;
    }

    public void setChallenge_ts(LocalDateTime challenge_ts) {
        this.challenge_ts = challenge_ts;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ReCaptchaDTOResponse{");
        sb.append("success=").append(success);
        sb.append(", challenge_ts=").append(challenge_ts);
        sb.append(", hostname='").append(hostname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
