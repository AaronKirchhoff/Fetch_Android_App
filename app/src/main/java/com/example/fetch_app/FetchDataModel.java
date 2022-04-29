package com.example.fetch_app;

public class FetchDataModel {



    private int id;
    private int listId;
    private String name;

    public FetchDataModel(int id, int listId, String name) {
        this.id = id;
        this.listId = listId;
        this.name = name;
    }

    public FetchDataModel() {
    }

    @Override
    public String toString() {
        return  "id:  " + id +
                ", listId:  " + listId +
                ", name:  " + name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
