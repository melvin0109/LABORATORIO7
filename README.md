# Practica 07 - Manejo de archivos en Java

Lenguajes de Programacion III - UCSM. Un solo proyecto de Eclipse con los 4 ejercicios propuestos (Java 8 o superior, solo librerias estandar).

## Estructura

| Paquete | Clase principal | Descripcion |
|---|---|---|
| `ejercicio1` | `Main` | Gestor de personajes (archivo `personajes_ej1.txt`) |
| `ejercicio2` | `Main` | Gestor ampliado: ordenar, aleatorios, actualizar atributo, estadisticas, importar, niveles (`personajes_ej2.txt`) |
| `ejercicio3` | `Main` | Empleados con MVC (`empleados.txt`) |
| `ejercicio4` | `ContadorPalabras` | Contador de palabras con `JFileChooser` (`lear.txt` de ejemplo) |

## Abrir en Eclipse

`File > Import > General > Existing Projects into Workspace` y elegir esta carpeta.
Ejecutar con clic derecho sobre la clase principal > `Run As > Java Application`.

Los archivos de datos se crean en la raiz del proyecto al ejecutar y estan en `.gitignore`.
`importar_ejemplo.txt` sirve para probar la opcion de importar del ejercicio 2 (formato `nombre;vida;ataque;defensa;alcance[;nivel]`).

## Constantes faciles de modificar

- `ejercicio2.Personaje.BONO_POR_NIVEL`: puntos que gana cada atributo al subir de nivel.
- `ejercicio2.Gestor.NOMBRES_ALEATORIOS`: nombres para personajes aleatorios.
- `ejercicio4.ContadorPalabras.TOP_PALABRAS`: cuantas palabras frecuentes se muestran.
