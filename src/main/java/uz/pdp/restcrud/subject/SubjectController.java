package uz.pdp.restcrud.subject;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectRepository subjectRepository;

    public SubjectController(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @PostMapping("create")
    public Subject create(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @GetMapping("{id}")
    public Subject get(@PathVariable String id) {
        return subjectRepository.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("subject not found"));
    }

    @GetMapping("")
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") String id) {
        if (!subjectRepository.existsById(Long.valueOf(id))) {
            throw new RuntimeException("subject not found");
        }
        subjectRepository.deleteById(Long.valueOf(id));
    }
}
