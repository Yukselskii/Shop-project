package store.model;

import java.io.Serializable;

public class Cashier implements Serializable {
    private final String id;
    private final String name;
    private final double monthlySalary;

    public Cashier(String id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.monthlySalary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    @Override
    public String toString() {
        return String.format("Cashier[id=%s, name=%s]", id, name);
    }
}
