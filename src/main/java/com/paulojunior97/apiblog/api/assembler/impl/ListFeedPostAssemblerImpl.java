package com.paulojunior97.apiblog.api.assembler.impl;

import com.paulojunior97.apiblog.api.assembler.Assembler;
import com.paulojunior97.apiblog.api.dto.FeedNoticiaDto;
import com.paulojunior97.apiblog.domain.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Qualifier("feedPostList")
@Service
public class ListFeedPostAssemblerImpl implements Assembler<List<FeedNoticiaDto>, List<Post>> {

    @Autowired
    private FeedPostAssemblerImpl assembler;

    @Override
    public List<Post> dtoToEntity(List<FeedNoticiaDto> dto) {
        return dto.stream().map(assembler::dtoToEntity).collect(Collectors.toList());
    }

    @Override
    public List<FeedNoticiaDto> entityToDto(List<Post> entity) {
        return entity.stream().map(assembler::entityToDto).collect(Collectors.toList());
    }
}
