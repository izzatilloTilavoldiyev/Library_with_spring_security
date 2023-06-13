package uz.pdp.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springsecurity.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/{userId}/block")
    public ResponseEntity<Boolean> blockUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.blockUser(userId));
    }

    @PutMapping("/{userId}/unblock")
    public ResponseEntity<Boolean> unblockUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.unBlockUser(userId));
    }
}
