package cat.itacademy.s04.t02.n01.services;

import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.repository.IFruitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class FruitService {

    @Autowired
    IFruitRepo fruitRepo;

    public ArrayList<Fruit> getFruits() {
        return (ArrayList<Fruit>) fruitRepo.findAll();
    }

    public Fruit saveFruit(Fruit fruit) {
        return fruitRepo.save(fruit);
    }

    public Optional<Fruit> getFruitById(Long id) {
        return fruitRepo.findById(id);
    }

    public Fruit updateById(Fruit fruitData, Long id) throws Exception {
        return fruitRepo.findById(id).map(fruit -> {
            fruit.setName(fruitData.getName());
            fruit.setKgAmount(fruitData.getKgAmount());
            return fruitRepo.save(fruit);
        }).orElseThrow(() -> new Exception("Fruit not found with id: " + id));
    }

    public void deleteById(Long id) throws Exception {
        if (fruitRepo.existsById(id)) {
            fruitRepo.deleteById(id);
        } else {
            throw new Exception("Fruit not found with id: " + id);
        }
    }
}
