import CPU.CPU;
import CPU.Cache;
import MemoriaPrincipal.*;

import java.io.IOException;
import java.util.Scanner;

import static MemoriaPrincipal.Instrucciones.*;

// Author: Fernando Huilca :)
// Date: 03/08/2024

public class RunMejor {

    // Códigos de escape ANSI para colores
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tamañoDeDirección = 4; // bits
        int tamañoDeBloque = 4; // bytes
        int tamañoDeLaCache = 8; // bytes

        boolean salir = false;

        // Configuración inicial de memoria y caché
        tamañoDeDirección = configurarMemoria("Tamaño de la dirección de los datos en la RAM en bits", new int[]{4, 6, 8, 12}, scanner);
        tamañoDeBloque = configurarMemoria("Tamaño de bloque de la RAM en bytes", new int[]{4, 6, 8, 12}, scanner);
        tamañoDeLaCache = configurarMemoria("Tamaño de la cache en bytes", new int[]{8, 12, 16, 32}, scanner);

        // Creación de la memoria, cache y CPU
        MemoriaPrincipal memoriaPrincipal = new MemoriaPrincipal(tamañoDeDirección, tamañoDeBloque, tamañoDeLaCache);
        Cache cache = new Cache(tamañoDeLaCache, memoriaPrincipal);
        CPU cpu = new CPU(cache);

        // Cargar datos desde archivo
        try {
            memoriaPrincipal.cargarDatosDesdeArchivo("src/DatosEnMemoriaPrincipal.txt");
        } catch (IOException e) {
            System.err.println("ERROR, NO SE CARGÓ CORRECTAMENTE EL ARCHIVO: " + e.getMessage());
        }

        // Menú principal
        while (!salir) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Realizar Instrucción");
            System.out.println("2. Imprimir Estado de CPU");
            System.out.println("3. Imprimir Estado de Caché");
            System.out.println("4. Imprimir memoria RAM");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");
            int opción = scanner.nextInt();

            switch (opción) {
                case 1:
                    realizarInstruccion(cpu, scanner);
                    break;
                case 2:
                    System.out.println(ANSI_BLUE);
                    cpu.imprimirEnConsolaCPU();
                    System.out.println(ANSI_RESET);
                    break;
                case 3:
                    System.out.println(ANSI_YELLOW);
                    cache.imprimirCacheAtributos();
                    cache.imprimirLíneas();
                    System.out.println(ANSI_RESET);
                    break;
                case 4:
                    System.out.println(ANSI_GREEN);
                    imprimirDatosDeMemoria(memoriaPrincipal.getDatos(), tamañoDeDirección, tamañoDeBloque);
                    System.out.println(ANSI_RESET);
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 5.");
                    break;
            }
        }

        // Guardar datos en archivo antes de salir
        try {
            memoriaPrincipal.guardarDatosEnArchivo("src/DatosEnMemoriaPrincipal.txt");
        } catch (IOException e) {
            System.err.println("ERROR, NO SE GUARDÓ CORRECTAMENTE EN EL ARCHIVO: " + e.getMessage());
        }
        scanner.close();
    }







    // MÉTODOS PARA QUE FUNCIONE EL MENÚ

    private static int configurarMemoria(String mensaje, int[] opciones, Scanner scanner) {
        boolean salir = false;
        int opciónSeleccionada = opciones[0];

        do {
            System.out.println("__________ " + mensaje + " ______________");
            for (int i = 0; i < opciones.length; i++) {
                System.out.println((i + 1) + ") " + opciones[i] + " Bytes");
            }
            System.out.print("Escriba el número de la opción que escoge: ");
            int opción = scanner.nextInt();
            if (opción >= 1 && opción <= opciones.length) {
                opciónSeleccionada = opciones[opción - 1];
                salir = true;
            } else {
                System.out.println("Escriba el INDICE de las opciones dadas");
            }
        } while (!salir);
        return opciónSeleccionada;
    }

    private static void realizarInstruccion(CPU cpu, Scanner scanner) {
        System.out.println("\nMenú de Instrucciones:");
        System.out.println("1. LOAD");
        System.out.println("2. STORE");
        System.out.println("3. MOVE");
        System.out.println("4. ADD");
        System.out.println("5. SUB");
        System.out.println("6. MUL");
        System.out.print("Selecciona una instrucción: ");
        int opción = scanner.nextInt();

        System.out.print("Ingrese el primer número (Registro): ");
        int registro = scanner.nextInt();
        System.out.print("Ingrese el segundo número (Registro_DirecMemoria): ");
        int registro_DireccióMemoria = scanner.nextInt();

        switch (opción) {
            case 1:
                cpu.ejecutarInstrucción(LOAD, registro, registro_DireccióMemoria);
                break;
            case 2:
                cpu.ejecutarInstrucción(STORE, registro, registro_DireccióMemoria);
                break;
            case 3:
                cpu.ejecutarInstrucción(MOVE, registro, registro_DireccióMemoria);
                break;
            case 4:
                cpu.ejecutarInstrucción(ADD, registro, registro_DireccióMemoria);
                break;
            case 5:
                cpu.ejecutarInstrucción(SUB, registro, registro_DireccióMemoria);
                break;
            case 6:
                cpu.ejecutarInstrucción(MUL, registro, registro_DireccióMemoria);
                break;
            default:
                System.out.println("Instrucción no válida.");
                break;
        }

    }
/*
    private static void imprimirDatosDeMemoria(int[] datosAux, int tamañoDeDirección) {
        // Definir los códigos de color ANSI
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_CYAN = "\u001B[36m";

        System.out.println("__________________________________________");
        System.out.println("     Dirección       |         Dato      ");
        System.out.println("-----------------------------------------");

        int contador = 1 ;
        for (int i = 0; i < datosAux.length; i++) {
            String color = (contador % 4 == 0) ? ANSI_BLUE : ANSI_CYAN;
            System.out.println(color + "     " + SistemasNumericos.decimalABinarioDevuelveString(i, tamañoDeDirección)
                    + " (" + i + ")" + "            " + SistemasNumericos.decimalABinarioDevuelveString(datosAux[i], 8)
                    + "("+datosAux[i]+")" + ANSI_RESET);
            contador++;
        }
    } */
private static void imprimirDatosDeMemoria(int[] datosAux, int tamañoDeDirección, int tamañoDeBloque) {
    // Definir los códigos de color ANSI
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_CYAN = "\u001B[36m";

    // Definir el ancho máximo de la columna
    final int WIDTH_DIRECCION = 18;  // Ajusta el ancho según sea necesario
    final int WIDTH_DATO = 18;       // Ajusta el ancho según sea necesario

    System.out.println("______________________________________________");
    System.out.println("     Dirección       |         Dato      ");
    System.out.println("----------------------------------------------");

    // Alternar colores por bloque
    for (int i = 0; i < datosAux.length; i++) {
        String color = ((i / tamañoDeBloque) % 2 == 0) ? ANSI_BLUE : ANSI_CYAN;

        // Formatear las cadenas para alinear las columnas
        String direccion = String.format("%" + WIDTH_DIRECCION + "s", SistemasNumericos.decimalABinarioDevuelveString(i, tamañoDeDirección) + " (" + i + ")");
        String dato = String.format("%" + WIDTH_DATO + "s", SistemasNumericos.decimalABinarioDevuelveString(datosAux[i], 8) + " (" + datosAux[i] + ")");

        System.out.println(color + direccion + "    " + dato + ANSI_RESET);
    }
}

}
