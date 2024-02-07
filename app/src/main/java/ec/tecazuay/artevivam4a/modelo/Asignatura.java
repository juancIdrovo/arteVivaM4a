package ec.tecazuay.artevivam4a.modelo;

public class Asignatura {

    private Long idAsignatura;
    private String nombre;
    private String descripcion;
    private Long codigoCategorias;
    private String ced_profesor_fk;

    public Asignatura() {
    }

    public Asignatura(Long idAsignatura, String nombre, String descripcion, Long codigoCategorias, String ced_profesor_fk) {
        this.idAsignatura = idAsignatura;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigoCategorias = codigoCategorias;
        this.ced_profesor_fk = ced_profesor_fk;
    }

    public Long getCodigoAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Long idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCodigoCategorias() {
        return codigoCategorias;
    }

    public void setCodigoCategorias(Long codigoCategorias) {
        this.codigoCategorias = codigoCategorias;
    }

    public String getCed_profesor_fk() {
        return ced_profesor_fk;
    }

    public void setCed_profesor_fk(String ced_profesor_fk) {
        this.ced_profesor_fk = ced_profesor_fk;
    }
}
