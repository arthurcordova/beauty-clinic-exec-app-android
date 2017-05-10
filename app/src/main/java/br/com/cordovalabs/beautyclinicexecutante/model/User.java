package br.com.cordovalabs.beautyclinicexecutante.model;

import java.io.Serializable;

/**
 * Created by acstapassoli on 09/05/17.
 */

public class User implements Serializable {

    private Integer codigo;
    private String nome;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
