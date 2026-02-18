package service;

import Model.Estudiante;
import java.util.List;

public interface EstudianteService {

    Estudiante registrar(String nombre, String correo, String programaAcademico);
    List<Estudiante> listarTodos();
    Estudiante buscarPorId(String id);
    void modificar(String id, String nuevoNombre, String nuevoCorreo, String nuevoProgramaAcademico);
    void eliminar(String id);
}