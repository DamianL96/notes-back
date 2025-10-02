CREATE TABLE colaboraciones(
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_nota BIGINT NOT NULL,
    rol ENUM('PROPIETARIO','EDITOR','LECTOR') NOT NULL,
    creado_en DATETIME,

    CONSTRAINT fk_usuario FOREIGN KEY(id_usuario) REFERENCES usuarios(id),
    CONSTRAINT fk_nota FOREIGN KEY(id_nota) REFERENCES notas(id)

);