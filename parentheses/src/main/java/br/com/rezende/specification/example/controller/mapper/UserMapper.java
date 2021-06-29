package br.com.rezende.specification.example.controller.mapper;

import br.com.rezende.specification.example.controller.dto.UserDto;
import br.com.rezende.specification.example.domain.User;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(User user);

    List<UserDto> map(List<User> users);
}
