package ejercicio2;

public class Personaje {

    private static final int BONO_POR_NIVEL = 1;

    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;
    private int nivel;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        this(nombre, vida, ataque, defensa, alcance, 1);
    }

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance, int nivel) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
        setAlcance(alcance);
        this.nivel = validar("Nivel", nivel);
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
    public int getNivel()     { return nivel; }

    public void setVida(int vida)       { this.vida = validar("Vida", vida); }
    public void setAtaque(int ataque)   { this.ataque = validar("Ataque", ataque); }
    public void setDefensa(int defensa) { this.defensa = validar("Defensa", defensa); }
    public void setAlcance(int alcance) { this.alcance = validar("Alcance", alcance); }

    public void subirNivel() {
        nivel++;
        vida += BONO_POR_NIVEL;
        ataque += BONO_POR_NIVEL;
        defensa += BONO_POR_NIVEL;
        alcance += BONO_POR_NIVEL;
    }

    public int getAtributo(String atributo) {
        switch (atributo.toLowerCase()) {
            case "vida":    return vida;
            case "ataque":  return ataque;
            case "defensa": return defensa;
            case "alcance": return alcance;
            case "nivel":   return nivel;
            default: throw new IllegalArgumentException("Atributo desconocido: " + atributo);
        }
    }

    public void setAtributo(String atributo, int valor) {
        switch (atributo.toLowerCase()) {
            case "vida":    setVida(valor);    break;
            case "ataque":  setAtaque(valor);  break;
            case "defensa": setDefensa(valor); break;
            case "alcance": setAlcance(valor); break;
            default: throw new IllegalArgumentException("Atributo desconocido: " + atributo);
        }
    }

    public String toLineaArchivo() {
        return nombre + ";" + vida + ";" + ataque + ";" + defensa + ";" + alcance + ";" + nivel;
    }

    public static Personaje desdeLinea(String linea) {
        String[] p = linea.split(";");
        if (p.length != 5 && p.length != 6) {
            throw new IllegalArgumentException("Linea invalida: " + linea);
        }
        int nivel = (p.length == 6) ? Integer.parseInt(p[5].trim()) : 1;
        return new Personaje(p[0], Integer.parseInt(p[1].trim()), Integer.parseInt(p[2].trim()),
                Integer.parseInt(p[3].trim()), Integer.parseInt(p[4].trim()), nivel);
    }

    @Override
    public String toString() {
        return String.format("%-12s Nivel: %-2d Vida: %-3d Ataque: %-3d Defensa: %-3d Alcance: %-3d",
                nombre, nivel, vida, ataque, defensa, alcance);
    }
}
