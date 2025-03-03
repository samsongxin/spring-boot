package com.samsong.reward.modules;

public class Reward {
    private final String id;
    private final String name;

    public Reward(String id, String name) {
        assert id != null : "ID cannot be null";
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
