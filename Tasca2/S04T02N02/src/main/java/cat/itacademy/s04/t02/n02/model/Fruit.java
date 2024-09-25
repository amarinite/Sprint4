package cat.itacademy.s04.t02.n02.model;

import jakarta.persistence.*;

@Entity
@Table(name="Fruits")

public class Fruit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int kgAmount;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getKgAmount() {
        return kgAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKgAmount(int kgAmount) {
        this.kgAmount = kgAmount;
    }
}
