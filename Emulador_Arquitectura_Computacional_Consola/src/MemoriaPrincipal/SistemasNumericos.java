package MemoriaPrincipal;

public class SistemasNumericos {
    public static int decimalABinario(int decimal) {
        int binary = 0;
        int place = 1; // Representa la posición en el número binario

        // Convertir el número decimal a binario
        while (decimal > 0) {
            int remainder = decimal % 2;
            decimal = decimal / 2;
            binary += remainder * place;
            place *= 10;
        }

        return binary;
    }
    public static int binarioADecimalRecibeString(String binary) {

        // Verificar que la cadena binaria solo contiene 0s y 1s
        for (char c : binary.toCharArray()) {
            if (c != '0' && c != '1') {
                throw new IllegalArgumentException("El número binario solo debe contener 0s y 1s.");
            }
        }

        int decimal = 0;
        int base = 1; // 2^0

        // Recorrer la cadena binaria desde el final
        for (int i = binary.length() - 1; i >= 0; i--) {
            if (binary.charAt(i) == '1') {
                decimal += base;
            }
            base *= 2; // Incrementar la base (2^n)
        }

        return decimal;
    }

    public static int binarioADecimal(int binary) {
        int decimal = 0;
        int base = 1; // 2^0

        // Recorrer el número binario bit a bit
        while (binary > 0) {
            int lastDigit = binary % 10; // Obtener el último bit
            binary = binary / 10; // Eliminar el último bit
            decimal += lastDigit * base;
            base *= 2; // Incrementar la base (2^n)
        }

        return decimal;
    }

    public static String decimalABinarioDevuelveString(int decimal) {

        StringBuilder binary = new StringBuilder();

        // Convertir el número decimal a binario
        while (decimal > 0) {
            int remainder = decimal % 2;
            decimal = decimal / 2;
            binary.insert(0, remainder);
        }

        // Rellenar con ceros a la izquierda hasta tener 8 bits
        while (binary.length() < 8) {
            binary.insert(0, '0');
        }

        return binary.toString();
    }
}
