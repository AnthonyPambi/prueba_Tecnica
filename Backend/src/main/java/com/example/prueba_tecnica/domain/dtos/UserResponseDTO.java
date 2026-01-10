package com.example.prueba_tecnica.domain.dtos;

public class UserResponseDTO {

    private String userName;
    private String mail;
    private String sessionActive;
    private String status;

    // Constructores
    public UserResponseDTO() {}

    public UserResponseDTO(String userName, String mail, String sessionActive, String status) {
        this.userName = userName;
        this.mail = mail;
        this.sessionActive = sessionActive;
        this.status = status;
    }

    // Getters y Setters
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getSessionActive() { return sessionActive; }
    public void setSessionActive(String sessionActive) { this.sessionActive = sessionActive; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
