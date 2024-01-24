package ec.tecazuay.artevivam4a.modelo;

import com.google.type.Date;

public class Tareas {

    private Long codigoTareas;
    private String observaciones;
    private String notas;
    private Long cod_matricula_pk;
    private Date fecharegistro;

    public Long getCodigoTareas() {
        return codigoTareas;
    }

    public void setCodigoTareas(Long codigoTareas) {
        this.codigoTareas = codigoTareas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Long getCod_matricula_pk() {
        return cod_matricula_pk;
    }

    public void setCod_matricula_pk(Long cod_matricula_pk) {
        this.cod_matricula_pk = cod_matricula_pk;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }
}
