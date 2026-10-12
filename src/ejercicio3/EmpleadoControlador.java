package ejercicio3;

import java.io.IOException;
import java.util.List;

public class EmpleadoControlador {

    private final EmpleadoModelo modelo;
    private final EmpleadoVista vista;

    public EmpleadoControlador(EmpleadoModelo modelo, EmpleadoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {

        listar();

        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.pedirEntero("Opcion: ");
            switch (opcion) {
                case 1: listar(); break;
                case 2: agregar(); break;
                case 3: buscar(); break;
                case 4: eliminar(); break;
                case 5: vista.mostrarMensaje("Programa finalizado."); break;
                default: vista.mostrarError("Opcion no valida.");
            }
        } while (opcion != 5);
    }

    private void listar() {
        try {
            List<Empleado> lista = modelo.leerEmpleados();
            vista.mostrarEmpleados(lista);
        } catch (IOException e) {
            vista.mostrarError("No se pudo leer el archivo: " + e.getMessage());
        }
    }

    private void agregar() {
        int numero = vista.pedirEntero("Numero: ");
        String nombre = vista.pedirTexto("Nombre: ");
        double sueldo = vista.pedirDecimal("Sueldo: ");
        try {
            modelo.agregarEmpleado(new Empleado(numero, nombre, sueldo));
            vista.mostrarMensaje("Operacion exitosa: empleado agregado.");
        } catch (IllegalArgumentException e) {
            vista.mostrarError(e.getMessage());
        } catch (IOException e) {
            vista.mostrarError("No se pudo escribir en el archivo: " + e.getMessage());
        }
    }

    private void buscar() {
        int numero = vista.pedirEntero("Numero a buscar: ");
        try {
            Empleado e = modelo.buscarEmpleado(numero);
            if (e == null) {
                vista.mostrarError("No existe un empleado con el numero " + numero);
            } else {
                vista.mostrarMensaje("Operacion exitosa: empleado encontrado.");
                vista.mostrarEmpleado(e);
            }
        } catch (IOException e) {
            vista.mostrarError("No se pudo leer el archivo: " + e.getMessage());
        }
    }

    private void eliminar() {
        int numero = vista.pedirEntero("Numero a eliminar: ");
        try {
            if (modelo.eliminarEmpleado(numero)) {
                vista.mostrarMensaje("Operacion exitosa: empleado eliminado.");
            } else {
                vista.mostrarError("No existe un empleado con el numero " + numero);
            }
        } catch (IOException e) {
            vista.mostrarError("No se pudo actualizar el archivo: " + e.getMessage());
        }
    }
}
