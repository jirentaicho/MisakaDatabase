package database;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class MisakaDatabase{

    private static final Map<Class<?>, Table<?,?>> tables = new HashMap<>();

    private static final MisakaDatabase misakaDatabase = new MisakaDatabase();

    private MisakaDatabase(){}

    public static MisakaDatabase getDatabase(){
        if(MisakaDatabase.misakaDatabase != null){
            return MisakaDatabase.misakaDatabase;
        } else {
            throw new RuntimeException("MisakaDatabase creation failed.");
        }
    }

    public <I,T> void setupTableMap(Class<?> clazz, Map<I,T> map){
        // Already holds a table of the same type
        if(Objects.nonNull(MisakaDatabase.tables.get(clazz))) {
            Table<I,T> currentTable = (Table<I, T>) MisakaDatabase.tables.get(clazz);
            currentTable.getMapClone().putAll(map);
        } else {
            // create table after add to misaka database
            Table<I, T> table = Table.of(map);
            MisakaDatabase.tables.put(clazz, table);
        }
    }

    public <I,T> Table<I,T> getTable(Class<T> clazz){
        if(Objects.isNull(MisakaDatabase.tables.get(clazz))){
            Table<I, T> table = Table.of(new HashMap<>());
            MisakaDatabase.tables.put(clazz,table);
        }
        return (Table<I, T>) MisakaDatabase.tables.get(clazz);
    }
}