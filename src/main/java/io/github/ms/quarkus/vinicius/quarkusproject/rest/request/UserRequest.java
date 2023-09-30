package io.github.ms.quarkus.vinicius.quarkusproject.rest.request;

public class UserRequest {

    private Long id;
    private String name;
    private String emailAddress;
    private Long rg;
    private String cpf;

    public UserRequest() { }

    public UserRequest(Long id, String name, String emailAddress, Long rg, String cpf) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.rg = rg;
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getRg() {
        return rg;
    }

    public void setRg(Long rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
