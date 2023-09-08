package uz.pdp.codingbatapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    //solved problems
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Problem> problems;

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Solution> solutions;

    public void addProblem(Problem problem) {
        this.problems.add(problem);
        problem.getUsers().add(this);
    }
    public void removeProblem(Problem problem) {
        this.problems.remove(problem);
        problem.getUsers().remove(this);
    }
}
