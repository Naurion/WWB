package web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Calendar;
import java.util.Random;

@Entity
public class Bill implements ModelObject {
    @Id
    private long id;
    @Column
    private long ownerId;
    @Column
    private double money;

    public Bill() {
    }

    public Bill(long ownerId, double money) {
        this.id = generateId();
        this.ownerId = ownerId;
        this.money = money;
    }

    private long generateId() {
        return Calendar.getInstance().getTimeInMillis() + new Random(100).nextLong();
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
