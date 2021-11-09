public class User {

    private String username;
    private String sessionNumber;
    private String sessionToken;


    public User(){
        this.sessionNumber = "00000";
        this.sessionToken = "0000000000";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSesionNumber() {
        return sessionNumber;
    }

    public void setSesionNumber(String sesionNumber) {
        this.sessionNumber = sesionNumber;
    }

    public String getSesionToken() {
        return sessionToken;
    }

    public void setSesionToken(String sesionToken) {
        this.sessionToken = sesionToken;
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
