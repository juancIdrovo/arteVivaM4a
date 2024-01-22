package ec.tecazuay.artevivam4a.modelo;

public class Estudiante extends  Persona {

    private String cedula_estudiante_fk;

    public Estudiante(Long cod_estudiante_pk, String cedula_estudiante_fk) {

        this.cedula_estudiante_fk = cedula_estudiante_fk;
    }

    public Estudiante() {

    }

    public String getCedula_estudiante_fk() {
        return cedula_estudiante_fk;
    }

    //hola care cola

    public void setCedula_estudiante_fk(String cedula_estudiante_fk) {
        this.cedula_estudiante_fk = cedula_estudiante_fk;
    }
}
