package locale;

import java.util.ListResourceBundle;

public class Gui_es extends ListResourceBundle {
    private final Object[][] contents = {
            {"addButton", "Agregar"},
            {"canvasTab", "Lienzo"},
            {"tableTab", "Tabla"},
            {"removeLowerButton", "Eliminar menores"},
            {"clearButton", "Limpiar"},
            {"executeScriptButton", "Ejecutar script"},
            {"historyLabel", "Historia"},
            {"greaterThanTypeButton", "Mayor que tipo"},
            {"groupByDateButton", "Agrupar por fecha"},
            {"printTypeAscendingButton", "Imprimir tipo"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}