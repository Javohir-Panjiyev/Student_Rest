package uz.pdp.restcrud.subject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

//    @Query("select (count(s) > 0) from Subject s where s.id = ?1")
    boolean existsById(Long id);

}
