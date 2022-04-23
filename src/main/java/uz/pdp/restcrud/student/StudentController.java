package uz.pdp.restcrud.student;

import org.springframework.web.bind.annotation.*;
import uz.pdp.restcrud.subject.Subject;
import uz.pdp.restcrud.subject.SubjectRepository;

import java.util.List;

@RestController
@RequestMapping("/student/")
public class StudentController {

    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;

    public StudentController(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    @PostMapping("create")
    public Student create(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    @GetMapping("{id}")
    public Student get(@PathVariable String id) {
        return studentRepository.findById(Long.valueOf(id)).orElseThrow(() -> new RuntimeException("student not found"));
    }

    @GetMapping("")
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @PostMapping("update")
    public Student update(@RequestBody Student student) {
        Student temp = studentRepository.findById(student.getId()).orElseThrow(() -> new RuntimeException("student not found"));
        student.setSubjects(temp.getSubjects());
        return studentRepository.save(student);
    }

    @PostMapping("/add/subject/{studentId}/{subjectId}")
    public Student addSubject(@PathVariable(name = "studentId") String studentId,
                              @PathVariable(name = "subjectId") String subjectId) {

        Student student = studentRepository.findById(Long.valueOf(studentId)).orElseThrow(() -> new RuntimeException("student not found"));
        Subject subject = subjectRepository.findById(Long.valueOf(subjectId)).orElseThrow(() -> new RuntimeException("subject not found"));
        List<Subject> subjects = student.getSubjects();
        for (Subject s : subjects) {
            if (s.getId().equals(subject.getId())) {
                throw new RuntimeException("subject is already added");
            }
        }
        subjects.add(subject);
        student.setSubjects(subjects);
        return studentRepository.save(student);
    }

    @PostMapping("delete/subject/{studentId}/{subjectId}")
    public Student removeSubject(@PathVariable(name = "studentId") String studentId,
                                 @PathVariable(name = "subjectId") String subjectId) {
        Student student = studentRepository.findById(Long.valueOf(studentId)).orElseThrow(() -> new RuntimeException("student not found"));
        Subject subject = subjectRepository.findById(Long.valueOf(subjectId)).orElseThrow(() -> new RuntimeException("subject not found"));
        List<Subject> subjects = student.getSubjects();
        if (!subjects.removeIf(a -> a.getId().equals(subject.getId()))) {
            throw new RuntimeException("subject not found in list of student's subjects");
        }
        student.setSubjects(subjects);
        return studentRepository.save(student);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable(name = "id") String id) {
        if (!studentRepository.existsById(Long.valueOf(id))) {
            throw new RuntimeException("student not found");
        }
        studentRepository.deleteById(Long.valueOf(id));
    }

}
