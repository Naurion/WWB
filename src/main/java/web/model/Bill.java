package web.model;

import java.util.Calendar;

public class Bill {
    private long id;
    private long ownerId;
    private double money;

    public Bill() {
    }

    public Bill(long ownerId, double money) {
        this.id = generateId();
        this.ownerId = ownerId;
        this.money = money;
    }

    private long generateId() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public long getId() {
        return id;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
