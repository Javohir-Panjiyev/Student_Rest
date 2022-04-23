package uz.pdp.restcrud.grouping;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grouping")
public class GroupingController {
    private final GroupingRepository groupingRepository;

    public GroupingController(GroupingRepository groupingRepository) {
        this.groupingRepository = groupingRepository;
    }

    @PostMapping("create")
    public Grouping create(@RequestBody Grouping grouping) {
        return groupingRepository.save(grouping);
    }

    @GetMapping("")
    public List<Grouping> getAll() {
        return groupingRepository.findAll();
    }

    @GetMapping("{id}")
    public Grouping get(@PathVariable(name = "id") String id) {
        return groupingRepository.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("grouping not found"));
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") String id) {
        if (!groupingRepository.existsById(Long.valueOf(id))) {
            throw new RuntimeException("grouping not found");
        }
        groupingRepository.deleteById(Long.valueOf(id));
    }
}
