import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Gestor {

    private final String archivo;
    private final List<Personaje> personajes = new ArrayList<>();

    public Gestor(String archivo) {
        this.archivo = archivo;
        cargar();
    }


    public boolean anadir(Personaje p) {
        if (buscar(p.getNombre()) != null) {
            return false;
        }
        personajes.add(p);
        guardar();
        return true;
    }

    public boolean modificar(String nombre, int vida, int ataque, int defensa, int alcance) {
        Personaje p = buscar(nombre);
        if (p == null) {
            return false;
        }
        p.setVida(vida);
        p.setAtaque(ataque);
        p.setDefensa(defensa);
        p.setAlcance(alcance);
        guardar();
        return true;
    }

    public boolean borrar(String nombre) {
        Personaje p = buscar(nombre);
        if (p == null) {
            return false;
        }
        personajes.remove(p);
        guardar();
        return true;
    }

    public Personaje buscar(String nombre) {
        for (Personaje p : personajes) {
            if (p.getNombre().equalsIgnoreCase(nombre.trim())) {
                return p;
            }
        }
        return null;
    }

    public void mostrar() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        for (Personaje p : personajes) {
            System.out.println(p);
        }
    }

    private void cargar() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                try {
                    personajes.add(Personaje.desdeLinea(linea));
                } catch (IllegalArgumentException e) {
                    System.out.println("Linea ignorada (" + e.getMessage() + ")");
                }
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Archivo no encontrado, se creara uno nuevo: " + archivo);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void guardar() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                pw.println(p.toLineaArchivo());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
