package br.com.matheush.apptodolist.dao;

import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by matheush on 22/02/17.
 */

public interface IDao<T extends RealmModel> {
    public void setObjeto(T objeto);
    public void deleteObjeto(T objeto);
    public void deleteObjetos();
    public void atualizaObjeto(T objeto);
    public RealmResults<T> getObjetos();
    public T getObjeto(int id);
    public int idGenerator(Class classe, String campo);
}
