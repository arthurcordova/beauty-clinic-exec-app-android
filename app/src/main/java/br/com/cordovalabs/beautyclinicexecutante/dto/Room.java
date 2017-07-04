package br.com.cordovalabs.beautyclinicexecutante.dto;

import java.io.Serializable;

/**
 * Created by acstapassoli on 03/07/17.
 */

public class Room implements Serializable {

    private Long codSala;

    private String descricao;

    public Long getCodSala() {
        return codSala;
    }

    public void setCodSala(Long codSala) {
        this.codSala = codSala;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
