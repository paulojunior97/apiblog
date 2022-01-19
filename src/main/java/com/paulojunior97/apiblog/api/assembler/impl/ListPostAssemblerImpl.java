package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.PostDto;
import com.paulojunior97.apiblog.domain.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Qualifier("postList")
@Service
public class ListPostAssemblerImpl implements Assembler<List<PostDto>, List<Post>> {

    @Autowired
    private PostAssemblerImpl assembler;

    @Override
    public List<Post> dtoToEntity(List<PostDto> dto) {
        return dto.stream().map(assembler::dtoToEntity).collect(Collectors.toList());
    }

    @Override
    public List<PostDto> entityToDto(List<Post> entity) {
        return entity.stream().map(assembler::entityToDto).collect(Collectors.toList());
    }

    public void setAssembler(PostAssemblerImpl assembler) {
        this.assembler = assembler;
    }

}
