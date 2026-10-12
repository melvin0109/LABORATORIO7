package ejercicio3;

import java.util.List;
import java.util.Scanner;

public class EmpleadoVista {

    private final Scanner sc = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n=== EMPLEADOS ===");
        System.out.println("1. Listar todos los empleados");
        System.out.println("2. Agregar un nuevo empleado");
        System.out.println("3. Buscar un empleado por numero");
        System.out.println("4. Eliminar un empleado por numero");
        System.out.println("5. Salir");
    }

    public void mostrarEmpleados(List<Empleado> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (Empleado e : lista) {
            System.out.println(e);
        }
    }

    public void mostrarEmpleado(Empleado e) {
        System.out.println(e);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.out.println("ERROR: " + mensaje);
    }

    public int pedirEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                mostrarError("Debe ingresar un numero entero.");
            }
        }
    }

    public double pedirDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                double valor = Double.parseDouble(sc.nextLine().trim());
                if (valor >= 0) {
                    return valor;
                }
                mostrarError("El sueldo no puede ser negativo.");
            } catch (NumberFormatException e) {
                mostrarError("Debe ingresar un numero valido.");
            }
        }
    }

    public String pedirTexto(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (texto.isEmpty()) {
                mostrarError("El texto no puede estar vacio.");
            } else if (texto.contains(";")) {
                mostrarError("El texto no puede contener ';'.");
            } else {
                return texto;
            }
        }
    }
}
