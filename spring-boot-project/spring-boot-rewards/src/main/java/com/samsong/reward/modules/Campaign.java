package com.samsong.reward.modules;

public class Campaign {
    private final String id;
    private final String name;

    public Campaign(String id, String name) {
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
