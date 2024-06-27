CREATE TYPE status_enum AS ENUM ('Não iniciado', 'Em andamento', 'Finalizado');
CREATE TYPE prioridade_enum AS ENUM ('Alta', 'Média', 'Baixa');
CREATE TYPE complexidade_enum AS ENUM ('Alta', 'Média', 'Baixa');
CREATE TYPE nivel_enum AS ENUM ('Usuário', 'Admin');
CREATE TYPE tipo_enum as ENUM ('Funcional', 'Não funcional');

CREATE TABLE usuario (
	id SERIAL NOT NULL,
	nome VARCHAR(100),
	email VARCHAR(100),
	senha VARCHAR(60),
	nivel nivel_enum,
	CONSTRAINT pk_usuario PRIMARY KEY(id)
);

INSERT INTO usuario VALUES (default, 'Felipe', 'felipe@email.com', 'Felipe', 'Admin');

CREATE TABLE projeto (
	id SERIAL NOT NULL,
	nome VARCHAR(100),
	descricao VARCHAR(250),
	dataInicio DATE,
	status status_enum,
	usuario_id INT,
	CONSTRAINT pk_projeto PRIMARY KEY(id),
	CONSTRAINT fk_projeto_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);

CREATE TABLE requisito (
	id SERIAL NOT NULL,
	nome VARCHAR(100),
        tipo tipo_enum,
	descricao VARCHAR(250),
        projeto_id INT,
	prioridade prioridade_enum,
	complexidade complexidade_enum,
	versao_atual INT,
	CONSTRAINT pk_requisito PRIMARY KEY(id),
	CONSTRAINT fk_requisito_projeto FOREIGN KEY (projeto_id) REFERENCES projeto(id)
);

CREATE TABLE historico_versoes (
	id SERIAL NOT NULL,
	id_requisito INT,
	nome VARCHAR(100),
        tipo tipo_enum,
	descricao VARCHAR(250),
	prioridade prioridade_enum,
	complexidade complexidade_enum,
	versao INT,
	CONSTRAINT pk_historico PRIMARY KEY(id),
	CONSTRAINT fk_historico_requisito FOREIGN KEY (id_requisito) REFERENCES requisito(id)
);