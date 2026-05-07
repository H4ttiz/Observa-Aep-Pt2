package com.unicesumar.observa_aep.model;

import com.unicesumar.observa_aep.enums.TipoLog;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "logs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "nome_tabela", nullable = false, length = 100)
    private String nomeTabela;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private TipoLog acao;

    @Column(name = "dados_alterados", nullable = false, columnDefinition = "jsonb")
    private String dadosAlterados;

    @Column(name = "data_execucao")
    private LocalDateTime dataExecucao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        Log that = (Log) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
