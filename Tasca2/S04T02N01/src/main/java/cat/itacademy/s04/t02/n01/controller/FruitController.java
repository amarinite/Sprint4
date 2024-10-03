package cat.itacademy.s04.t02.n01.controller;


import cat.itacademy.s04.t02.n01.model.Fruit;
import cat.itacademy.s04.t02.n01.service.impl.FruitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllFruits() {
        List<Fruit> fruitsList = fruitService.getFruits();
        return fruitsList.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(fruitsList);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFruit(@RequestBody Fruit fruit) {
        Fruit fruitObj = fruitService.saveFruit(fruit);
        return new ResponseEntity<>(fruitObj, HttpStatus.CREATED);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getFruitById(@PathVariable Long id) {
        Fruit fruit = fruitService.getFruitById(id);
        return ResponseEntity.ok(fruit);
    }

    public ResponseEntity<?> updateFruitById(@PathVariable Long id, @RequestBody Fruit newFruitData) {
        Fruit updatedFruit = fruitService.updateById(newFruitData, id);
        return ResponseEntity.ok(updatedFruit);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFruitById(@PathVariable Long id) {
        fruitService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
