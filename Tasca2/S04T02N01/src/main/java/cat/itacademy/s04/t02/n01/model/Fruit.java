package cat.itacademy.s04.t02.n01.model;

import jakarta.persistence.*;

@Entity
@Table(name="Fruits")

public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private int kgAmount;

    public Fruit() {
    }

    public Fruit(int id, String name, int kgAmount) {
        this.id = id;
        this.name = name;
        this.kgAmount = kgAmount;
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

    public int getKgAmount() {
        return kgAmount;
    }

    public void setKgAmount(int kgAmount) {
        this.kgAmount = kgAmount;
    }
}


