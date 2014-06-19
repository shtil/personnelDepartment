package ua.ks.shtil.java.models;

import java.math.BigDecimal;

/**
 * Created by shtil on 14.06.14.
 */
public class Position {
    private  int id;
    private String name;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private int department;

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(BigDecimal minSalary) {
        this.minSalary = minSalary;
    }

    public BigDecimal getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(BigDecimal maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Position() {
    }

    public Position(String name, BigDecimal minSalary, BigDecimal maxSalary) {
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public Position(int id, String name, BigDecimal minSalary, BigDecimal maxSalary) {
        this.id = id;
        this.name = name;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", department=" + department +
                '}';
    }
}
