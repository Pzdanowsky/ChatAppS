package Objects;

import java.io.Serializable;

public class UserData implements Serializable {

    private int userID;
    private int avatarID;
    private String username;
    private String sessionNumber;
    private String sessionToken;
    private String nameUser;
    private String surname;
    private String password;
    private String email;



    public UserData(){
        this.sessionNumber = "00000";
        this.sessionToken = "0000000000";

    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(int avatarID) {
        this.avatarID = avatarID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionNumber() {
        return sessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean checkEqualsToken(String sesionToken){
        if(this.sessionToken.equals(sesionToken)){
            return true;
        }
        return false;
    }

    public boolean checkEqualsSesion(String sessionNumber){
        if(this.sessionNumber.equals(sessionNumber)){
            return true;
        }
        return false;
    }

}
