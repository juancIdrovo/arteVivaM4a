package ec.tecazuay.artevivam4a.modelo;

public class asigna {
    private int idAsignatura;
    private String nombre;
    private String descripcion;
    private int codigoCategorias;
    private String ced_profesor_fk;

    public int getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(int idAsignatura) {
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

    public int getCodigoCategorias() {
        return codigoCategorias;
    }

    public void setCodigoCategorias(int codigoCategorias) {
        this.codigoCategorias = codigoCategorias;
    }

    public String getCed_profesor_fk() {
        return ced_profesor_fk;
    }

    public void setCed_profesor_fk(String ced_profesor_fk) {
        this.ced_profesor_fk = ced_profesor_fk;
    }
}
