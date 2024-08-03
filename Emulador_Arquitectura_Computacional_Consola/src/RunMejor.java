import CPU.CPU;
import CPU.Cache;
import MemoriaPrincipal.*;

import java.io.IOException;
import java.util.Scanner;

import static MemoriaPrincipal.Instrucciones.*;

// Author: Fernando Huilca
public class RunMejor {
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
                    cpu.imprimirEnConsolaCPU();
                    break;
                case 3:
                    cache.imprimirCacheAtributos();
                    cache.imprimirLíneas();
                    break;
                case 4:
                    imprimirDatosDeMemoria(memoriaPrincipal.getDatos());
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

    private static int configurarMemoria(String mensaje, int[] opciones, Scanner scanner) {
        boolean salir = false;
        int opciónSeleccionada = opciones[0];

        do {
            System.out.println("__________ " + mensaje + " ______________");
            for (int i = 0; i < opciones.length; i++) {
                System.out.println((i + 1) + ") " + opciones[i] + " bits");
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

    private static void imprimirDatosDeMemoria(int[] datosAux) {
        for (int i = 0; i < datosAux.length; i++) {
            System.out.println("Dato número " + i + ": " + datosAux[i]);
        }
    }
}
