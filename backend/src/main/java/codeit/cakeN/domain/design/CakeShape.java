package codeit.cakeN.domain.design;

public enum CakeShape {

    CIRCLE("원형"),
    RECTANGLE("사각형"),
    HEART("하트");


    private String value;

    CakeShape(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
