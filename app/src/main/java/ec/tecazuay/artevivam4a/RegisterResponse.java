package ec.tecazuay.artevivam4a;

public class RegisterResponse {

    private String cedula;
    private String ced_estudiante_fk;
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

    public String getCed_estudiante_fk() {
        return ced_estudiante_fk;
    }

    public void setCed_estudiante_fk(String ced_estudiante_fk) {
        this.ced_estudiante_fk = ced_estudiante_fk;
    }
}