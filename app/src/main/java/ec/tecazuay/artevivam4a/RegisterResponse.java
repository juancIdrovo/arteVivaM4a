package ec.tecazuay.artevivam4a;

public class RegisterResponse {

    private String cedula;
    private String cedula_estudiante_fk;
    private String correo;
    private String contrasenia;

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getCedula_estudiante_fk() {
        return cedula_estudiante_fk;
    }

    public void setCedula_estudiante_fk(String cedula_estudiante_fk) {
        this.cedula_estudiante_fk = cedula_estudiante_fk;
    }
}