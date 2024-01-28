package ec.tecazuay.artevivam4a.modelo;

public class Profesor extends  Persona{

    private String cedula_profesor_fk;
    private String Titulo;

    public String getCedula_profesor_fk() {
        return cedula_profesor_fk;
    }

    public void setCedula_profesor_fk(String cedula_profesor_fk) {
        this.cedula_profesor_fk = cedula_profesor_fk;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }
}
