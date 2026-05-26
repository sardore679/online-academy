package online_academy.controller.group;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online_academy.dto.group_dto.GroupRequestDto;
import online_academy.dto.group_dto.GroupResponseDto;
import online_academy.service.group_service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<GroupResponseDto>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<GroupResponseDto> getGroupById(@PathVariable Long id) {
        return ResponseEntity.ok(groupService.getGroupById(id));
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<GroupResponseDto> createGroup(@Valid @RequestBody GroupRequestDto dto) {
        return ResponseEntity.ok(groupService.createGroup(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{groupId}/students/{studentId}")
    public ResponseEntity<GroupResponseDto> addStudentToGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        return ResponseEntity.ok(groupService.addStudentToGroup(groupId, studentId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{groupId}/students/{studentId}")
    public ResponseEntity<GroupResponseDto> removeStudentFromGroup(@PathVariable Long groupId, @PathVariable Long studentId) {
        return ResponseEntity.ok(groupService.removeStudentFromGroup(groupId, studentId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }

}
