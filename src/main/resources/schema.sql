DROP TABLE IF EXISTS pelicula;

CREATE TABLE IF NOT EXISTS pelicula(
	id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR NOT NULL,
    sinopsis VARCHAR,
    fecha_estreno VARCHAR,
    poster_url VARCHAR
);

CREATE TABLE IF NOT EXISTS actor(
	id INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR NOT NULL,
    fecha_nacimiento VARCHAR,
    foto_url VARCHAR,
    pelicula_id INT
);

CREATE TABLE IF NOT EXISTS actor_pelicula(
	pelicula_id int NOT NULL,
	actor_id int NOT NULL
);


INSERT INTO pelicula (titulo, sinopsis, fecha_estreno, poster_url) values('The Dark Knight','Batman vs Guason','2008-07-15','https://http2.mlstatic.com/D_NQ_NP_762347-MLA31840426768_082019-O.jpg');
INSERT INTO pelicula (titulo, sinopsis, fecha_estreno, poster_url) values('Inception','Sueños de sueños','2010-07-10','https://m.media-amazon.com/images/I/71uKM+LdgFL._AC_SY741_.jpg');
INSERT INTO pelicula (titulo, sinopsis, fecha_estreno, poster_url) values('Interstellar','Viajes intergalacticos','2014-08-05','https://i.pinimg.com/originals/26/ee/c3/26eec32582fabc16d00cb64f37f2a393.jpg');
INSERT INTO pelicula (titulo, sinopsis, fecha_estreno, poster_url) values('Oppenheimer','La historia detras de la bomba atomica','2023-07-21','https://cloudfront-us-east-1.images.arcpublishing.com/infobae/VLFHY43STVH3PBSEULWXPRCSIY.jpeg');

INSERT INTO actor (nombre, fecha_nacimiento, foto_url) values('Christian Bale','1969-05-15','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/qCpZn2e3dimwbryLnqxZuI88PTi.jpg');
INSERT INTO actor (nombre, fecha_nacimiento, foto_url) values('Leonardo DiCaprio','1967-02-10','https://www.themoviedb.org/t/p/w300_and_h450_bestv2/wo2hJpn04vbtmh0B9utCFdsQhxM.jpg');

INSERT INTO actor_pelicula (pelicula_id, actor_id) values(1,1);
INSERT INTO actor_pelicula (pelicula_id, actor_id) values(2,1);
INSERT INTO actor_pelicula (pelicula_id, actor_id) values(3,1);
INSERT INTO actor_pelicula (pelicula_id, actor_id) values(3,2);
INSERT INTO actor_pelicula (pelicula_id, actor_id) values(4,2);