package br.com.cordovalabs.beautyclinicexecutante.dto;

import java.io.Serializable;

/**
 * Created by acstapassoli on 12/07/17.
 */

public class ExecutionMessage implements Serializable {

    private Long codigo;
    private String mensagem;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
