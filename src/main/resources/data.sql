
CREATE TABLE usuario (
                          id          VARCHAR2(20) NOT NULL,
                          nombre      VARCHAR2(20),
                          contrasenia VARCHAR2(30),
                          telefono    VARCHAR2(30)
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );



CREATE TABLE afiliado (
                          id          VARCHAR2(20) NOT NULL,

);

ALTER TABLE afiliado ADD CONSTRAINT afiliado_pk PRIMARY KEY ( id );

CREATE TABLE centroservicio (
                                id                VARCHAR2(20) NOT NULL,
                                tipocentro_codigo VARCHAR2(20) NOT NULL
);

ALTER TABLE centroservicio ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );

CREATE TABLE consulta (
                          codigo            VARCHAR2(20) NOT NULL,
                          fecha_cita        DATE,
                          descripcion       VARCHAR2(300),
                          centroservicio_id VARCHAR2(5) NOT NULL,
                          mascota_id        VARCHAR2(20) NOT NULL
);

ALTER TABLE consulta ADD CONSTRAINT consulta_pk PRIMARY KEY ( codigo );

CREATE TABLE empleadosafepet (
                                 id          VARCHAR2(20) NOT NULL,
                                 nombre      VARCHAR2(50),
                                 contrasenia VARCHAR2(20)
);

ALTER TABLE empleadosafepet ADD CONSTRAINT empleado_pk PRIMARY KEY ( id );

CREATE TABLE evaluacion (
                            id_evaluacion              VARCHAR2(20) NOT NULL,
                            puntuacion               INTEGER,
                            afiliado_id                VARCHAR2(20) NOT NULL,
                            planservicio_servicio_idcs VARCHAR2(30) NOT NULL,
                            planservicio_servicioc_id  INTEGER NOT NULL,
                            planservicio_id            VARCHAR2(20) NOT NULL
);

CREATE UNIQUE INDEX evaluacion__idx ON
    evaluacion (
                id_evaluacion
                ASC );

ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_pk PRIMARY KEY ( id_evaluacion );

CREATE TABLE examenes (
                          codigo VARCHAR2(20) NOT NULL,
                          nombre VARCHAR2(20)
);

ALTER TABLE examenes ADD CONSTRAINT examenes_pk PRIMARY KEY ( codigo );

