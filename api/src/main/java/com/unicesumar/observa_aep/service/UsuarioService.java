package com.unicesumar.observa_aep.service;

import com.unicesumar.observa_aep.mapper.UsuarioMapper;
import com.unicesumar.observa_aep.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

}
