package com.example.assignment2;


import java.util.Date;

public class Member {
    String userId;
    Integer systolic;
    Integer diastolic;
    String dt;


    public Member() {}

    public Member(String userId, Integer systolic,
                   Integer diastolic,String dt) {
        this.userId = userId;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.dt=dt;

    }

    public String getUserId() { return userId; }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getsystolic() {return systolic;}

    public void setStudentFirstName(Integer systolic) {
        this.systolic = systolic;
    }

    public Integer getdiastolic() {return diastolic;}

    public void setStudentLastName(Integer studentLastName) {
        this.diastolic = diastolic;
    }

    public String getdate(){
        return dt;
    }
    public void setDate(String dt){
        this.dt=dt;
    }


}

