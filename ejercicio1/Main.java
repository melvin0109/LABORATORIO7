import java.util.Scanner;


public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Gestor gestor = new Gestor("personajes.txt");


        gestor.anadir(new Personaje("Caballero", 4, 2, 4, 2));
        gestor.anadir(new Personaje("Guerrero", 2, 4, 2, 4));
        gestor.anadir(new Personaje("Arquero", 2, 4, 1, 8));

        int opcion;
        do {
            System.out.println("\n=== GESTOR DE PERSONAJES ===");
            System.out.println("1. Mostrar personajes");
            System.out.println("2. Anadir personaje");
            System.out.println("3. Modificar personaje");
            System.out.println("4. Borrar personaje");
            System.out.println("0. Salir");
            opcion = leerEntero("Opcion: ");

            switch (opcion) {
                case 1: gestor.mostrar(); break;
                case 2: anadir(gestor); break;
                case 3: modificar(gestor); break;
                case 4: borrar(gestor); break;
                case 0: System.out.println("Hasta luego."); break;
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
