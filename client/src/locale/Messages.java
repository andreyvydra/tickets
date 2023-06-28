package locale;

import java.util.ListResourceBundle;

public class Messages extends ListResourceBundle {
    private final Object[][] contents = {
            {"", "Проблемы с подгрзкой fxml файла"}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
