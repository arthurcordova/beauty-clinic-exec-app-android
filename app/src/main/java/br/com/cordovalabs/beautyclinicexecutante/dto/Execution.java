package br.com.cordovalabs.beautyclinicexecutante.dto;

import java.io.Serializable;

/**
 * Created by acstapassoli on 20/04/17.
 */

public class Execution implements Serializable {

    private String codigo;
    private String data;
    private String hrInicio;
    private String hrFim;
    private Long total;
    private Long fixo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHrInicio() {
        return hrInicio;
    }

    public void setHrInicio(String hrInicio) {
        this.hrInicio = hrInicio;
    }

    public String getHrFim() {
        return hrFim;
    }

    public void setHrFim(String hrFim) {
        this.hrFim = hrFim;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getFixo() {
        return fixo;
    }

    public void setFixo(Long fixo) {
        this.fixo = fixo;
    }
}
