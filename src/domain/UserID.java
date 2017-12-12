package domain;

public class UserID {
    public String userid;

    public UserID(){

    }

    public UserID(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "UserID{" +
                "userid='" + userid + '\'' +
                '}';
    }
}
