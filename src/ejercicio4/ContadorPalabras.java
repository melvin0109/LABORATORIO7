package ejercicio4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class ContadorPalabras {

    private static final int TOP_PALABRAS = 5;

    public static void main(String[] args) {
        File archivo = (args.length > 0) ? new File(args[0]) : null;

        while (true) {
            if (archivo == null) {
                archivo = elegirArchivo();
                if (archivo == null) {
                    System.out.println("No se selecciono ningun archivo. Programa finalizado.");
                    return;
                }
            }
            try {
                analizar(archivo);
                return;
            } catch (IOException e) {
                String msg = "El archivo no existe o no se puede leer: " + archivo.getName();
                System.out.println(msg);
                if (args.length > 0) return;
                JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
                archivo = null;
            }
        }
    }

    private static File elegirArchivo() {
        JFileChooser selector = new JFileChooser();
        int resultado = selector.showOpenDialog(null);
        return (resultado == JFileChooser.APPROVE_OPTION) ? selector.getSelectedFile() : null;
    }

    private static void analizar(File archivo) throws IOException {
        int totalLineas = 0;
        int totalPalabras = 0;
        int totalCaracteres = 0;
        Map<String, Integer> frecuencias = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                totalLineas++;
                totalCaracteres += linea.length();

                for (String palabra : extraerPalabras(linea)) {
                    totalPalabras++;
                    frecuencias.merge(palabra.toLowerCase(), 1, Integer::sum);
                }
            }
        }

        double promedio = (totalLineas == 0) ? 0 : (double) totalPalabras / totalLineas;

        System.out.println("Archivo: " + archivo.getName());
        System.out.println("Total de lineas: " + totalLineas);
        System.out.println("Total de palabras: " + totalPalabras);
        System.out.println("Total de caracteres: " + totalCaracteres);
        System.out.printf("Promedio de palabras por linea: %.2f%n", promedio);
        mostrarMasFrecuentes(frecuencias);
    }

    private static List<String> extraerPalabras(String linea) {
        List<String> palabras = new ArrayList<>();
        StringBuilder actual = new StringBuilder();
        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                actual.append(c);
            } else if (actual.length() > 0) {
                palabras.add(actual.toString());
                actual.setLength(0);
            }
        }
        if (actual.length() > 0) {
            palabras.add(actual.toString());
        }
        return palabras;
    }

    private static void mostrarMasFrecuentes(Map<String, Integer> frecuencias) {
        List<Map.Entry<String, Integer>> lista = new ArrayList<>(frecuencias.entrySet());
        lista.sort((a, b) -> {
            int porConteo = b.getValue().compareTo(a.getValue());
            return (porConteo != 0) ? porConteo : a.getKey().compareTo(b.getKey());
        });

        System.out.println("Palabras mas frecuentes:");
        int limite = Math.min(TOP_PALABRAS, lista.size());
        for (int i = 0; i < limite; i++) {
            System.out.println("  " + lista.get(i).getKey() + " : " + lista.get(i).getValue());
        }
    }
}
