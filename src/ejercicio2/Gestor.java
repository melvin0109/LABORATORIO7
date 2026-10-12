package ejercicio2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Gestor {

    private static final String[] NOMBRES_ALEATORIOS = {"Mago", "Ladron", "Paladin", "Druida", "Monje", "Bardo"};

    private final String archivo;
    private final List<Personaje> personajes = new ArrayList<>();
    private final Random random = new Random();

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
        if (p == null) return false;
        p.setVida(vida);
        p.setAtaque(ataque);
        p.setDefensa(defensa);
        p.setAlcance(alcance);
        guardar();
        return true;
    }

    public boolean borrar(String nombre) {
        Personaje p = buscar(nombre);
        if (p == null) return false;
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
        mostrarLista(personajes);
    }

    private void mostrarLista(List<Personaje> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay personajes registrados.");
            return;
        }
        for (Personaje p : lista) {
            System.out.println(p);
        }
    }

    public void mostrarOrdenadosPor(String atributo) {
        List<Personaje> copia = new ArrayList<>(personajes);
        copia.sort(Comparator.comparingInt((Personaje p) -> p.getAtributo(atributo)).reversed());
        mostrarLista(copia);
    }

    public int cargarAleatorios(int cantidad) {
        int creados = 0;
        for (int i = 0; i < cantidad; i++) {
            String nombre = NOMBRES_ALEATORIOS[random.nextInt(NOMBRES_ALEATORIOS.length)];
            Personaje p = new Personaje(nombre, valorAleatorio(), valorAleatorio(),
                    valorAleatorio(), valorAleatorio());
            if (anadir(p)) {
                creados++;
            }
        }
        return creados;
    }

    private int valorAleatorio() {
        return random.nextInt(8) + 1;
    }

    public boolean actualizarAtributo(String nombre, String atributo, int valor) {
        Personaje p = buscar(nombre);
        if (p == null) return false;
        p.setAtributo(atributo, valor);
        guardar();
        return true;
    }

    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para calcular estadisticas.");
            return;
        }
        int n = personajes.size();
        double vida = 0, ataque = 0, defensa = 0, alcance = 0, nivel = 0;
        for (Personaje p : personajes) {
            vida += p.getVida();
            ataque += p.getAtaque();
            defensa += p.getDefensa();
            alcance += p.getAlcance();
            nivel += p.getNivel();
        }
        System.out.println("Total de personajes: " + n);
        System.out.printf("Promedio vida: %.2f%n", vida / n);
        System.out.printf("Promedio ataque: %.2f%n", ataque / n);
        System.out.printf("Promedio defensa: %.2f%n", defensa / n);
        System.out.printf("Promedio alcance: %.2f%n", alcance / n);
        System.out.printf("Promedio nivel: %.2f%n", nivel / n);
    }

    public int importar(String rutaOtroArchivo) {
        int importados = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(rutaOtroArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;
                try {
                    if (anadir(Personaje.desdeLinea(linea))) {
                        importados++;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Linea ignorada: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer el archivo: " + e.getMessage());
            return -1;
        }
        return importados;
    }

    public boolean subirNivel(String nombre) {
        Personaje p = buscar(nombre);
        if (p == null) return false;
        p.subirNivel();
        guardar();
        return true;
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
