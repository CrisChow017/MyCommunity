package life.cris.community.enums;

public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private final Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum value : CommentTypeEnum.values()) {
            if (value.getType().equals(type))
                return true;
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
