package codeit.cakeN.domain.letter;

public enum Tag {
    BIRTHDAY("생일"),
    BIRTHDAY2("생신"),
    PARENTSDAY("어버이날"),
    ANNIVERSARY("기념일"),
    EMPLOYMENT("취뽀축하"),
    LEAVE("퇴사축하"),
    ADMISSION("입학축하"),
    GRADUATION("졸업축하"),
    FRIENDSHIP("우정"),
    LOVE("커플"),
    TEACHERSDAY("스승의날");


    private String value;

    Tag(String value) {
        this.value = value;
    }

    public String getKey() {
        return name();
    }

    public String getValue() {
        return value;
    }
}
