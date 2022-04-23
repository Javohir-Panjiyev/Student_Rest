# Student_Rest
## Source Code Review

Student

1. Entity feild:

```java
	 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "student_subject",
            schema = "student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;
```
   2.StudentRepository
   ```java
   public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsById(Long id);

}
   ```
   3. Student Controller
      ```java
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
   ```
  3.1 Student add subject
   ```java
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

   ```
