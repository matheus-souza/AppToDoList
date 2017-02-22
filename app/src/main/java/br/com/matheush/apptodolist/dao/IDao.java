package br.com.matheush.apptodolist.dao;

import java.util.List;

/**
 * Created by matheush on 22/02/17.
 */

public interface IDao<T> {
    public void setObjeto(T objeto);
    public void deleteObjeto(T objeto);
    public List<T> getObjetos();
    public T getObjeto(long id);
    public int idGenerator(Class classe, String campo);
}
