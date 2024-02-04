package ec.tecazuay.artevivam4a.modelo;

import java.util.Date;

public class Matricula {

    private Long cod_matricula_pk;
    private Date fecha_ini;
    private Date feche_fin;
    private int cupo;
    private double calificacion;
    private int asistencias;
    private String estado;
    private String horario;
    private String ced_estudiante_fk;
    private Long codigoAsignatura;

    public Matricula() {
    }

    public Matricula(Long cod_matricula_pk, Date fecha_ini, Date feche_fin, int cupo, double calificacion, int asistencias, String estado, String horario, String ced_estudiante_fk, Long codigoAsignatura) {
        this.cod_matricula_pk = cod_matricula_pk;
        this.fecha_ini = fecha_ini;
        this.feche_fin = feche_fin;
        this.cupo = cupo;
        this.calificacion = calificacion;
        this.asistencias = asistencias;
        this.estado = estado;
        this.horario = horario;
        this.ced_estudiante_fk = ced_estudiante_fk;
        this.codigoAsignatura = codigoAsignatura;
    }

    public Long getCod_matricula_pk() {
        return cod_matricula_pk;
    }

    public void setCod_matricula_pk(Long cod_matricula_pk) {
        this.cod_matricula_pk = cod_matricula_pk;
    }

    public Date getFecha_ini() {
        return fecha_ini;
    }

    public void setFecha_ini(Date fecha_ini) {
        this.fecha_ini = fecha_ini;
    }

    public Date getFeche_fin() {
        return feche_fin;
    }

    public void setFeche_fin(Date feche_fin) {
        this.feche_fin = feche_fin;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCed_estudiante_fk() {
        return ced_estudiante_fk;
    }

    public void setCed_estudiante_fk(String ced_estudiante_fk) {
        this.ced_estudiante_fk = ced_estudiante_fk;
    }

    public Long getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(Long codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }
}
