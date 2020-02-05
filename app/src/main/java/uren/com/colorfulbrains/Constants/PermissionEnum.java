package uren.com.colorfulbrains.Constants;

public enum  PermissionEnum {

    PERMISSION_WRITE_EXTERNAL_STORAGE(1001),
    PERMISSION_CAMERA(1002),
    PERMISSION_ACCESS_FINE_LOCATION(1003),
    PERMISSION_RECORD_AUDIO(1004),
    PERMISSION_READ_PHONE_STATE(1005),
    PERMISSION_READ_PHONE_NUMBERS(1006),
    PERMISSION_READ_CONTACTS(1007);

    private int id;

    private PermissionEnum(int id) {
        this.id = id;
    }


    public static PermissionEnum getById(int id) {
        for (PermissionEnum e : values()) {
            if (e.id == id)
                return e;
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
