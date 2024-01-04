package com.publicissapient.kpidashboard.microserviceapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Demo")
public class Demo {
    @Id
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
