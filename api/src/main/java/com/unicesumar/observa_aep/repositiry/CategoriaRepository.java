package com.unicesumar.observa_aep.repositiry;

import com.unicesumar.observa_aep.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByAtivo(boolean ativo);
    boolean existsByCategoria(String categoria);
}
