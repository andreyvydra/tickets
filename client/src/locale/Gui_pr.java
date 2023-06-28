package locale;

import java.util.ListResourceBundle;

public class Gui_pr extends ListResourceBundle {
    private final Object[][] contents = {
            {"addButton", "Adicionar"},
            {"canvasTab", "Linho"},
            {"tableTab", "Tabela"},
            {"removeLowerButton", "Remover os menores"},
            {"clearButton", "Limpar"},
            {"executeScriptButton", "Executar o programa"},
            {"historyLabel", "História"},
            {"greaterThanTypeButton", "Tipos grandes"},
            {"groupByDateButton", "Agrupamento por data"},
            {"printTypeAscendingButton", "Tipo imprimir"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
