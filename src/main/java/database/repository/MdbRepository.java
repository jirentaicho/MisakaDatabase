package database.repository;

import database.MisakaEntity;

public class MdbRepository<I,T> extends MisakaRepositoryBase<I,T>{
    public MdbRepository(Class<T> clazz) {
        super(clazz);
    }
}
