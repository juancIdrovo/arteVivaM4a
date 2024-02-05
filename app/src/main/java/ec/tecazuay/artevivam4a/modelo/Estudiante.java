package ec.tecazuay.artevivam4a.modelo;

import java.util.Date;

public class Estudiante extends  Persona {

    private String cedula_estudiante_fk;

    public Estudiante(String cedula_estudiante_fk) {

        this.cedula_estudiante_fk = cedula_estudiante_fk;
    }

    public Estudiante() {

    }

    public Estudiante(String cedula, String nombres, String apellidos, String correo, String direccion, String telf, String foto, Date fecha_nac, String contrasenia, String cedula_estudiante_fk) {
        super(cedula, nombres, apellidos, correo, direccion, telf, foto, fecha_nac, contrasenia);
        this.cedula_estudiante_fk = cedula_estudiante_fk;
    }

    public String getCedula_estudiante_fk() {
        return cedula_estudiante_fk;
    }

    public void setCedula_estudiante_fk(String cedula_estudiante_fk) {
        this.cedula_estudiante_fk = cedula_estudiante_fk;
    }
}
