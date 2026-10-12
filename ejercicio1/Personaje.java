
public class Personaje {

    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
        setAlcance(alcance);
    }

    private static int validar(String campo, int valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser un entero mayor que cero.");
        }
        return valor;
    }

    public String getNombre() { return nombre; }
    public int getVida()      { return vida; }
    public int getAtaque()    { return ataque; }
    public int getDefensa()   { return defensa; }
    public int getAlcance()   { return alcance; }

    public void setVida(int vida)       { this.vida = validar("Vida", vida); }
    public void setAtaque(int ataque)   { this.ataque = validar("Ataque", ataque); }
    public void setDefensa(int defensa) { this.defensa = validar("Defensa", defensa); }
    public void setAlcance(int alcance) { this.alcance = validar("Alcance", alcance); }

    public String toLineaArchivo() {
        return nombre + ";" + vida + ";" + ataque + ";" + defensa + ";" + alcance;
    }

    public static Personaje desdeLinea(String linea) {
        String[] p = linea.split(";");
        if (p.length != 5) {
            throw new IllegalArgumentException("Linea invalida: " + linea);
        }
        return new Personaje(p[0], Integer.parseInt(p[1].trim()), Integer.parseInt(p[2].trim()),
                Integer.parseInt(p[3].trim()), Integer.parseInt(p[4].trim()));
    }

    @Override
    public String toString() {
        return String.format("%-12s Vida: %-3d Ataque: %-3d Defensa: %-3d Alcance: %-3d",
                nombre, vida, ataque, defensa, alcance);
    }
}
