ALTER TABLE usuarios ALTER COLUMN tipo_usuario TYPE VARCHAR(255);
ALTER TABLE enderecos ALTER COLUMN estado TYPE VARCHAR(2);

ALTER TABLE solicitacoes ALTER COLUMN id_solicitante DROP NOT NULL;

ALTER TABLE usuarios DROP CONSTRAINT chk_tipo_usuario;

UPDATE usuarios SET tipo_usuario = CASE tipo_usuario
    WHEN 'A' THEN 'ADMIN'
    WHEN 'C' THEN 'CIDADAO'
    WHEN 'G' THEN 'GESTOR'
    WHEN 'S' THEN 'ATENDENTE'
END;