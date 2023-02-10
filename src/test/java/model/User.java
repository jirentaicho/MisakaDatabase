package model;

import database.MisakaEntity;

public class User{
    private final int id;
    private final String name;
    private boolean deleteFlag = false;
    public User(int id , String name){
        this.id = id;
        this.name = name;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public void setDelete(){
        this.deleteFlag = true;
    }
    public boolean getDeleteFlag(){
        return this.deleteFlag;
    }
    public User(){
        this.id = 0;
        this.name = "none";
    }
}
