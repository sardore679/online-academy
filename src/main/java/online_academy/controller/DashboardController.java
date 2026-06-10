package online_academy.controller;


import lombok.RequiredArgsConstructor;
import online_academy.dto.dashboard_dto.DashboardResponseDto;
import online_academy.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<DashboardResponseDto> getDashboard() {
        return ResponseEntity.ok(dashboardService.getDashboard());
    }

}
