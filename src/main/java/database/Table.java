package database;

import java.util.HashMap;
import java.util.Map;

public class Table<I,T>{
    private final Map<I,T> map;

    private Table(){
        this.map = new HashMap<>();
    }

    public static Table of(Map<?,?> map){
        Table table = new Table();
        table.map.putAll(map);
        return table;
    }

    public Map<I,T> getMap(){
        return this.map;
    }

}
