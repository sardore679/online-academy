package online_academy.repository;

import online_academy.entity.Group;
import online_academy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByTeacher(User teacher);

}
