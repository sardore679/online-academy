package online_academy.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToMany
    @JoinTable(
            name = "group_students",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")

    )
    private List<User> student;

}