CREATE TABLE examenes_centro (
                                 centroservicio_id VARCHAR2(20) NOT NULL,
                                 examenes_codigo   VARCHAR2(20) NOT NULL
);
ALTER TABLE examenes_centro ADD CONSTRAINT examenes_centro_pk PRIMARY KEY ( centroservicio_id);
ALTER TABLE examenes_centro ADD CONSTRAINT examenes_centro_pk PRIMARY KEY ( examenes_codigo;

CREATE TABLE historiaclinica (
                                 nombre        VARCHAR2(20) NOT NULL,
                                 sexo          VARCHAR2(20),
                                 fecha_ingreso DATE,
                                 fechasalida   DATE,
                                 mascota_id    VARCHAR2(20) NOT NULL
);

CREATE UNIQUE INDEX historiaclinica__idx ON
    historiaclinica (
                     mascota_id
                     ASC );

ALTER TABLE historiaclinica ADD CONSTRAINT historiaclinica_pk PRIMARY KEY ( mascota_id );

CREATE TABLE mascota (
                         id               VARCHAR2(20) NOT NULL,
                         nombre           VARCHAR2(20),
                         fecha_nacimiento DATE,
                         genero           VARCHAR2(20),
                         plan_id          VARCHAR2(5) NOT NULL,
                         raza_codigo      VARCHAR2(20) NOT NULL,
                         tipomascota_id   VARCHAR2(20) NOT NULL
);

ALTER TABLE mascota ADD CONSTRAINT mascota_pk PRIMARY KEY ( id );

CREATE TABLE plan (
                      id                 VARCHAR2(20) NOT NULL,
                      mensualidad        NUMBER,
                      copago             NUMBER,
                      afiliado_id        VARCHAR2(20) NOT NULL,
                      empleadosafepet_id VARCHAR2(20) NOT NULL
);

ALTER TABLE plan ADD CONSTRAINT plan_pk PRIMARY KEY ( id );

CREATE TABLE planservicio (
                              id                   VARCHAR2(20) NOT NULL,
                              fecha_servicio       DATE,
                              servicio_idcs        VARCHAR2(30) NOT NULL,
                              servicioc_id         INTEGER NOT NULL,
                              plan_id              VARCHAR2(20) NOT NULL,
                              serviciocentro_idcen VARCHAR2(5) NOT NULL,
                              serviciocentro_idser VARCHAR2(20) NOT NULL
);

ALTER TABLE planservicio
    ADD CONSTRAINT planservicio_pk PRIMARY KEY ( servicio_idcs,
                                                 servicioc_id,
                                                 id );

CREATE TABLE raza (
                      codigo VARCHAR2(20) NOT NULL,
                      nombre VARCHAR2(20) NOT NULL
);

ALTER TABLE raza ADD CONSTRAINT raza_pk PRIMARY KEY ( codigo );

CREATE TABLE registro (
                          codigo                     VARCHAR2(20) NOT NULL,
                          observaciones              VARCHAR2(20),
                          concepto                   VARCHAR2(20),
                          decisiones                 VARCHAR2(20),
                          fecharegistro              DATE,
                          examenes_codigo            VARCHAR2(20) NOT NULL,
                          historiaclinica_mascota_id VARCHAR2(20) NOT NULL
);

ALTER TABLE registro ADD CONSTRAINT registro_pk PRIMARY KEY ( codigo );

CREATE TABLE servicio (
                          id     VARCHAR2(20) NOT NULL,
                          nombre VARCHAR2(50),
                          valor  INTEGER
);

ALTER TABLE servicio ADD CONSTRAINT servicio_pk PRIMARY KEY ( id );

CREATE TABLE serviciocentro (
                                idser       VARCHAR2(20) NOT NULL,
                                idcen       VARCHAR2(5) NOT NULL,
                                servicio_id VARCHAR2(20) NOT NULL
);

ALTER TABLE serviciocentro ADD CONSTRAINT servicio_centro_pk PRIMARY KEY ( idcen,
                                                                           idser );

CREATE TABLE tipocentro (
                            codigo VARCHAR2(20) NOT NULL,
                            nombre VARCHAR2(20)
);

ALTER TABLE tipocentro ADD CONSTRAINT tipocentro_pk PRIMARY KEY ( codigo );

CREATE TABLE tipomascota (
                             id   VARCHAR2(20) NOT NULL,
                             tipo VARCHAR2(20)
);

ALTER TABLE tipomascota ADD CONSTRAINT tipomascota_pk PRIMARY KEY ( id );

ALTER TABLE centroservicio
    ADD CONSTRAINT centroservicio_tipocentro_fk FOREIGN KEY ( tipocentro_codigo )
        REFERENCES tipocentro ( codigo );

ALTER TABLE consulta
    ADD CONSTRAINT consulta_centroservicio_fk FOREIGN KEY ( centroservicio_id )
        REFERENCES centroservicio ( id );

ALTER TABLE consulta
    ADD CONSTRAINT consulta_mascota_fk FOREIGN KEY ( mascota_id )
        REFERENCES mascota ( id );

ALTER TABLE evaluacion
    ADD CONSTRAINT evaluacion_afiliado_fk FOREIGN KEY ( afiliado_id )
        REFERENCES afiliado ( id );

ALTER TABLE evaluacion
    ADD CONSTRAINT evaluacion_planservicio_fk FOREIGN KEY ( planservicio_servicio_idcs,
                                                            planservicio_servicioc_id,
                                                            planservicio_id )
        REFERENCES planservicio ( servicio_idcs,
                                  servicioc_id,
                                  id );

ALTER TABLE examenes_centro
    ADD CONSTRAINT examenes_centro__fk FOREIGN KEY ( examenes_codigo )
        REFERENCES examenes ( codigo );

ALTER TABLE examenes_centro
    ADD CONSTRAINT examenes_centro_servicio_fk FOREIGN KEY ( centroservicio_id )
        REFERENCES centroservicio ( id );

ALTER TABLE historiaclinica
    ADD CONSTRAINT historiaclinica_mascota_fk FOREIGN KEY ( mascota_id )
        REFERENCES mascota ( id );

ALTER TABLE mascota
    ADD CONSTRAINT mascota_plan_fk FOREIGN KEY ( plan_id )
        REFERENCES plan ( id );

ALTER TABLE mascota
    ADD CONSTRAINT mascota_raza_fk FOREIGN KEY ( raza_codigo )
        REFERENCES raza ( codigo );

ALTER TABLE mascota
    ADD CONSTRAINT mascota_tipomascota_fk FOREIGN KEY ( tipomascota_id )
        REFERENCES tipomascota ( id );

ALTER TABLE plan
    ADD CONSTRAINT plan_afiliado_fk FOREIGN KEY ( afiliado_id )
        REFERENCES afiliado ( id );

ALTER TABLE plan
    ADD CONSTRAINT plan_empleadosafepet_fk FOREIGN KEY ( empleadosafepet_id )
        REFERENCES empleadosafepet ( id );

ALTER TABLE planservicio
    ADD CONSTRAINT planservicio_plan_fk FOREIGN KEY ( plan_id )
        REFERENCES plan ( id );

ALTER TABLE planservicio
    ADD CONSTRAINT planservicio_serviciocentro_fk FOREIGN KEY ( serviciocentro_idcen,
                                                                serviciocentro_idser )
        REFERENCES serviciocentro ( idcen,
                                    idser );

ALTER TABLE registro
    ADD CONSTRAINT registro_examenes_fk FOREIGN KEY ( examenes_codigo )
        REFERENCES examenes ( codigo );

ALTER TABLE registro
    ADD CONSTRAINT registro_historiaclinica_fk FOREIGN KEY ( historiaclinica_mascota_id )
        REFERENCES historiaclinica ( mascota_id );

ALTER TABLE serviciocentro
    ADD CONSTRAINT servicio_centroservicio_fk FOREIGN KEY ( idcen )
        REFERENCES centroservicio ( id );

ALTER TABLE serviciocentro
    ADD CONSTRAINT serviciocentro_servicio_fk FOREIGN KEY ( servicio_id )
        REFERENCES servicio ( id );

---- INSERT RAZA ---

INSERT INTO RAZA (codigo, nombre) VALUES ('1','CRIOLLO');
INSERT INTO RAZA (codigo, nombre) VALUES ('2','BULDOG');
INSERT INTO RAZA (codigo, nombre) VALUES ('3','CHIHUAHUA');
INSERT INTO RAZA (codigo, nombre) VALUES ('4','LABRADOR');
INSERT INTO RAZA (codigo, nombre) VALUES ('5','DOBERMAN');
INSERT INTO RAZA (codigo, nombre) VALUES ('6','ROTTWEILER');
INSERT INTO RAZA (codigo, nombre) VALUES ('7','PERSA');
INSERT INTO RAZA (codigo, nombre) VALUES ('8','SIAMES');
INSERT INTO RAZA (codigo, nombre) VALUES ('9','RAGDOLL');
INSERT INTO RAZA (codigo, nombre) VALUES ('10','HOLANDES');

----  INSERT TIPOMASCOTA

INSERT INTO tipomascota (id, tipo) VALUES ('1', 'PERRO');
INSERT INTO tipomascota (id, tipo) VALUES ('2', 'GATO');
INSERT INTO tipomascota (id, tipo) VALUES ('3', 'HAMSTER');
INSERT INTO tipomascota (id, tipo) VALUES ('4', 'CONEJO');
INSERT INTO tipomascota (id, tipo) VALUES ('5', 'PAJARO');
INSERT INTO tipomascota (id, tipo) VALUES ('6', 'HURON');
INSERT INTO tipomascota (id, tipo) VALUES ('7', 'MINI PIG');
INSERT INTO tipomascota (id, tipo) VALUES ('8', 'CABALLO');
INSERT INTO tipomascota (id, tipo) VALUES ('9', 'BOVINO');
INSERT INTO tipomascota (id, tipo) VALUES ('10', 'REPTIL');

----  INSERT TIPOCENTRO

INSERT INTO tipocentro(codigo, nombre) VALUES ('1', 'FARMACEUTICA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('2', 'PELUQUERIA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('3', 'LABORATORIO');
INSERT INTO tipocentro(codigo, nombre) VALUES ('4', 'RECREACION');
INSERT INTO tipocentro(codigo, nombre) VALUES ('5', 'ADIESTRAMIENTO');
INSERT INTO tipocentro(codigo, nombre) VALUES ('6', 'SPA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('7', 'GUARDERIA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('8', 'FUNERARIA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('9', 'HOSPITAL');
INSERT INTO tipocentro(codigo, nombre) VALUES ('10', 'VETERINARIA');

----  INSERT EXAMENES

INSERT INTO examenes (codigo, nombre) VALUES ('1', 'HEPATICO');
INSERT INTO examenes (codigo, nombre) VALUES ('2', 'RADIOGRAFICO');
INSERT INTO examenes (codigo, nombre) VALUES ('3', 'LEUCEMICO');
INSERT INTO examenes (codigo, nombre) VALUES ('4', 'TOXICOLOGICO');
INSERT INTO examenes (codigo, nombre) VALUES ('5', 'DIGESTIVO');
INSERT INTO examenes (codigo, nombre) VALUES ('6', 'ESTERILIZACION');
INSERT INTO examenes (codigo, nombre) VALUES ('7', 'PRUEBA DE TIROIDES');
INSERT INTO examenes (codigo, nombre) VALUES ('8', 'EXAMEN DE LA RETINA');
INSERT INTO examenes (codigo, nombre) VALUES ('9', 'LEPTOSPIRA');
INSERT INTO examenes (codigo, nombre) VALUES ('10', 'FRUCTOSAMINA');

----  INSERT SERVICIO

insert into SERVICIO values('1','Vacunas',2000);
insert into SERVICIO values('2','Desparasitación',1500);
insert into SERVICIO values('3','Consultas',3000);
insert into SERVICIO values('4','Hospitalización',10000);
insert into SERVICIO values('5','Medicamentos',3000);
insert into SERVICIO values('6','Cirugias',40000);
insert into SERVICIO values('7','Funeraria',9000);
insert into SERVICIO values('8','Arreglo de Uñas',10000);
insert into SERVICIO values('9','Corte de Pelo',9000);
insert into SERVICIO values('10','Paseos matutinos',5000);

--- INSERT USUARIO ---
INSERT INTO usuario values ('1094901343', 'Milena Rodriguez', '123456', '3124567892');
INSERT INTO usuario values ('1097902111', 'Juan Diaz', '123456', '7456321');
INSERT INTO usuario values ('1094876543', 'Yeferson Cardona', '123456', '3003452165');
INSERT INTO usuario values ('1094952956', 'Brahiam Tabares', '123456', '3177074523');
INSERT INTO usuario values ('41902453', 'Olga Leal', '123456', '3164297865');
INSERT INTO usuario values ('1094876543', 'Juan Tabares', '123456', '3014356798');
INSERT INTO usuario values ('76453221', 'Lilia Patricia Vallejo', '123456', '3101365475');
INSERT INTO usuario values ('1096764321', 'Over Tabares', '123456', '3112345643');
INSERT INTO usuario values ('1094973555', 'Daniel Castro', '123456', '3152777654');
INSERT INTO usuario values ('1094756098', 'Vanessa Rubio', '123456', '3184563214');


INSERT INTO usuario values ('1094901333', 'Sandra Quintero', '123456', '3124567892');
INSERT INTO usuario values ('1094901331', 'Pablo Diaz', '123456', '3124567892');
INSERT INTO usuario values ('1094901332', 'Carolina Torres', '123456', '3124567892');
INSERT INTO usuario values ('1094901334', 'Duvan Molina', '123456', '3124567892');
INSERT INTO usuario values ('1094901335', 'Margarita Centeno', '123456', '3124567892');
INSERT INTO usuario values ('1094901336', 'Diego Ipiales', '123456', '3124567892');
INSERT INTO usuario values ('1094901337', 'Paula Gutierrez', '123456', '3124567892');
INSERT INTO usuario values ('1094901338', 'Sebastian Londoño', '123456', '3124567892');
INSERT INTO usuario values ('1094901339', 'Alba Marín', '123456', '3124567892');
INSERT INTO usuario values ('1094901340', 'Cristian Benitez', '123456', '3124567892');


INSERT INTO usuario values ('1', 'Patitas', '123456', '7345567');
INSERT INTO usuario values ('2', 'Clínica Pet', '123456', '7345567');
INSERT INTO usuario values ('3', 'Pelos Spa', '123456', '7345567');
INSERT INTO usuario values ('4', 'Laboratorio Huellas', '123456', '7345567');
INSERT INTO usuario values ('5', 'Caninos Adiestrados', '123456', '7345567');
INSERT INTO usuario values ('6', 'Almacen Veterinario', '123456', '7345567');
INSERT INTO usuario values ('7', 'Mascotas Bellas', '123456', '7345567');
INSERT INTO usuario values ('8', 'FarmaPet', '123456', '7345567');
INSERT INTO usuario values ('9', 'AgroPets', '123456', '7345567');
INSERT INTO usuario values ('10', 'Especialistas Pets', '123456', '7345567');
INSERT INTO usuario values ('11', 'Guardería Mis Amores', '123456', '3177304567');

-- INSERT EMPLEADO SAFEPET

insert into EMPLEADOSAFEPET values('1094952956');
insert into EMPLEADOSAFEPET values('1094901343');
insert into EMPLEADOSAFEPET values('1097902111');
insert into EMPLEADOSAFEPET values('1094876543');
insert into EMPLEADOSAFEPET values('41902453');
insert into EMPLEADOSAFEPET values('1094876543');
insert into EMPLEADOSAFEPET values('76453221');
insert into EMPLEADOSAFEPET values('1096764321');
insert into EMPLEADOSAFEPET values('1094973555');
insert into EMPLEADOSAFEPET values('1094756098');

-- INSERT AFILIADO

insert into afiliado values('1094901333');
insert into afiliado values('1094901331');
insert into afiliado values('1094901332');
insert into afiliado values('1094901334');
insert into afiliado values('1094901335');
insert into afiliado values('1094901336');
insert into afiliado values('1094901337');
insert into afiliado values('1094901338');
insert into afiliado values('1094901339');
insert into afiliado values('1094901340');

-- INSERT CENTRO DE SERVICIOS

insert into centroservicio values('1','10');
insert into centroservicio values('2','9');
insert into centroservicio values('3','6');
insert into centroservicio values('4','3');
insert into centroservicio values('5','5');
insert into centroservicio values('6','1');
insert into centroservicio values('7','2');
insert into centroservicio values('8','1');
insert into centroservicio values('9','1');
insert into centroservicio values('10','9');
insert into centroservicio values('11','7');

-- INSERT EVALUACIÓN

