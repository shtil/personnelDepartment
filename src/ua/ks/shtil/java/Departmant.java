package ua.ks.shtil.java;

/**
 * Created by shtil on 14.06.14.
 */
public class Departmant {
    private int id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Departmant() {
    }

    public Departmant(String name) {
        this.name = name;
    }

    public Departmant(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
