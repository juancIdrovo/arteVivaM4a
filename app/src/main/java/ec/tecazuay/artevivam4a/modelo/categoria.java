package ec.tecazuay.artevivam4a.modelo;

public class categoria {
    private Long codigoCategoria;
    private String descripcion;
    private String nombre;

    public categoria() {
    }

    public categoria(Long codigoCategoria, String descripcion, String nombre) {
        this.codigoCategoria = codigoCategoria;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public Long getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(Long codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
