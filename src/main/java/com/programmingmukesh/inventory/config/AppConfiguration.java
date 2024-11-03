package com.programmingmukesh.inventory.config;

import com.programmingmukesh.inventory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {
    private final UserRepository userRepository;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
