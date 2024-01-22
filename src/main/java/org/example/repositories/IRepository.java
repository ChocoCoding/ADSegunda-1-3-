package org.example.repositories;

public interface IRepository<T>{

    void guardar(T t);
    T findbyId(Long id);
    void eliminar(Long id);

}
