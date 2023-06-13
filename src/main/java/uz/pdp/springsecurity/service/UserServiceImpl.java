package uz.pdp.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springsecurity.dto.LoginDto;
import uz.pdp.springsecurity.dto.UserCreateDto;
import uz.pdp.springsecurity.entity.UserEntity;
import uz.pdp.springsecurity.entity.UserRole;
import uz.pdp.springsecurity.exception.DataNotFoundException;
import uz.pdp.springsecurity.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public UserEntity login(LoginDto loginDto) {
        UserEntity userEntity = userRepository.findUserEntityByUsername(loginDto.getUsername())
                .orElseThrow(
                        () -> new DataNotFoundException("user not found")
                );
        if (passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())) {
            return userEntity;
        }
        throw new DataNotFoundException("user not found");
    }

    @Override
    public UserEntity save(UserCreateDto userDto, List<UserRole> roles) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setRoles(roles);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userRepository.save(userEntity);
    }

    @Override
    public boolean blockUser(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("user not found")
        );
        userEntity.setHasBlocked(true);
        return true;
    }

    @Override
    public boolean unBlockUser(UUID userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new DataNotFoundException("user not found")
        );
        userEntity.setHasBlocked(false);
        return false;
    }
}

