package cat.itacademy.s04.t02.n01.repository;

import cat.itacademy.s04.t02.n01.model.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFruitRepo extends JpaRepository<Fruit, Long> {
}
