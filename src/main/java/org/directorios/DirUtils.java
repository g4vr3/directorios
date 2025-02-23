package org.directorios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirUtils {

    // Recibe como parámetro el nombre del directorio y
    // Devuelve el número del nivel al que se encuentra el DIRECTORIO más profundo de la jerarquía.
    public static int numNiveles(String dir) {
        File directory = new File(dir); // Obtener dir recibido como param

        // Valida que sea un directorio
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Directorio inválido."); // Excepción que recogerá la función que invoque
        }

        // Callback para obtener la solución
        // Segundo parámetro considerando el dir enviado como param como nivel 0
        return getNumNiveles(directory, 0);
    }

    // Devuelve entero que corresponde al nivel del directorio más profundo de la jerarquía
    private static int getNumNiveles(File dir, int nivel) {
        File[] files = dir.listFiles(); // Obtener archivos a partir del dir

        // Si no hay nada, se considera el nivel 0 como el más profundo
        if (files == null || files.length == 0) {
            return nivel;
        }

        int maxNivel = nivel;

        // Iteración sobre todos los archivos
        for (File file : files) {
            // Validar que el archivo de esta iteración sea un directorio
            if (file.isDirectory()) {
                int currentNivel = getNumNiveles(file, nivel + 1); // Obtener en la variable de control el nivel más profundo alcanzado en esta ejecución

                // Modificar el nivel más profundo si se alcanza un nivel más profundo
                if (currentNivel > maxNivel) {
                    maxNivel = currentNivel;
                }
            }
        }
        return maxNivel;
    }

    // Recibe como parámetro el nombre del directorio
    // Devuelve una lista con los nombres de los ficheros que estén más profundos en la jerarquía correspondiente.

    public static List<String> listadoProfundos(String dir) {
        File directory = new File(dir); // Obtener dir recibido como param

        // Valida que sea un directorio
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException("Directorio inválido");
        }

        List<String> result = new ArrayList<>(); // Lista para almacenar el resultado
        int maxNivel = numNiveles(dir); // Obtener el nivel máximo de profundidad donde se encuentra un directorio
        getListadoProfundos(directory, 0, maxNivel, result); // Realizar la lógica
        return result; // Devolver resultado
    }

    private static void getListadoProfundos(File dir, int nivel, int maxNivel, List<String> result) {
        File[] files = dir.listFiles(); // Listar todos los archivos y directorios en el directorio actual

        // Validar que haya contenido
        if (files == null) {
            return;
        }

        // Iteración sobre todos los archivos
        for (File file : files) {
            // Validar que el archivo de esta iteración sea un directorio
            if (file.isDirectory()) {
                getListadoProfundos(file, nivel + 1, maxNivel, result); // Operar sobre este dir
            } else if (nivel == maxNivel) { // Si el archivo actual está en el nivel más profundo
                result.add(file.getPath()); // Agregar la ruta del archivo a la lista del resultado
            }
        }
    }
}
