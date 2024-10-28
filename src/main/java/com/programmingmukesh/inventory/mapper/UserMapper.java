package com.programmingmukesh.inventory.mapper;

import com.programmingmukesh.inventory.dto.user.UserDto;
import com.programmingmukesh.inventory.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public User mapToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public UserDto mapToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
