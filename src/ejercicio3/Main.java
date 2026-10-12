package ejercicio3;

public class Main {
    public static void main(String[] args) {
        EmpleadoModelo modelo = new EmpleadoModelo("empleados.txt");
        EmpleadoVista vista = new EmpleadoVista();
        new EmpleadoControlador(modelo, vista).iniciar();
    }
}
