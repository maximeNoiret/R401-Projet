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

    public Menu(int id, String name, int creatorId, LocalDate creationDate, LocalDate lastUpdateDate, String creatorName, double totalPrice) {
        this.id = id;
        this.name = name;
        this.creatorId = creatorId;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.creatorName = creatorName;
        this.totalPrice = totalPrice;
    }
}
