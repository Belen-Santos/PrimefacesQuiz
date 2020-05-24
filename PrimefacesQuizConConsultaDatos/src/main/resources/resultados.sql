CREATE DATABASE resultados;
USE resultados;

CREATE TABLE usuario (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE pregunta (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `texto` varchar(50) NOT NULL,
  `link`  varchar(100) NOT NULL,
  `op1` varchar(50) NOT NULL,
  `op2` varchar(50) NOT NULL,
  `op3` varchar(50),
  `op4` varchar(50),
  `op5` varchar(50),
  `correcta` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB CHARACTER SET=utf8;

CREATE TABLE respuesta (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `idPregunta` int(10) NOT NULL,
  `idUsuario`  int(10) NOT NULL,
  `texto` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`idPregunta`) REFERENCES pregunta(id),
  FOREIGN KEY (`idUsuario`) REFERENCES usuario(id)
) ENGINE=InnoDB CHARACTER SET=utf8;


INSERT INTO PREGUNTA (texto,link,op1,op2,op3,correcta) VALUES ('Autor del lenguaje','http://servidor.iespoligonosur.org/Materiales/Modulos/ED/fotos/b1.jpg','python','ruby','ninguna','ninguna');

INSERT INTO PREGUNTA (texto,link,op1,op2,op3,op4,op5,correcta) VALUES ('Quién descubrió América','https://www.xlsemanal.com/wp-content/uploads/sites/3/2017/07/colon-descubrimiento-America.jpg','rebeca','lopera','Colón','Berta','ninguna','Colón');

INSERT INTO PREGUNTA (texto,link,op1,op2,op3,correcta) VALUES ('Luego del puente estaremos en','https://fotos02.deia.eus/2020/05/11/690x278/terrazas-ledesma-fase1.jpg','julio','marzo', 'abril','marzo');
