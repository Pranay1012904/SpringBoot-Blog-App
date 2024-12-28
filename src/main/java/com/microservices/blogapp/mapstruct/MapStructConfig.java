package com.microservices.blogapp.mapstruct;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;

@MapperConfig(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
        )
public interface MapStructConfig {
}
