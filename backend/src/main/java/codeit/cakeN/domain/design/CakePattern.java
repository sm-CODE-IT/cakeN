package codeit.cakeN.domain.design;

public enum CakePattern {
    BASIC("패턴없음"),
    GRADATION("그라데이션"),
    ZIGZAG("지그재그"),
    HALF("하프");

    private String value;

    CakePattern(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
