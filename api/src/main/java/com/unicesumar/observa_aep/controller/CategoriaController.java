package com.unicesumar.observa_aep.controller;

import com.unicesumar.observa_aep.dto.categoria.CategoriaRequestDTO;
import com.unicesumar.observa_aep.dto.categoria.CategoriaResponseDTO;
import com.unicesumar.observa_aep.exception.AcessoNegadoException;
import com.unicesumar.observa_aep.exception.EntidadeNaoEncontradaException;
import com.unicesumar.observa_aep.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller responsável pelo gerenciamento de Categorias.
 *
 * <p>Expõe endpoints REST para criação, consulta, atualização e
 * ativação/desativação de categorias.
 */
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    /**
     * Cria uma nova Categoria.
     *
     * <p>Restrições:
     * <ul>
     *   <li>Apenas usuários com perfil ADMIN podem executar esta operação</li>
     * </ul>
     *
     * @param requestDTO dados da categoria a ser criada
     * @param uriBuilder builder para construção da URI do recurso criado
     * @return status {@code 201 Created} com a URI da categoria criada e o DTO no corpo
     * @throws AcessoNegadoException se o usuário não for ADMIN
     */
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> salvarCategoria(
            @RequestBody @Valid CategoriaRequestDTO requestDTO,
            UriComponentsBuilder uriBuilder) {

        CategoriaResponseDTO response = service.salvarCategoria(requestDTO);

        URI uri = uriBuilder
                .path("/categorias/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(uri).body(response);
    }

    /**
     * Busca uma Categoria pelo seu identificador único.
     *
     * @param id identificador da categoria
     * @return status {@code 200 OK} com o DTO da categoria encontrada
     * @throws EntidadeNaoEncontradaException se nenhuma categoria for encontrada com o id informado
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarCategoria(id));
    }

    /**
     * Lista todas as Categorias cadastradas, incluindo as inativas.
     *
     * <p>Restrições:
     * <ul>
     *   <li>Apenas usuários com perfil ADMIN podem executar esta operação</li>
     * </ul>
     *
     * @return status {@code 200 OK} com a lista de todas as categorias
     * @throws AcessoNegadoException se o usuário não for ADMIN
     */
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        return ResponseEntity.ok(service.listarCategorias());
    }

    /**
     * Lista todas as Categorias com status ativo.
     *
     * <p>Acessível por qualquer usuário autenticado.
     *
     * @return status {@code 200 OK} com a lista de categorias ativas
     */
    @GetMapping("/ativas")
    public ResponseEntity<List<CategoriaResponseDTO>> buscarAtivos() {
        return ResponseEntity.ok(service.buscarAtivos());
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
     * @return status {@code 200 OK} com o DTO atualizado
     * @throws AcessoNegadoException se o usuário não for ADMIN
     * @throws EntidadeNaoEncontradaException se nenhuma categoria for encontrada com o id informado
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(
            @PathVariable Long id,
            @RequestBody @Valid CategoriaRequestDTO requestDTO) {

        return ResponseEntity.ok(service.atualizarCategoria(id, requestDTO));
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
     * @return status {@code 200 OK} com o DTO atualizado
     * @throws AcessoNegadoException se o usuário não for ADMIN
     * @throws EntidadeNaoEncontradaException se nenhuma categoria for encontrada com o id informado
     */
    @PatchMapping("/{id}/ativar")
    public ResponseEntity<CategoriaResponseDTO> ativarCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(service.ativarCategoria(id));
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
     * @return status {@code 200 OK} com o DTO atualizado
     * @throws AcessoNegadoException se o usuário não for ADMIN
     * @throws EntidadeNaoEncontradaException se nenhuma categoria for encontrada com o id informado
     */
    @PatchMapping("/{id}/desativar")
    public ResponseEntity<CategoriaResponseDTO> desativarCategoria(@PathVariable Long id) {
        return ResponseEntity.ok(service.desativarCategoria(id));
    }
}