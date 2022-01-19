package com.paulojunior97.apiblog.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@ApiModel("Login")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtRequestDto implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    @ApiModelProperty(required = true)
    private String email;

    @ApiModelProperty(required = true)
    private String senha;
}
