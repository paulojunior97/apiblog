package com.paulojunior97.apiblog.domain.service;

import com.paulojunior97.apiblog.api.dto.FeedNoticiaDto;

import java.util.List;

public interface FeedNoticiasService {

    List<FeedNoticiaDto> feedNoticias();
}
