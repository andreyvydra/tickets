package locale;

import java.util.ListResourceBundle;

public class GuiTicket_pr extends ListResourceBundle {
    private final Object[][] contents = {
            {"addButton", "Adicionar"},
            {"addIfMaxButton", "Adicionar se makimum"},
            {"removeButton", "Remover"},
            {"updateButton", "Renovar"},
            {"removeLowerButton", "Remover os menores"},
            {"ticketLabel", "Bilhete"},
            {"nameLabel", "Nome"},
            {"priceLabel", "Preço"},
            {"typeLabel", "Tipo"},
            {"birthdayLabel", "Aniversário"},
            {"colorEyeLabel", "Cor dos olhos"},
            {"colorHairLabel", "Cor do cabelo"},
            {"nationalityLabel", "Nacionalidade"},
            {"coordinatesLabel", "Coordenadas"},
            {"personLabel", "Pessoa"},
            {"locationLabel", "Localização"}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
