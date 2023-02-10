package database;

@Deprecated
public class MisakaEntity implements Cloneable{
    @Override
    public MisakaEntity clone() {
        try {
            return (MisakaEntity) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
