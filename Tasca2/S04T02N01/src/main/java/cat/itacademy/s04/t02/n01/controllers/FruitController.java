package cat.itacademy.s04.t02.n01.controllers;


import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.repository.FruitRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class FruitController {

    @Autowired
    private FruitRepo fruitRepo;

    @GetMapping("/fruit/getAll/")
    public ResponseEntity<List<Fruit>> getAllFruits() {
        try {
            List<Fruit> fruitsList = new ArrayList<>();
            fruitRepo.findAll().forEach(fruitsList::add);

            if(fruitsList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(fruitsList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/fruit/getOne/{id}")
    public ResponseEntity<Fruit> getFruitById(@PathVariable int id) {
        Optional<Fruit> fruitData = fruitRepo.findById(id);

        if (fruitData.isPresent()) {
            return new ResponseEntity<>(fruitData.get(), HttpStatus.OK);
        }

       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/fruit/add")
    public ResponseEntity<Fruit> addFruit(@RequestBody Fruit fruit) {
        Fruit fruitObj = fruitRepo.save(fruit);

        return new ResponseEntity<>(fruitObj, HttpStatus.OK);
    }

    @PostMapping("/fruit/update/{id}")
    public ResponseEntity<Fruit> updateFruitById(@PathVariable int id, @RequestBody Fruit newFruitData) {
        Optional<Fruit> oldFruitData =  fruitRepo.findById(id);

        if (oldFruitData.isPresent()) {
            Fruit updatedFruitData = oldFruitData.get();
            updatedFruitData.setName(newFruitData.getName());
            updatedFruitData.setKgAmount(newFruitData.getKgAmount());

            Fruit fruitObj = fruitRepo.save(updatedFruitData);

            return new ResponseEntity<>(fruitObj, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/fruit/delete/{id}")
    public ResponseEntity<Object> deleteFruitById(@PathVariable int id) {
        fruitRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
