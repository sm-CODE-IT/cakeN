package codeit.cakeN.domain.design;

public enum LetterSize {
    SMALL("SMALL"),
    MIDDLE("MIDDLE"),
    LARGE("LARGE");

    private String value;

    LetterSize(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
