package ejercicio2;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Gestor gestor = new Gestor("personajes_ej2.txt");

        gestor.anadir(new Personaje("Caballero", 4, 2, 4, 2));
        gestor.anadir(new Personaje("Guerrero", 2, 4, 2, 4));
        gestor.anadir(new Personaje("Arquero", 2, 4, 1, 8));

        int opcion;
        do {
            System.out.println("\n=== GESTOR DE PERSONAJES (v2) ===");
            System.out.println("1. Mostrar personajes");
            System.out.println("2. Anadir personaje");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Borrar personaje");
            System.out.println("5. Mostrar ordenados por atributo");
            System.out.println("6. Cargar personajes aleatorios");
            System.out.println("7. Actualizar un solo atributo");
            System.out.println("8. Mostrar estadisticas");
            System.out.println("9. Importar personajes desde archivo");
            System.out.println("10. Subir de nivel");
            System.out.println("0. Salir");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1:  gestor.mostrar(); break;
                case 2:  anadir(gestor); break;
                case 3:  modificar(gestor); break;
                case 4:  borrar(gestor); break;
                case 5:  ordenar(gestor); break;
                case 6:  aleatorios(gestor); break;
                case 7:  actualizarAtributo(gestor); break;
                case 8:  gestor.mostrarEstadisticas(); break;
                case 9:  importar(gestor); break;
                case 10: subirNivel(gestor); break;
                case 0:  System.out.println("Hasta luego."); break;
                default: System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
    }

    private static void anadir(Gestor gestor) {
        try {
            String nombre = leerTexto("Nombre: ");
            Personaje p = new Personaje(nombre, leerEntero("Vida: "), leerEntero("Ataque: "),
                    leerEntero("Defensa: "), leerEntero("Alcance: "));
            System.out.println(gestor.anadir(p) ? "Personaje anadido." : "Ya existe un personaje con ese nombre.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void modificar(Gestor gestor) {
        String nombre = leerTexto("Nombre del personaje a modificar: ");
        if (gestor.buscar(nombre) == null) {
            System.out.println("No existe ese personaje.");
            return;
        }
        try {
            gestor.modificar(nombre, leerEntero("Nueva vida: "), leerEntero("Nuevo ataque: "),
                    leerEntero("Nueva defensa: "), leerEntero("Nuevo alcance: "));
            System.out.println("Personaje modificado.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void borrar(Gestor gestor) {
        String nombre = leerTexto("Nombre del personaje a borrar: ");
        System.out.println(gestor.borrar(nombre) ? "Personaje borrado." : "No existe ese personaje.");
    }

    private static void ordenar(Gestor gestor) {
        String atributo = leerTexto("Atributo (vida, ataque, defensa, alcance, nivel): ");
        try {
            gestor.mostrarOrdenadosPor(atributo);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void aleatorios(Gestor gestor) {
        int cantidad = leerEntero("Cuantos personajes aleatorios: ");
        System.out.println("Personajes creados: " + gestor.cargarAleatorios(cantidad)
                + " (los nombres repetidos se omiten)");
    }

    private static void actualizarAtributo(Gestor gestor) {
        String nombre = leerTexto("Nombre del personaje: ");
        String atributo = leerTexto("Atributo (vida, ataque, defensa, alcance): ");
        int valor = leerEntero("Nuevo valor: ");
        try {
            System.out.println(gestor.actualizarAtributo(nombre, atributo, valor)
                    ? "Atributo actualizado." : "No existe ese personaje.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void importar(Gestor gestor) {
        String ruta = leerTexto("Ruta del archivo a importar: ");
        int n = gestor.importar(ruta);
        if (n >= 0) {
            System.out.println("Personajes importados: " + n);
        }
    }

    private static void subirNivel(Gestor gestor) {
        String nombre = leerTexto("Nombre del personaje: ");
        System.out.println(gestor.subirNivel(nombre) ? "Nivel aumentado." : "No existe ese personaje.");
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero entero.");
            }
        }
    }
}
