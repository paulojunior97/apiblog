package com.paulojunior97.apiblog.api.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@ApiModel("Login Response")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseDto implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private String jwttoken;

}
