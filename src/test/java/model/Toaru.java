package model;

import database.MisakaEntity;

public class Toaru{

    public int id;

    public String name;

    public Toaru(int id, String name){
        this.id = id; this.name = name;
    }

    // 必ず引数無しのコンストラクタを用意する
    public Toaru(){
    }
}
