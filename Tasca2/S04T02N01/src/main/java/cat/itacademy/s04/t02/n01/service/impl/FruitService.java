package cat.itacademy.s04.t02.n01.service.impl;

import cat.itacademy.s04.t02.n01.exception.NoFruitFoundException;
import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.repository.FruitRepository;
import cat.itacademy.s04.t02.n01.service.FruitServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FruitService implements FruitServiceInterface {

    private final FruitRepository fruitRepo;

    @Autowired
    public FruitService(FruitRepository fruitRepo) {
        this.fruitRepo = fruitRepo;
    }

    public ArrayList<Fruit> getFruits() {
        return (ArrayList<Fruit>) fruitRepo.findAll();
    }

    public Fruit saveFruit(Fruit fruit) {
        return fruitRepo.save(fruit);
    }

    public Fruit getFruitById(Long id) {
        return fruitRepo.findById(id)
                .orElseThrow(() -> new NoFruitFoundException("Fruit not found with id: " + id));
    }

    public Fruit updateById(Fruit fruitData, Long id) {
        return fruitRepo.findById(id).map(fruit -> {
            fruit.setName(fruitData.getName());
            fruit.setKgAmount(fruitData.getKgAmount());
            return fruitRepo.save(fruit);
        }).orElseThrow(() -> new NoFruitFoundException("Fruit not found with id: " + id));
    }

    public void deleteById(Long id) {
        fruitRepo.findById(id)
                .orElseThrow(() -> new NoFruitFoundException("Fruit not found with id: " + id));

        fruitRepo.deleteById(id);
    }

}

