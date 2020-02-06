package uren.com.colorfulbrains.Constants;

public enum LoginMethodEnum {

    EMAIL(1),
    GOOGLE(2);

    private int id;

    private LoginMethodEnum(int id) {
        this.id = id;
    }

    public static LoginMethodEnum getById(int id) {
        for (LoginMethodEnum e : values()) {
            if (e.id == id)
                return e;
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
