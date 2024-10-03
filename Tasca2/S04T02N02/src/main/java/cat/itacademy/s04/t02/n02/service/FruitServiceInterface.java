package cat.itacademy.s04.t02.n02.service;

import cat.itacademy.s04.t02.n02.model.Fruit;

import java.util.ArrayList;

public interface FruitServiceInterface {
    ArrayList<Fruit> getFruits();

    Fruit saveFruit(Fruit fruit);

    Fruit getFruitById(Long id);

    Fruit updateById(Fruit fruitData, Long id);

    void deleteById(Long id);
}
