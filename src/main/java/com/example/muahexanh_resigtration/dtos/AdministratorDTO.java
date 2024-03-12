package com.example.muahexanh_resigtration.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class AdministratorDTO extends UserDTO {
    @JsonProperty("name")
    private String name;
}
