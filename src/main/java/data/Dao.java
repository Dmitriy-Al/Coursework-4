package data;

import java.util.List;

public interface Dao<T, E> {

    void add(T t);

    List<T> getInfo();

    T getById(E e);

}
