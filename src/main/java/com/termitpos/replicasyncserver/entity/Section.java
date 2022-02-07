package com.termitpos.replicasyncserver.entity;

import java.io.Serializable;

public class Section  implements Serializable {
    private Long id;
    private String name;
    private String uuid;

    public Section() {
    }

    public Section(Long id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Section{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
