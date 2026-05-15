package com.unicesumar.observa_aep.service;

import com.unicesumar.observa_aep.dto.categoria.CategoriaRequestDTO;
import com.unicesumar.observa_aep.dto.categoria.CategoriaResponseDTO;
import com.unicesumar.observa_aep.exception.AcessoNegadoException;
import com.unicesumar.observa_aep.exception.EntidadeNaoEncontradaException;
import com.unicesumar.observa_aep.mapper.CategoriaMapper;
import com.unicesumar.observa_aep.model.Categoria;
import com.unicesumar.observa_aep.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoriaService {
    private final SecurityService security;
    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    public CategoriaService(CategoriaRepository repository,
                            CategoriaMapper mapper, SecurityService security) {
        this.repository = repository;
        this.mapper = mapper;
        this.security = security;
    }

    /**
     * Salva uma nova Categoria no banco de dados.
     *
     * <p>Restrições:
     * <ul>
     *   <li>Apenas usuários com perfil ADMIN podem executar esta operação</li>
     *   <li>A categoria é criada com status ativo e data de criação preenchida automaticamente</li>
     * </ul>
     *
     * @param requestDTO dados da categoria a ser criada
     * @return DTO com os dados da categoria salva
     * @throws AcessoNegadoException se o usuário não for ADMIN
     */
    public CategoriaResponseDTO salvarCategoria(CategoriaRequestDTO requestDTO) {
        security.requireAdmin();

        Categoria categoria = mapper.toEntity(requestDTO);
        categoria.setAtivo(true);
        categoria.setDataCriacao(LocalDateTime.now());

        return mapper.toResponseDTO(repository.save(categoria));
    }

    /**
     * Busca uma Categoria pelo seu identificador único.
     *
     * @param id identificador da categoria
     * @return DTO com os dados da categoria encontrada
     * @throws EntidadeNaoEncontradaException se nenhuma categoria for encontrada com o id informado
     */
    public CategoriaResponseDTO buscarCategoria(Long id) {
        return mapper.toResponseDTO(buscarOuFalhar(id));
    }

    /**
     * Lista todas as Categorias cadastradas, incluindo as inativas.
     *
     * <p>Restrições:
     * <ul>
     *   <li>Apenas usuários com perfil ADMIN podem executar esta operação</li>
     * </ul>
     *
     * @return lista de DTOs com todas as categorias
     * @throws AcessoNegadoException se o usuário não for ADMIN
     */
    public List<CategoriaResponseDTO> listarCategorias() {
        security.requireAdmin();
        return mapper.toResponseDTOList(repository.findAll());
    }

    /**
     * Lista todas as Categorias com status ativo.
     *
     * <p>Acessível por qualquer usuário autenticado.
     *
     * @return lista de DTOs com as categorias ativas
     */
    public List<CategoriaResponseDTO> buscarAtivos() {
        return repository.findByAtivo(true)
                .stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    /**
     * Ativa uma Categoria previamente desativada.
     *
     * <p>Restrições:
     * <ul>
     *   <li>Apenas usuários com perfil ADMIN podem executar esta operação</li>
     * </ul>
     *
     * @param id identificador da categoria a ser ativada
     * @return DTO com os dados atualizados da categoria
     * @throws AcessoNegadoException se o usuário não for ADMIN
     * @throws EntidadeNaoEncontradaException se nenhuma categoria for encontrada com o id informado
     */
    public CategoriaResponseDTO ativarCategoria(Long id) {
        security.requireAdmin();

        Categoria categoria = buscarOuFalhar(id);
        categoria.setAtivo(true);

        return mapper.toResponseDTO(repository.save(categoria));
    }

    /**
     * Desativa uma Categoria, removendo-a da listagem pública.
     *
     * <p>Restrições:
     * <ul>
     *   <li>Apenas usuários com perfil ADMIN podem executar esta operação</li>
     * </ul>
     *
     * @param id identificador da categoria a ser desativada
     * @return DTO com os dados atualizados da categoria
     * @throws AcessoNegadoException se o usuário não for ADMIN
     * @throws EntidadeNaoEncontradaException se nenhuma categoria for encontrada com o id informado
     */
    public CategoriaResponseDTO desativarCategoria(Long id) {
        security.requireAdmin();

        Categoria categoria = buscarOuFalhar(id);
        categoria.setAtivo(false);

        return mapper.toResponseDTO(repository.save(categoria));
    }

    /**
     * Atualiza os dados de uma Categoria existente.
     *
     * <p>Restrições:
     * <ul>
     *   <li>Apenas usuários com perfil ADMIN podem executar esta operação</li>
     * </ul>
     *
     * @param id identificador da categoria a ser atualizada
     * @param requestDTO dados atualizados da categoria
     * @return DTO com os dados atualizados da categoria
     * @throws AcessoNegadoException se o usuário não for ADMIN
     * @throws EntidadeNaoEncontradaException se nenhuma categoria for encontrada com o id informado
     */
    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaRequestDTO requestDTO) {
        security.requireAdmin();

        Categoria categoria = buscarOuFalhar(id);
        categoria.setCategoria(requestDTO.categoria());
        categoria.setDescricao(requestDTO.descricao());

        return mapper.toResponseDTO(repository.save(categoria));
    }

    /**
     * Busca uma Categoria pelo id ou lança exceção caso não encontrada.
     *
     * <p>Método utilitário interno para reuso entre os demais métodos do service,
     * evitando duplicação da lógica de busca e tratamento de não encontrado.
     *
     * @param id identificador da categoria
     * @return entidade Categoria encontrada
     * @throws EntidadeNaoEncontradaException se nenhuma categoria for encontrada com o id informado
     */
    private Categoria buscarOuFalhar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Categoria"));
    }
}
