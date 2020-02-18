package name.nicholasgribanov.restforweb.repository;

import name.nicholasgribanov.restforweb.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUsername(String username);
}
