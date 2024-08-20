package BusinessLogic.Controllers;

import javafx.scene.control.TableRow;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ColoredTableRow<T> extends TableRow<T> {
    private final Paint color1 = Color.web("#A3A8E0"); // Color para el primer tipo de bloque
    private final Paint color2 = Color.web("#D6D2E8"); // Color para el segundo tipo de bloque

    private int blockSize; // Tamaño del bloque de memoria

    public ColoredTableRow(int blockSize) {
        this.blockSize = blockSize;
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);

        if (empty || item == null) {
            setStyle(""); // Restablece el estilo si la fila está vacía
        } else {
            int index = getIndex();
            int blockIndex = index / blockSize; // Determina el índice del bloque
            Paint color = (blockIndex % 2 == 0) ? color1 : color2;
            setStyle("-fx-background-color: " + toHexString(color) + ";");
        }
    }

    private String toHexString(Paint paint) {
        // Convierte el Paint en un formato de color hexadecimal
        Color color = (Color) paint;
        return String.format("#%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255), (int) (color.getBlue() * 255));
    }
}
