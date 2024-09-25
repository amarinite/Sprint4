package cat.itacademy.s04.t02.n02.controllers;

import cat.itacademy.s04.t02.n02.model.Fruit;
import cat.itacademy.s04.t02.n02.services.FruitService;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fruit")
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllFruits() {
        try {
            List<Fruit> fruitsList = fruitService.getFruits();

            if (fruitsList.isEmpty()) {
                return new ResponseEntity<>("No fruits found", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(fruitsList, HttpStatus.OK);

        } catch (DataAccessException e) {
            return new ResponseEntity<>("Database error", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFruit(@RequestBody Fruit fruit) {
        try {
            Fruit fruitObj = fruitService.saveFruit(fruit);
            return new ResponseEntity<>(fruitObj, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Error saving fruit to database", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getFruitById(@PathVariable Long id) {
        try {
            Optional<Fruit> fruitData = fruitService.getFruitById(id);

            if (fruitData.isPresent()) {
                return new ResponseEntity<>(fruitData.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>("Fruit not found", HttpStatus.NOT_FOUND);
        } catch (DataAccessException e) {
            return new ResponseEntity<>("Database error", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateFruitById(@PathVariable Long id, @RequestBody Fruit newFruitData) {
        try {
            Fruit updatedFruit = fruitService.updateById(newFruitData, id);

            if (updatedFruit != null) {
                return new ResponseEntity<>(updatedFruit, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Fruit not found with id: " + id, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while updating the fruit: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteFruitById(@PathVariable Long id) {
        try {
            fruitService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting fruit: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
