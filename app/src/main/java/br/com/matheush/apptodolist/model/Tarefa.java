package br.com.matheush.apptodolist.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static android.R.attr.id;

/**
 * Created by matheush on 18/02/17.
 */

public class Tarefa extends RealmObject{
    @PrimaryKey
    private long id;
    private String tarefa;

    public Tarefa() {
    }

    public Tarefa(long id, String tarefa) {
        this.id = id;
        this.tarefa = tarefa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }
}
