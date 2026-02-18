package service;

import org.example.Model.Estudiante;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EstudianteServiceImpl implements EstudianteService {

    private final List<Estudiante> repositorio = new ArrayList<>();

    @Override
    public Estudiante registrar(String nombre, String correo, String programaAcademico) {
        validarCamposVacios(nombre, correo, programaAcademico);
        validarCorreo(correo);

        String id = String.valueOf((int)(Math.random() * 9000) + 1000);
        Estudiante estudiante = new Estudiante(id, nombre.trim(), correo.trim(), programaAcademico.trim());
        repositorio.add(estudiante);
        return estudiante;
    }

    @Override
    public List<Estudiante> listarTodos() {
        return new ArrayList<>(repositorio);
    }

    @Override
    public Estudiante buscarPorId(String id) {
        validarNoVacio(id, "El identificador");
        return buscar(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontro ningun estudiante con el ID: " + id));
    }

    @Override
    public void modificar(String id, String nuevoNombre, String nuevoCorreo, String nuevoProgramaAcademico) {
        validarNoVacio(id, "El identificador");
        validarCamposVacios(nuevoNombre, nuevoCorreo, nuevoProgramaAcademico);
        validarCorreo(nuevoCorreo);

        Estudiante e = buscarPorId(id);
        e.setNombre(nuevoNombre.trim());
        e.setCorreo(nuevoCorreo.trim());
        e.setProgramaAcademico(nuevoProgramaAcademico.trim());
    }

    @Override
    public void eliminar(String id) {
        validarNoVacio(id, "El identificador");
        boolean eliminado = repositorio.removeIf(e -> e.getId().equals(id));
        if (!eliminado) {
            throw new IllegalArgumentException("No se encontro ningun estudiante con el ID: " + id);
        }
    }

    private Optional<Estudiante> buscar(String id) {
        return repositorio.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    private void validarCamposVacios(String nombre, String correo, String programa) {
        validarNoVacio(nombre, "El nombre");
        validarNoVacio(correo, "El correo");
        validarNoVacio(programa, "El programa academico");
    }

    private void validarNoVacio(String valor, String etiqueta) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(etiqueta + " no puede estar vacio.");
        }
    }

    private void validarCorreo(String correo) {
        String c = correo.trim();
        int arrobaIndex = c.indexOf('@');
        boolean tieneArroba = arrobaIndex > 0;
        boolean tieneDominio = arrobaIndex < c.length() - 1 && c.lastIndexOf('.') > arrobaIndex;

        if (!tieneArroba || !tieneDominio) {
            throw new IllegalArgumentException(
                    "El correo " + correo + " no tiene formato valido. Ejemplo: usuario@dominio.com");
        }
    }
}