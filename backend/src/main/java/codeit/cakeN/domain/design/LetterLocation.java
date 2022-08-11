package codeit.cakeN.domain.design;

public enum LetterLocation {

    SIDE("SIDE"),
    CENTER("CENTER");

    private String value;

    LetterLocation(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
