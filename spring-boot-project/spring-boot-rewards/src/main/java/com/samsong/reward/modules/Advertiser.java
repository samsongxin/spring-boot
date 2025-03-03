package com.samsong.reward.modules;

import java.util.Objects;

public class Advertiser {
    private final String id;
    private final String name;
    private final String email;

    public Advertiser(String id, String name, String email) {
        assert id != null : "ID cannot be null";
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertiser boat = (Advertiser) o;
        return this.id.equals(boat.getId());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
