package br.com.cordovalabs.beautyclinicexecutante.model;

import java.io.Serializable;

/**
 * Created by acstapassoli on 20/04/17.
 */

public class Execution implements Serializable {

    private String data;
    private String produto;
    private String cliente;
    private Long idAgenda;
    private String horario;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Long getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(Long idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
