package br.com.matheush.apptodolist.dao;

import android.util.Log;

import java.util.List;

import br.com.matheush.apptodolist.MyApplication;
import br.com.matheush.apptodolist.model.Tarefa;
import io.realm.Realm;

/**
 * Created by matheush on 21/02/17.
 */

public class TarefaDao implements IDao<Tarefa> {
    private Realm realm = MyApplication.REALM;

    @Override
    public void setObjeto(Tarefa objeto) {
        realm.beginTransaction();

        Tarefa tarefa = realm.createObject(Tarefa.class, idGenerator(Tarefa.class, "id"));

        tarefa.setTarefa(objeto.getTarefa());
        tarefa.setDataHora(objeto.getDataHora());

        realm.commitTransaction();
    }

    @Override
    public void deleteObjeto(Tarefa objeto) {

    }

    @Override
    public List<Tarefa> getObjetos() {
    }

    @Override
    public Tarefa getObjeto(long id) {
        return null;
    }

    @Override
    public int idGenerator(Class classe, String campo) {
        try {
            return realm.where(classe).max(campo).intValue()+1;
        } catch (Exception e) {
            Log.d("LogX", String.valueOf(e));
            return 0;
        }
    }
}
