package interfaces;

import java.util.List;

public interface iGeneric<K> {
    public void save(K entity);
    public void delete(K entity);
    public K find(Object id);
    public List<K> find();
    public void beginTransaction();
    public void commit();
    public void rollback();
    public void close();
}
