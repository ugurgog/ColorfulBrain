package uren.com.colorfulbrains.evetBusModels;

import uren.com.colorfulbrains.Models.User;

public class UserBus {

    User user;

    public UserBus(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
