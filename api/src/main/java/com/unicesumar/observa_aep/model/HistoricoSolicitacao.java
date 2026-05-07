package com.unicesumar.observa_aep.model;

import com.unicesumar.observa_aep.enums.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "historico_movimentacao_solicitacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoSolicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_solicitacao", nullable = false)
    private Solicitacao solicitacao;

    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false)
    private Usuario responsavel;

    @Column(nullable = false, columnDefinition = "text")
    private String comentario;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_atual", nullable = false)
    private StatusSolicitacao statusAtual;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_anterior")
    private StatusSolicitacao statusAnterior;

    @Column(name = "data_movimentacao")
    private LocalDateTime dataMovimentacao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HistoricoSolicitacao)) return false;
        HistoricoSolicitacao that = (HistoricoSolicitacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
