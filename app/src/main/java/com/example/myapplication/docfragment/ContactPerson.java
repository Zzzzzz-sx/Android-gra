package com.example.myapplication.docfragment;

public class ContactPerson {
    int personid;
    String personname;
    public ContactPerson(int personid,String personname){
        this.personid = personid;
        this.personname = personname;
    }
    public int getPersonid() {
        return personid;
    }
    public String getPersonname() {return personname;};

}
