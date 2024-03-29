package br.com.cordovalabs.beautyclinicexecutante.dto;

import java.io.Serializable;

/**
 * Created by acstapassoli on 10/07/17.
 */

public class Schedule implements Serializable{

    private String data;
    private String produto;
    private String cliente;
    private String idAgenda;
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

    public String getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(String idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
}
