package model;


import org.apache.commons.lang3.RandomStringUtils;


public class CreateUserRequest {

    private final static String gmail = "@gmail.com";
    private String email;
    private String password;
    private String name;

    public CreateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public CreateUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static CreateUserRequest getRandomUser() {
        String email = RandomStringUtils.randomAlphabetic(10) + gmail;
        String password = RandomStringUtils.randomAlphabetic(10);
        String name = RandomStringUtils.randomAlphabetic(10);
        return new CreateUserRequest(email, password, name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}