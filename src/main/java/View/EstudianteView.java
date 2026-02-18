package View;

import Model.Estudiante;
import java.util.List;
import java.util.Scanner;

public class EstudianteView {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\nMENU PRINCIPAL");
        System.out.println("1. Registrar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Buscar estudiante por ID");
        System.out.println("4. Modificar estudiante");
        System.out.println("5. Eliminar estudiante");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public String leerOpcion() {
        return scanner.nextLine().trim();
    }

    public String leerCampo(String etiqueta) {
        System.out.print(etiqueta + ": ");
        return scanner.nextLine();
    }

    public void mostrarEstudiante(Estudiante e) {
        System.out.println("ID: " + e.getId());
        System.out.println("Nombre: " + e.getNombre());
        System.out.println("Correo: " + e.getCorreo());
        System.out.println("Programa academico: " + e.getProgramaAcademico());
    }

    public void mostrarLista(List<Estudiante> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante e : lista) {
            System.out.println(e);
        }
        System.out.println("Total: " + lista.size() + " estudiante(s).");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public String leerConfirmacion() {
        System.out.print("Esta seguro? (s/n): ");
        return scanner.nextLine().trim().toLowerCase();
    }

    public void cerrarScanner() {
        scanner.close();
    }
}