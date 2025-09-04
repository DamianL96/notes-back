CREATE TABLE usuarios(
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fecha_creacion DATETIME NOT NULL,

    PRIMARY KEY(id)
);