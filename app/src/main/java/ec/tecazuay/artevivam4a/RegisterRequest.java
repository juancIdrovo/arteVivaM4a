package ec.tecazuay.artevivam4a;

public class RegisterRequest {
    private String cedula;
    private String cedula_estudiante_fk;
    private String nombres;
    private String apellidos;
    private String correo;
    private String direccion;
    private String contrasenia;
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