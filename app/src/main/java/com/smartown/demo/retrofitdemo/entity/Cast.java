package com.smartown.demo.retrofitdemo.entity;

public class Cast {

    private String id;
    private String name;
    private String alt;
    private Avatars avatars;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Avatars getAvatars() {
        return avatars;
    }

    public void setAvatars(Avatars avatars) {
        this.avatars = avatars;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", alt='" + alt + '\'' +
                ", avatars=" + avatars +
                '}';
    }

}
