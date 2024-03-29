package ec.tecazuay.artevivam4a.modelo;

public class Horarioss {
    private Long codigoHorarios;
    private String hora_Inicio;
    private String hora_fin;
    private String dia;
    private Profesor profesor;
    private Asignatura asignatura;
    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public Long getCodigoHorarios() {
        return codigoHorarios;
    }

    public void setCodigoHorarios(Long codigoHorarios) {
        this.codigoHorarios = codigoHorarios;
    }

    public String getHora_Inicio() {
        return hora_Inicio;
    }

    public void setHora_Inicio(String hora_Inicio) {
        this.hora_Inicio = hora_Inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    private String nombreAsignatura; // Nuevo campo para almacenar el nombre de la asignatura

    // Getters y setters para el nuevo campo
    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public Long getCodigoAsignatura() {
        // Asumiendo que puedes acceder al objeto asignatura y obtener su código
        if (asignatura != null) {
            return asignatura.getCodigoAsignatura();
        } else {
            return null; // O lanza una excepción o devuelve un valor predeterminado según tu lógica
        }
    }

}
