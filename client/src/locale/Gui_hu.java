package locale;

import java.util.ListResourceBundle;

public class Gui_hu extends ListResourceBundle {

    private final Object[][] contents = {
            {"addButton", "Hozzáadás"},
            {"canvasTab", "Vászon"},
            {"tableTab", "Táblázat"},
            {"removeLowerButton", "Törölje a kisebbeket"},
            {"clearButton", "Tiszta"},
            {"executeScriptButton", "Hajtsa végre a szkriptet"},
            {"historyLabel", "Történelem"},
            {"greaterThanTypeButton", "A típusok nagyok"},
            {"groupByDateButton", "Сsoportosítás dátum szerint"},
            {"printTypeAscendingButton", "Nyomtatás típusa"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
