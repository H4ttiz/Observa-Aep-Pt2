package com.unicesumar.observa_aep.model;

import com.unicesumar.observa_aep.enums.NivelPrioridade;
import com.unicesumar.observa_aep.enums.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "solicitacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_solicitante")
    private Usuario solicitante; // null se anônimo

    @ManyToOne
    @JoinColumn(name = "id_atendente")
    private Usuario atendente;

    @ManyToOne
    @JoinColumn(name = "id_endereco", nullable = false)
    private Endereco endereco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSolicitacao status;

    @Enumerated(EnumType.STRING)
    private NivelPrioridade prioridade;

    @Column(nullable = false)
    private boolean anonimo;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(nullable = false, columnDefinition = "text")
    private String descricao;

    @Column(name = "data_solicitada")
    private LocalDateTime dataSolicitada;

    @Column(name = "data_prazo")
    private LocalDateTime dataPrazo;

    @Column(name = "data_finalizada")
    private LocalDateTime dataFinalizada;

    @Column(columnDefinition = "text")
    private String observacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solicitacao)) return false;
        Solicitacao that = (Solicitacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
