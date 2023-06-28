package data;

public enum Language {
    RU("ru"),
    PR("pr"),
    HU("hu"),
    ES("es");


    public final String languageName;
    Language(String name) {
        this.languageName = name;
    }
}
