package ejercicio3;

public class Empleado {

    private int numero;
    private String nombre;
    private double sueldo;

    public Empleado(int numero, String nombre, double sueldo) {
        this.numero = numero;
        this.nombre = nombre;
        this.sueldo = sueldo;
    }

    public int getNumero()           { return numero; }
    public String getNombre()        { return nombre; }
    public double getSueldo()        { return sueldo; }

    public void setNumero(int numero)      { this.numero = numero; }
    public void setNombre(String nombre)   { this.nombre = nombre; }
    public void setSueldo(double sueldo)   { this.sueldo = sueldo; }

    public String toLineaArchivo() {
        return numero + ";" + nombre + ";" + sueldo;
    }

    public static Empleado desdeLinea(String linea) {
        String[] p = linea.split(";");
        if (p.length != 3) {
            throw new IllegalArgumentException("Linea invalida: " + linea);
        }
        return new Empleado(Integer.parseInt(p[0].trim()), p[1].trim(), Double.parseDouble(p[2].trim()));
    }

    @Override
    public String toString() {
        return String.format("Numero: %-5d Nombre: %-20s Sueldo: %.2f", numero, nombre, sueldo);
    }
}
