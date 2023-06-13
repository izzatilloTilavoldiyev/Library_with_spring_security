package uz.pdp.springsecurity.service;

import uz.pdp.springsecurity.dto.LoginDto;
import uz.pdp.springsecurity.dto.UserCreateDto;
import uz.pdp.springsecurity.entity.UserEntity;
import uz.pdp.springsecurity.entity.UserRole;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserEntity login(LoginDto loginDto);
    UserEntity save(UserCreateDto userDto, List<UserRole> roles);
    boolean blockUser(UUID userId);
    boolean unBlockUser(UUID userId);
}
