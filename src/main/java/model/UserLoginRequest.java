package model;


import org.apache.commons.lang3.RandomStringUtils;


public class UserLoginRequest {

    private final static String gmail = "@gmail.com";
    private String email;
    private String name;

    public UserLoginRequest(String email) {
        this.email = email;
    }

    public UserLoginRequest(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public static UserLoginRequest getRandomUser() {
        String email = RandomStringUtils.randomAlphabetic(10) + gmail;
        String name = RandomStringUtils.randomAlphabetic(10);
        return new UserLoginRequest(email, name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}