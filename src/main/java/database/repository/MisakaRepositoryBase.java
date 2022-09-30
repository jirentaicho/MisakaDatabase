package database.repository;

import database.MisakaDatabase;
import database.Table;

import java.util.*;
import java.util.stream.Collectors;

public abstract class MisakaRepositoryBase<I,T> implements MisakaRepository<I,T>{

    protected final Table<I,T> table;

    public MisakaRepositoryBase(Class<T> clazz){
        this.table = MisakaDatabase.getDatabase().getTable(clazz);
    }

    @Override
    public List<T> findAll() {
        Map<I,T> tableMap = this.table.getMap();
        return tableMap.values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<T> findById(I id) {
        T obj = this.table.getMap().get(id);
        if(Objects.isNull(obj)){
            return Optional.empty();
        }
        return Optional.of(obj);
    }

    @Override
    public boolean insert(I id, T obj) {
        Map<I,T> tableMap = this.table.getMap();
        tableMap.put(id,obj);
        return true;
    }

    @Override
    public boolean insertMap(Map<I,T> map) {
        this.table.getMap().putAll(map);
        return true;
    }

    @Override
    public boolean update(I id,T obj) {
        if(Objects.isNull(this.table.getMap().get(id))){
            return false;
        }
        this.table.getMap().put(id,obj);
        return true;
    }

    @Override
    public boolean updateMap(Map<I,T> map) {
        this.table.getMap().putAll(map);
        return true;
    }

    @Override
    public boolean deleteById(I id) {
        this.table.getMap().remove(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        this.table.getMap().clear();
        return true;
    }

    @Override
    public boolean deleteMap(Map<I,T> map) {
        Set<?> set = new HashSet<>(map.keySet());
        Map<I,T> tableMap = this.table.getMap();
        this.table.getMap().keySet().stream().forEach(i -> {
            if(set.contains(i)){
                tableMap.remove(i);
            }
        });
        return true;
    }
}