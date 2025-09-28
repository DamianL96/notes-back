CREATE TABLE notas(
    id BIGINT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(100) NOT NULL,
    cuerpo TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    fecha_modificacion DATETIME,
    id_ultimo_modificador BIGINT,

    PRIMARY KEY(id)
);