package ejercicio3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoModelo {

    private final String archivo;

    public EmpleadoModelo(String archivo) {
        this.archivo = archivo;
    }

    public List<Empleado> leerEmpleados() throws IOException {
        List<Empleado> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                try {
                    lista.add(Empleado.desdeLinea(linea));
                } catch (IllegalArgumentException e) {
                    System.out.println("Linea ignorada en el archivo: " + linea);
                }
            }
        } catch (FileNotFoundException e) {

        }
        return lista;
    }

    public void agregarEmpleado(Empleado nuevo) throws IOException {
        if (buscarEmpleado(nuevo.getNumero()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con el numero " + nuevo.getNumero());
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(nuevo.toLineaArchivo());
        }
    }

    public Empleado buscarEmpleado(int numero) throws IOException {
        for (Empleado e : leerEmpleados()) {
            if (e.getNumero() == numero) {
                return e;
            }
        }
        return null;
    }

    public boolean eliminarEmpleado(int numero) throws IOException {
        List<Empleado> lista = leerEmpleados();
        boolean eliminado = lista.removeIf(e -> e.getNumero() == numero);
        if (eliminado) {
            guardarTodos(lista);
        }
        return eliminado;
    }

    private void guardarTodos(List<Empleado> lista) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Empleado e : lista) {
                pw.println(e.toLineaArchivo());
            }
        }
    }
}
