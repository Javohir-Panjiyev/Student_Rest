package uz.pdp.restcrud.grouping;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "grouping", schema = "grouping")
public class Grouping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    public Grouping() {
    }

    public Grouping(String name) {
        this.name = name;
    }

    public Grouping(Long id, String name) {
        this(name);
        this.id = id;
    }
}
