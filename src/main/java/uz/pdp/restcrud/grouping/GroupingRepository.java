package uz.pdp.restcrud.grouping;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupingRepository extends JpaRepository<Grouping, Long> {

    boolean existsById(Long id);

}
