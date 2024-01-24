package ec.tecazuay.artevivam4a;

import java.io.Serializable;

public class LoginResponse implements Serializable {
private String cedula;
private String cedula_estudiante_fk;
    private String nombres;
    private String apellidos;
    private String correo;
    private String direccion;
    private String telf;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelf() {
        return telf;
    }

    public String getCedula_estudiante_fk() {
        return cedula_estudiante_fk;
    }

    public void setCedula_estudiante_fk(String cedula_estudiante_fk) {
        this.cedula_estudiante_fk = cedula_estudiante_fk;
    }

    public void setTelf(String telf) {
        this.telf = telf;

    }
}
