package database.repository;

public class MdbRepository<I,T> extends MisakaRepositoryBase<I,T>{
    public MdbRepository(Class<T> clazz) {
        super(clazz);
    }
}
