-- MIGRATION V1 - CREATE TABLES

-- TABELA: usuarios
CREATE TABLE public.usuarios (
    id           bigserial        NOT NULL,
    criado_por   int8,
    nome         varchar(150)     NOT NULL,
    cpf          char(11)         NOT NULL,
    email        varchar(150)     NOT NULL,
    senha        varchar(255)     NOT NULL,
    tipo_usuario char(1)          NOT NULL,
    data_criacao timestamp        DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ativo        bool             DEFAULT true NOT NULL,

    CONSTRAINT usuarios_pk          PRIMARY KEY (id),
    CONSTRAINT usuarios_email_key   UNIQUE (email),
    CONSTRAINT usuarios_unique_cpf  UNIQUE (cpf),
    CONSTRAINT fk_criado_por        FOREIGN KEY (criado_por) REFERENCES public.usuarios(id),
    CONSTRAINT chk_tipo_usuario     CHECK (tipo_usuario IN ('A', 'C', 'G', 'S'))
);

-- TABELA: categorias
CREATE TABLE public.categorias (
    id           bigserial       NOT NULL,
    categoria    varchar(150)    NOT NULL,
    descricao    text            NOT NULL,
    data_criacao timestamp       DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ativo        bool            DEFAULT true NOT NULL,

    CONSTRAINT categorias_pk PRIMARY KEY (id)
);

-- TABELA: enderecos
CREATE TABLE public.enderecos (
    id           bigserial       NOT NULL,
    cep          varchar(10)     NOT NULL,
    logradouro   varchar(150)    NOT NULL,
    numero       varchar(10)     NOT NULL,
    complemento  varchar(100)    NULL,
    bairro       varchar(100)    NOT NULL,
    cidade       varchar(100)    NOT NULL,
    estado       char(2)         NOT NULL,
    data_criacao timestamp       DEFAULT CURRENT_TIMESTAMP NOT NULL,
    ativo        bool            DEFAULT true NOT NULL,

    CONSTRAINT enderecos_pk PRIMARY KEY (id)
);

-- TABELA: solicitacoes
CREATE TABLE public.solicitacoes (
    id              bigserial       NOT NULL,
    id_categoria    int8            NOT NULL,
    id_solicitante  int8            NOT NULL,
    id_atendente    int8            NULL,
    id_endereco     int8            NOT NULL,
    status          varchar(20)     NOT NULL,
    prioridade      varchar(10)     NULL,
    anonimo         bool            DEFAULT false NOT NULL,
    titulo          varchar(150)    NOT NULL,
    descricao       text            NOT NULL,
    data_solicitada timestamp       DEFAULT CURRENT_TIMESTAMP NOT NULL,
    data_prazo      timestamp       NULL,
    data_finalizada timestamp       NULL,
    observacao      text            NULL,

    CONSTRAINT solicitacoes_pk      PRIMARY KEY (id),
    CONSTRAINT fk_id_categoria      FOREIGN KEY (id_categoria)   REFERENCES public.categorias(id),
    CONSTRAINT fk_id_solicitante    FOREIGN KEY (id_solicitante) REFERENCES public.usuarios(id),
    CONSTRAINT fk_id_atendente      FOREIGN KEY (id_atendente)   REFERENCES public.usuarios(id),
    CONSTRAINT fk_id_endereco       FOREIGN KEY (id_endereco)    REFERENCES public.enderecos(id),
    CONSTRAINT chk_status           CHECK (status IN ('AGUARDANDO_APROVACAO', 'APROVADA', 'AGUARDANDO_ATENDIMENTO', 'EM_ANDAMENTO', 'FINALIZADO', 'REJEITADA')),
    CONSTRAINT chk_prioridade       CHECK (prioridade IN ('BAIXA', 'MODERADA', 'MEDIA', 'ALTA', 'URGENTE'))
);

-- TABELA: historico_movimentacao_solicitacao
CREATE TABLE public.historico_movimentacao_solicitacao (
    id               bigserial   NOT NULL,
    id_solicitacao   int8        NOT NULL,
    id_responsavel   int8        NOT NULL,
    comentario       text        NOT NULL,
    status_atual     varchar(20) NOT NULL,
    status_anterior  varchar(20) NULL,
    data_movimentacao timestamp  DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT historico_movimentacao_pk    PRIMARY KEY (id),
    CONSTRAINT fk_historico_solicitacao     FOREIGN KEY (id_solicitacao) REFERENCES public.solicitacoes(id),
    CONSTRAINT fk_historico_responsavel     FOREIGN KEY (id_responsavel) REFERENCES public.usuarios(id),
    CONSTRAINT chk_status_atual             CHECK (status_atual    IN ('AGUARDANDO_APROVACAO', 'APROVADA', 'AGUARDANDO_ATENDIMENTO', 'EM_ANDAMENTO', 'FINALIZADO', 'REJEITADA')),
    CONSTRAINT chk_status_anterior          CHECK (status_anterior IN ('AGUARDANDO_APROVACAO', 'APROVADA', 'AGUARDANDO_ATENDIMENTO', 'EM_ANDAMENTO', 'FINALIZADO', 'REJEITADA'))
);

-- TABELA: logs
CREATE TABLE public.logs (
    id              bigserial       NOT NULL,
    id_usuario      int8            NOT NULL,
    nome_tabela     varchar(100)    NOT NULL,
    acao            varchar(50)     NOT NULL,
    dados_alterados jsonb           NOT NULL,
    data_execucao   timestamp       DEFAULT CURRENT_TIMESTAMP NOT NULL,

    CONSTRAINT logs_pk          PRIMARY KEY (id),
    CONSTRAINT fk_logs_usuario  FOREIGN KEY (id_usuario) REFERENCES public.usuarios(id),
    CONSTRAINT chk_acao         CHECK (acao IN ('INSERT', 'UPDATE', 'DELETE'))
);