package com.wolfpack.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.utility.nullability.MaybeNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper mapper (){
        return new ModelMapper();
    }
}
