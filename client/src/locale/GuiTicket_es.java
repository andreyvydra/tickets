package locale;

import java.util.ListResourceBundle;

public class GuiTicket_es extends ListResourceBundle {
    private final Object[][] contents = {
            {"addButton", "Agregar"},
            {"addIfMaxButton", "Agregar si máximo"},
            {"removeButton", "Eliminar"},
            {"updateButton", "Actualizar"},
            {"removeLowerButton", "Eliminar los menores"},
            {"ticketLabel", "Boleto"},
            {"nameLabel", "Nombre"},
            {"priceLabel", "Precio"},
            {"typeLabel", "Tipo"},
            {"birthdayLabel", "Fecha de nacimiento"},
            {"colorEyeLabel", "Color de ojos"},
            {"colorHairLabel", "Color de cabello"},
            {"nationalityLabel", "Nacionalidad"},
            {"coordinatesLabel", "Coordenadas"},
            {"personLabel", "Persona"},
            {"locationLabel", "Ubicación"}
    };
    @Override
    protected Object[][] getContents() {
        return contents;
    }
}