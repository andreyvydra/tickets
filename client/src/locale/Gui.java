package locale;

import java.util.ListResourceBundle;

public class Gui extends ListResourceBundle {
    private final Object[][] contents = {
            {"addButton", "Добавить"},
            {"canvasTab", "Полотно"},
            {"tableTab", "Таблица"},
            {"removeLowerButton", "Удалить меньшие"},
            {"clearButton", "Очистить"},
            {"executeScriptButton", "Выполнить скрипт"},
            {"historyLabel", "История"},
            {"greaterThanTypeButton", "Больше, чем тип"},
            {"groupByDateButton", "Группировка по дате"},
            {"printTypeAscendingButton", "Напечатать тип"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
