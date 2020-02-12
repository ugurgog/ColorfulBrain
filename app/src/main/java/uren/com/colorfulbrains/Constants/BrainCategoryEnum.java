package uren.com.colorfulbrains.Constants;

import android.content.Context;

import uren.com.colorfulbrains.R;

public enum BrainCategoryEnum {

    AWFUL_BRAIN(1, R.string.AWFUL_LEVEL, R.string.AWFUL_LEVEL_DESC),
    POOR_BRAIN(2, R.string.POOR_LEVEL,  R.string.POOR_LEVEL_DESC),
    NORMAL_BRAIN(3, R.string.NORMAL_LEVEL,  R.string.NORMAL_LEVEL_DESC),
    GOOD_BRAIN(4,R.string.GOOD_LEVEL,  R.string.GOOD_LEVEL_DESC),
    SUPER_BRAIN(5,R.string.SUPER_LEVEL,  R.string.SUPER_LEVEL_DESC),
    MEGA_BRAIN(6, R.string.MEGA_LEVEL,  R.string.MEGA_LEVEL_DESC);

    private int id;
    private int levelTitle;
    private int levelDesc;

    private BrainCategoryEnum(int id, int levelTitle,int levelDesc) {
        this.id = id;
        this.levelTitle = levelTitle;
        this.levelDesc = levelDesc;
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

    public int getLevelTitle() {
        return levelTitle;
    }

    public int getLevelDesc() {
        return levelDesc;
    }
}
