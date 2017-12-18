package domain;

import java.sql.Date;
import java.sql.Time;

public class VisitsDoctor {
	private String userId;
	private String doctor;

    private Long date;
    
    private String reason;

    public VisitsDoctor(){}

    public VisitsDoctor(String userId, String doctor, Long date, String reason) {
        this.userId = userId;
        this.doctor = doctor;
        this.date = date;
        this.reason = reason;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getDoctor() {
    		return this.doctor;
    }
    
    public void setDoctor(String doctor) {
    		this.doctor = doctor;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
    
    
    public String getReason() {
    		return this.reason;
    }
    
    public void setReason(String reason) {
    		this.reason = reason;
    }



    @Override
    public String toString() {
        return "VisitsDoctor{" +
                "userId='" + userId + '\'' +
                ", doctor=" + doctor +
                ", date=" + date +
                '}';
    }


}
