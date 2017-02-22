package br.com.matheush.apptodolist.model;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by matheush on 18/02/17.
 */

public class Tarefa extends RealmObject{
    @PrimaryKey
    private int id;
    private String tarefa;
    private Date dataHora;

    public Tarefa() {
    }

    public Tarefa(int id, String tarefa, Date dataHora) {
        this.id = id;
        this.tarefa = tarefa;
        this.dataHora = dataHora;
    }

    public long getId() {
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

    public Date getDataHora() {
        return dataHora;
    }

    public String getDataHoraString() {
        return "teste";
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tarefa tarefa1 = (Tarefa) o;

        if (id != tarefa1.id) return false;
        if (tarefa != null ? !tarefa.equals(tarefa1.tarefa) : tarefa1.tarefa != null) return false;
        return dataHora != null ? dataHora.equals(tarefa1.dataHora) : tarefa1.dataHora == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (tarefa != null ? tarefa.hashCode() : 0);
        result = 31 * result + (dataHora != null ? dataHora.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", tarefa='" + tarefa + '\'' +
                ", dataHora=" + dataHora +
                '}';
    }
}
