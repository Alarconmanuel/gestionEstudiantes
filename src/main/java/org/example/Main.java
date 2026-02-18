package org.example;

import Controller.EstudianteController;
import service.EstudianteService;
import service.EstudianteServiceImpl;
import View.EstudianteView;

public class Main {

    public static void main(String[] args) {
        EstudianteService servicio = new EstudianteServiceImpl();
        EstudianteView vista = new EstudianteView();
        EstudianteController controller = new EstudianteController(servicio, vista);
        controller.ejecutar();
    }
}