package locale;

import java.util.ListResourceBundle;

public class GuiTicket_hu extends ListResourceBundle {
    private final Object[][] contents = {
            {"addButton", "Hozzáadás"},
            {"addIfMaxButton", "Hozzáadás, ha maximum"},
            {"removeButton", "Eltávolítás"},
            {"updateButton", "Frissítés"},
            {"removeLowerButton", "Kisebbek eltávolítása"},
            {"ticketLabel", "Jegy"},
            {"nameLabel", "Név"},
            {"priceLabel", "Ár"},
            {"typeLabel", "Típus"},
            {"birthdayLabel", "Születésnap"},
            {"colorEyeLabel", "Szemszín"},
            {"colorHairLabel", "Hajszín"},
            {"nationalityLabel", "Nemzetiség"},
            {"coordinatesLabel", "Koordináták"},
            {"personLabel", "Személy"},
            {"locationLabel", "Helyszín"}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}