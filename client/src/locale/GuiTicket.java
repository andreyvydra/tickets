package locale;

import java.util.ListResourceBundle;

public class GuiTicket extends ListResourceBundle {
    private final Object[][] contents = {
            {"addButton", "Добавить"},
            {"addIfMaxButton", "Добавить максимум"},
            {"removeButton", "Удалить"},
            {"updateButton", "Обновить"},
            {"removeLowerButton", "Удалить ниже"},
            {"ticketLabel", "Билет"},
            {"nameLabel", "Имя"},
            {"priceLabel", "Цена"},
            {"typeLabel", "Тип"},
            {"birthdayLabel", "День рождения"},
            {"colorEyeLabel", "Цвет глаз"},
            {"colorHairLabel", "Цвет волос"},
            {"nationalityLabel", "Национальность"},
            {"coordinatesLabel", "Координаты"},
            {"personLabel", "Персона"},
            {"locationLabel", "Локация"}
    };

    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
