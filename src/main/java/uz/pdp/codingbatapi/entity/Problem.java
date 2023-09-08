package uz.pdp.codingbatapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "problems")
@Table(uniqueConstraints = { @UniqueConstraint(name = "UniqueNameAndCategoryId", columnNames = { "name", "category_id" }) })
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1000)
    private String instruction;

    @Column(nullable = false, length = 1000)
    private String correctSolution;

    @ManyToOne(optional = false)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "problem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Solution> solutions;

    // correct solved users
    @JsonIgnore
    @ManyToMany(mappedBy = "problems", cascade = CascadeType.REMOVE)
    private List<User> users;

    public void addUser(User user) {
        this.users.add(user);
        user.getProblems().add(this);
    }
    public void removeUser(User user) {
        this.users.remove(user);
        user.getProblems().remove(this);
    }
}
