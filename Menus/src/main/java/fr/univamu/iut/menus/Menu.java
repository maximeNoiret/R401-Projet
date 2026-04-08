package fr.univamu.iut.menus;

import java.time.LocalDate;
import java.util.ArrayList;

public class Menu {
    private int id;
    private String name;
    private int creatorId;
    private String creatorName;
    private LocalDate creationDate;
    private LocalDate lastUpdateDate;
    private ArrayList<Integer> mealsIds;
    private double totalPrice;

    public Menu() {}

    public Menu(int id, String name, int creatorId, LocalDate creationDate, LocalDate lastUpdateDate, String creatorName, double totalPrice) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.creatorName = creatorName;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public ArrayList<Integer> getMealsIds() {
        return mealsIds;
    }

    public void setMealsIds(ArrayList<Integer> mealsIds) {
        this.mealsIds = mealsIds;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creatorId=" + creatorId +
                ", creatorName='" + creatorName + '\'' +
                ", creationDate=" + creationDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", mealsIds=" + mealsIds +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
