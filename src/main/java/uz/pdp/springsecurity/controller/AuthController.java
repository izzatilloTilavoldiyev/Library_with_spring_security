package uz.pdp.springsecurity.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springsecurity.dto.LoginDto;
import uz.pdp.springsecurity.dto.UserCreateDto;
import uz.pdp.springsecurity.entity.UserEntity;
import uz.pdp.springsecurity.entity.UserRole;
import uz.pdp.springsecurity.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public ResponseEntity<UserEntity> login(
            @RequestBody LoginDto loginDto
    ) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @PostMapping("librarian/sign-up")
    public ResponseEntity<UserEntity> librarianSignUp(
            @RequestBody UserCreateDto userDto
    ) {
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.LIBRARIAN)));
    }

    @PostMapping("user/sign-up")
    public ResponseEntity<UserEntity> userSignUp(
            @RequestBody UserCreateDto userDto
    ) {
        return ResponseEntity.ok(userService.save(userDto, List.of(UserRole.USER)));
    }
}
