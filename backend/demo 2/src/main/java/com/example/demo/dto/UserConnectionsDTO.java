package com.example.demo.dto;

public class UserConnectionsDTO {
    private Long userId;
    private Long connectionId;

    public UserConnectionsDTO(Long userId, Long connectionId) {
        this.userId = userId;
        this.connectionId = connectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }
}
