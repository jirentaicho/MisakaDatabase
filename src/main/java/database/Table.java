package database;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    // select時は、こちらの処理に全て差し替える
    public Map<I,T> getMapClone(){
        return this.map
                .entrySet()
                .stream()
                .collect(Collectors.toMap( e -> e.getKey(), e -> this.cloneT(e.getValue())));
    }

    private T cloneT(T t){
        try {
            Class<?> tclazz = t.getClass();
            Constructor constructor = tclazz.getConstructor();
            T clone = (T)constructor.newInstance();
            for(Field field : tclazz.getDeclaredFields()){
                field.setAccessible(true);
                Object fieldValue = field.get(t);
                field.set(clone, fieldValue);
            }
            return clone;
        } catch (Exception e){
            throw new RuntimeException("オブジェクトのクローン時にエラーが発生しました");
        }
    }

}
