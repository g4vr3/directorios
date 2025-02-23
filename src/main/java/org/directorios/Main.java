package org.directorios;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1
        System.out.println("Número del nivel más profundo donde hay un directorio: ");
        System.out.println(DirUtils.numNiveles("directorio"));

        System.out.println("-------------------------------------");

        System.out.println("Listado de ficheros del nivel más profundo: ");
        // 2
        List<String> lista = DirUtils.listadoProfundos("directorio");
        lista.forEach(System.out::println);
    }
}
