CREATE TABLE paises(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       nombre VARCHAR(150) NOT NULL
);
CREATE TABLE departamentos(
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              nombre VARCHAR(150) NOT NULL,
                              id_pais INT,
                              FOREIGN KEY(id_pais) REFERENCES paises(id)
);
CREATE TABLE municipios(
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(150) NOT NULL,
                           id_departamento INT,
                           FOREIGN KEY(id_departamento) REFERENCES departamentos(id)
);
CREATE TABLE direcciones(
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            id_pais INT,
                            id_departamento INT,
                            id_municipio INT,
                            calle VARCHAR(100),
                            carrera VARCHAR(100),
                            coordenada VARCHAR(200),
                            descripcion VARCHAR(200),
                            FOREIGN KEY(id_pais) REFERENCES paises(id),
                            FOREIGN KEY(id_departamento) REFERENCES departamentos(id),
                            FOREIGN KEY(id_municipio) REFERENCES municipios(id)
);
CREATE TABLE personales(
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           nombre VARCHAR(255) NOT NULL,
                           apellidos VARCHAR(255) NOT NULL,
                           id_direccion INT,
                           FOREIGN KEY(id_direccion) REFERENCES direcciones(id)
);
CREATE TABLE cargos (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        nombre VARCHAR(50)
);
CREATE TABLE empleados (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           id_cargo INT,
                           salario DOUBLE,
                           id_persona INT,
                           FOREIGN KEY (id_persona) REFERENCES personales(id),
                           FOREIGN KEY (id_cargo) REFERENCES cargos(id)
);
CREATE TABLE estudiantes (
                             id INT PRIMARY KEY,
                             codigo VARCHAR(20),
                             programa VARCHAR(100),
                             promedio DOUBLE,
                             FOREIGN KEY (id) REFERENCES personales(id)
);
CREATE TABLE estudiantesinscrito (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                id_estudiante int,
                                foreign key (id_estudiante) references estudiantes(id)
);

CREATE TABLE PersonalInscrito (
                                id INT AUTO_INCREMENT PRIMARY KEY,
                                id_personal int,
                                foreign key (id_personal) references personales(id)
);

CREATE TABLE listadoTodos(
                             id int AUTO_INCREMENT PRIMARY KEY,
                             id_persona int,
                             id_cargo int,
                             FOREIGN KEY (id_persona) REFERENCES personales(id),
                             FOREIGN KEY (id_cargo) REFERENCES cargos(id)
);