package online_academy.service;


import lombok.RequiredArgsConstructor;
import online_academy.dto.group_dto.GroupRequestDto;
import online_academy.dto.group_dto.GroupResponseDto;
import online_academy.entity.Course;
import online_academy.entity.Group;
import online_academy.entity.User;
import online_academy.repository.CourseRepository;
import online_academy.repository.GroupRepository;
import online_academy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    private GroupResponseDto toDto(Group group) {
        return new GroupResponseDto(
                group.getId(),
                group.getName(),
                group.getTeacher().getFullName(),
                group.getCourse().getTitle(),
                group.getStudent()
                        .stream()
                        .map(User::getFullName)
                        .toList()
        );
    }

    public List<GroupResponseDto> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public GroupResponseDto getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guruh topilmadi: " + id));
        return toDto(group);
    }

    public GroupResponseDto createGroup(GroupRequestDto dto) {
        User teacher = userRepository.findById(dto.teacherId())
                .orElseThrow(() -> new RuntimeException("Teacher topilmadi: " + dto.teacherId()));

        Course course = courseRepository.findById(dto.courseId())
                .orElseThrow(() -> new RuntimeException("Kurs topilmadi: " + dto.courseId()));

        Group group = new Group();
        group.setName(dto.name());
        group.setTeacher(teacher);
        group.setCourse(course);
        group.setStudent(List.of());

        Group saved = groupRepository.save(group);
        return toDto(saved);
    }

    public GroupResponseDto addStudentToGroup(Long groupId, Long studentId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Guruh topilmadi: " + groupId));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student topilmadi: " + studentId));

        if (group.getStudent().contains(student)) {
            throw new RuntimeException("Student allaqachon bu guruhda mavjud");
        }

        group.getStudent().add(student);
        Group saved = groupRepository.save(group);
        return toDto(saved);
    }

    public GroupResponseDto removeStudentFromGroup(Long groupId, Long studentId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Guruh topilmadi: " + groupId));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student topilmadi: " + studentId));

        group.getStudent().remove(student);
        Group saved = groupRepository.save(group);
        return toDto(saved);
    }

    public void deleteGroup(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guruh topilmadi:" + id));
        groupRepository.delete(group);
    }

}
