package com.publicissapient.kpidashboard.microservice.model;



public class Demo {

    private String id;
    private String Name;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "id='" + id + '\'' +
                ", Name='" + Name + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
