package br.com.matheush.apptodolist.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by matheush on 18/02/17.
 */

public class Tarefa extends RealmObject {
    @PrimaryKey
    private int id;
    private String tarefa;
    private String data;
    private String hora;
    private String dataConclusao;
    private String horaConclusao;
    private boolean ativa;

    public Tarefa() {
    }

    public Tarefa(int id, String tarefa, String data, String hora, String dataConclusao, String horaConclusao, boolean ativa) {
        this.id = id;
        this.tarefa = tarefa;
        this.data = data;
        this.hora = hora;
        this.dataConclusao = dataConclusao;
        this.horaConclusao = horaConclusao;
        this.ativa = ativa;
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

    public String getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(String dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public String getHoraConclusao() {
        return horaConclusao;
    }

    public void setHoraConclusao(String horaConclusao) {
        this.horaConclusao = horaConclusao;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", tarefa='" + tarefa + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                ", dataConclusao='" + dataConclusao + '\'' +
                ", horaConclusao='" + horaConclusao + '\'' +
                ", ativa=" + ativa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tarefa tarefa1 = (Tarefa) o;

        if (id != tarefa1.id) return false;
        if (ativa != tarefa1.ativa) return false;
        if (tarefa != null ? !tarefa.equals(tarefa1.tarefa) : tarefa1.tarefa != null) return false;
        if (data != null ? !data.equals(tarefa1.data) : tarefa1.data != null) return false;
        if (hora != null ? !hora.equals(tarefa1.hora) : tarefa1.hora != null) return false;
        if (dataConclusao != null ? !dataConclusao.equals(tarefa1.dataConclusao) : tarefa1.dataConclusao != null)
            return false;
        return horaConclusao != null ? horaConclusao.equals(tarefa1.horaConclusao) : tarefa1.horaConclusao == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tarefa != null ? tarefa.hashCode() : 0);
        result = 31 * result + (data != null ? data.hashCode() : 0);
        result = 31 * result + (hora != null ? hora.hashCode() : 0);
        result = 31 * result + (dataConclusao != null ? dataConclusao.hashCode() : 0);
        result = 31 * result + (horaConclusao != null ? horaConclusao.hashCode() : 0);
        result = 31 * result + (ativa ? 1 : 0);
        return result;
    }
}