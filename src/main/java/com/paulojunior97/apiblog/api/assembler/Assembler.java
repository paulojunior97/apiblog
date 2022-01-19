package com.paulojunior97.apiblog.api.assembler;

public interface Assembler<T, S> {

    S dtoToEntity(T dto);
    T entityToDto(S entity);

}
