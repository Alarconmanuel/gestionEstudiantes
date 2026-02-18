package Controller;

import Model.Estudiante;
import service.EstudianteService;
import View.EstudianteView;
import java.util.List;

public class EstudianteController {

    private final EstudianteService servicio;
    private final EstudianteView vista;


    public EstudianteController(EstudianteService servicio, EstudianteView vista) {
        this.servicio = servicio;
        this.vista = vista;
    }

    public void ejecutar() {
        System.out.println("SISTEMA DE GESTION DE ESTUDIANTES");

        boolean continuar = true;
        while (continuar) {
            vista.mostrarMenu();
            String opcion = vista.leerOpcion();

            switch (opcion) {
                case "1" -> registrarEstudiante();
                case "2" -> listarEstudiantes();
                case "3" -> buscarEstudiante();
                case "4" -> modificarEstudiante();
                case "5" -> eliminarEstudiante();
                case "0" -> {
                    continuar = false;
                    vista.mostrarMensaje("Sesion finalizada. Hasta luego.");
                }
                default -> vista.mostrarMensaje("Opcion no valida. Intente nuevamente.");
            }
        }
        vista.cerrarScanner();
    }

    private void registrarEstudiante() {
        vista.mostrarMensaje("\nREGISTRAR ESTUDIANTE");
        try {
            String nombre            = vista.leerCampo("Nombre");
            String correo            = vista.leerCampo("Correo electronico");
            String programaAcademico = vista.leerCampo("Programa academico");

            Estudiante nuevo = servicio.registrar(nombre, correo, programaAcademico);
            vista.mostrarMensaje("Estudiante registrado exitosamente.");
            vista.mostrarMensaje("ID asignado: " + nuevo.getId());
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void listarEstudiantes() {
        vista.mostrarMensaje("\nLISTADO DE ESTUDIANTES");
        List<Estudiante> lista = servicio.listarTodos();
        vista.mostrarLista(lista);
    }

    private void buscarEstudiante() {
        vista.mostrarMensaje("\nBUSCAR ESTUDIANTE");
        try {
            String id = vista.leerCampo("ID del estudiante");
            Estudiante e = servicio.buscarPorId(id);
            vista.mostrarEstudiante(e);
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }

    private void modificarEstudiante() {
        vista.mostrarMensaje("\nMODIFICAR ESTUDIANTE");
        try {
            String id                     = vista.leerCampo("ID del estudiante a modificar");
            String nuevoNombre            = vista.leerCampo("Nuevo nombre");
            String nuevoCorreo            = vista.leerCampo("Nuevo correo electronico");
            String nuevoProgramaAcademico = vista.leerCampo("Nuevo programa academico");

            servicio.modificar(id, nuevoNombre, nuevoCorreo, nuevoProgramaAcademico);
            vista.mostrarMensaje("Estudiante modificado exitosamente.");
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje("Error: " + e.getMessage());
        }
    }

    private void eliminarEstudiante() {
        vista.mostrarMensaje("\nELIMINAR ESTUDIANTE");
        try {
            String id = vista.leerCampo("ID del estudiante a eliminar");
            String confirmacion = vista.leerConfirmacion();

            if (confirmacion.equals("s")) {
                servicio.eliminar(id);
                vista.mostrarMensaje("Estudiante eliminado exitosamente.");
            } else {
                vista.mostrarMensaje("Operacion cancelada.");
            }
        } catch (IllegalArgumentException e) {
            vista.mostrarMensaje(e.getMessage());
        }
    }
}