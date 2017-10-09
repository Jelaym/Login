DROP DATABASE IF EXISTS usuarios;
CREATE DATABASE usuarios;
USE usuarios;

CREATE TABLE usuario(
	id MEDIUMINT NOT NULL AUTO_INCREMENT,
	nombre CHAR(50) NOT NULL,
    contraseña CHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO usuario (nombre, contraseña) 
VALUES ('David', '5IM8');

SELECT * FROM usuario WHERE nombre='David' AND contraseña='5IM8';