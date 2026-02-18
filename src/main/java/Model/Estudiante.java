package Model;

public class Estudiante {

    private String id;
    private String nombre;
    private String correo;
    private String programaAcademico;

    public Estudiante(String id, String nombre, String correo, String programaAcademico) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.programaAcademico = programaAcademico;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCorreo() { return correo; }
    public String getProgramaAcademico() { return programaAcademico; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setCorreo(String correo) { this.correo = correo; }
    public void setProgramaAcademico(String programaAcademico) { this.programaAcademico = programaAcademico; }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo + " | Programa: " + programaAcademico;
    }
}