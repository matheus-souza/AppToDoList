package br.com.matheush.apptodolist.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by matheush on 18/02/17.
 */

public class Tarefa extends RealmObject{
    @PrimaryKey
    private int id;
    private String tarefa;
    private String data;
    private String hora;

    public Tarefa() {
    }

    public Tarefa(int id, String tarefa, String data, String hora) {
        this.id = id;
        this.tarefa = tarefa;
        this.data = data;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarefa() {
        return tarefa;
    }

    public void setTarefa(String tarefa) {
        this.tarefa = tarefa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", tarefa='" + tarefa + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tarefa tarefa1 = (Tarefa) o;

        if (id != tarefa1.id) return false;
        if (tarefa != null ? !tarefa.equals(tarefa1.tarefa) : tarefa1.tarefa != null) return false;
        if (data != null ? !data.equals(tarefa1.data) : tarefa1.data != null) return false;
        return hora != null ? hora.equals(tarefa1.hora) : tarefa1.hora == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tarefa != null ? tarefa.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (hora != null ? hora.hashCode() : 0);
        return result;
    }
}
