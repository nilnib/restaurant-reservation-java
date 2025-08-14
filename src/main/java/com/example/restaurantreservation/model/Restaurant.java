package com.example.restaurantreservation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Document(collection = "restaurants")
public class Restaurant {
    @Id
    private String id;
    private String name;
    private String address;
    private List<Table> tables;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public List<Table> getTables() { return tables; }
    public void setTables(List<Table> tables) { this.tables = tables; }

    public static class Table {
        private int tableNumber;
        private int seats;

        public int getTableNumber() { return tableNumber; }
        public void setTableNumber(int tableNumber) { this.tableNumber = tableNumber; }
        public int getSeats() { return seats; }
        public void setSeats(int seats) { this.seats = seats; }
    }
}
