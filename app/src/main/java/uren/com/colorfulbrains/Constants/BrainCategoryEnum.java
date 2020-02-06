package uren.com.colorfulbrains.Constants;

public enum BrainCategoryEnum {

    AWFUL_BRAIN(1,"Maalesef beyniniz "),
    POOR_BRAIN(2, "Kötü Seviye"),
    NORMAL_BRAIN(3, "Normal Seviye"),
    GOOD_BRAIN(4,"İyi Seviye"),
    SUPER_BRAIN(5,"Süper Seviye"),
    MEGA_BRAIN(6, "Mega Seviye");

    private int id;
    private String brainDesc;

    private BrainCategoryEnum(int id) {
        this.id = id;
    }

    public static BrainCategoryEnum getById(int id) {
        for (BrainCategoryEnum e : values()) {
            if (e.id == id)
                return e;
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
