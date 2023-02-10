package model;

import database.MisakaEntity;

public class Lot{
    private final String lotNo;
    public Lot(String lotNo){
        this.lotNo = lotNo;
    }
    public String getLotNo(){
        return this.lotNo;
    }
    public Lot(){
        this.lotNo = "none";
    }
}
