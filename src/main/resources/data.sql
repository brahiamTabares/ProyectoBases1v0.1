CREATE TABLE afiliado (
    usuario_id VARCHAR2(20) NOT NULL
);

CREATE INDEX afiliado__idx ON
    afiliado (
        usuario_id
    ASC );

ALTER TABLE afiliado ADD CONSTRAINT afiliado_pk PRIMARY KEY ( usuario_id );

CREATE TABLE centroservicio (
                                usuario_id        VARCHAR2(20) NOT NULL,
                                tipocentro_codigo VARCHAR2(20) NOT NULL
);

ALTER TABLE centroservicio ADD CONSTRAINT veterinaria_pk PRIMARY KEY ( usuario_id );

CREATE TABLE consulta (
                          codigo                    VARCHAR2(20) NOT NULL,
                          fecha_cita                DATE,
                          descripcion               VARCHAR2(300),
                          mascota_id                VARCHAR2(20) NOT NULL,
                          centroservicio_usuario_id VARCHAR2(20) NOT NULL
);

ALTER TABLE consulta ADD CONSTRAINT consulta_pk PRIMARY KEY ( codigo );

CREATE TABLE empleadosafepet (
    usuario_id VARCHAR2(20) NOT NULL
);

ALTER TABLE empleadosafepet ADD CONSTRAINT empleado_pk PRIMARY KEY ( usuario_id );

CREATE TABLE evaluacion (
                            id_evaluacion              VARCHAR2(20) NOT NULL,
                            "puntuacion "              INTEGER,
                            planservicio_servicio_idcs VARCHAR2(30) NOT NULL,
                            planservicio_servicioc_id  INTEGER NOT NULL,
                            planservicio_id            VARCHAR2(20) NOT NULL,
                            afiliado_usuario_id        VARCHAR2(20) NOT NULL
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
                                 centroservicio_id         VARCHAR2(20) NOT NULL,
                                 examenes_codigo           VARCHAR2(20) NOT NULL,
                                 centroservicio_usuario_id VARCHAR2(20) NOT NULL
);

ALTER TABLE examenes_centro ADD CONSTRAINT examenes_centro_pk PRIMARY KEY ( centroservicio_id,
                                                                            examenes_codigo );

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

CREATE TABLE historialservicio (
                                   id                   VARCHAR2(20) NOT NULL,
                                   fecha_servicio       DATE,
                                   servicio_idcs        VARCHAR2(30) NOT NULL,
                                   servicioc_id         INTEGER NOT NULL,
                                   plan_id              VARCHAR2(20) NOT NULL,
                                   serviciocentro_idcen VARCHAR2(5) NOT NULL,
                                   serviciocentro_idser VARCHAR2(20) NOT NULL
);

ALTER TABLE historialservicio
    ADD CONSTRAINT planservicio_pk PRIMARY KEY ( servicio_idcs,
                                                 servicioc_id,
                                                 id );

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
                      id                         VARCHAR2(20) NOT NULL,
                      mensualidad                NUMBER,
                      copago                     NUMBER,
                      afiliado_usuario_id        VARCHAR2(20) NOT NULL,
                      empleadosafepet_usuario_id VARCHAR2(20) NOT NULL
);

ALTER TABLE plan ADD CONSTRAINT plan_pk PRIMARY KEY ( id );

CREATE TABLE planservicio (
                              plan_id     VARCHAR2(20) NOT NULL,
                              servicio_id VARCHAR2(20) NOT NULL
);

ALTER TABLE planservicio ADD CONSTRAINT planservicio_pk PRIMARY KEY ( plan_id,
                                                                      servicio_id );

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
                                idser                     VARCHAR2(20) NOT NULL,
                                idcen                     VARCHAR2(5) NOT NULL,
                                servicio_id               VARCHAR2(20) NOT NULL,
                                centroservicio_usuario_id VARCHAR2(20) NOT NULL
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

CREATE TABLE usuario (
                         id          VARCHAR2(20) NOT NULL,
                         nombre      VARCHAR2(40),
                         contrasenia VARCHAR2(20),
                         telefono    VARCHAR2(20)
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );

ALTER TABLE afiliado
    ADD CONSTRAINT afiliado_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE centroservicio
    ADD CONSTRAINT centroservicio_tipocentro_fk FOREIGN KEY ( tipocentro_codigo )
        REFERENCES tipocentro ( codigo );

ALTER TABLE centroservicio
    ADD CONSTRAINT centroservicio_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE consulta
    ADD CONSTRAINT consulta_centroservicio_fk FOREIGN KEY ( centroservicio_usuario_id )
        REFERENCES centroservicio ( usuario_id );

ALTER TABLE consulta
    ADD CONSTRAINT consulta_mascota_fk FOREIGN KEY ( mascota_id )
        REFERENCES mascota ( id );

ALTER TABLE empleadosafepet
    ADD CONSTRAINT empleadosafepet_usuario_fk FOREIGN KEY ( usuario_id )
        REFERENCES usuario ( id );

ALTER TABLE evaluacion
    ADD CONSTRAINT evaluacion_afiliado_fk FOREIGN KEY ( afiliado_usuario_id )
        REFERENCES afiliado ( usuario_id );

ALTER TABLE evaluacion
    ADD CONSTRAINT evaluacion_planservicio_fk FOREIGN KEY ( planservicio_servicio_idcs,
                                                            planservicio_servicioc_id,
                                                            planservicio_id )
        REFERENCES historialservicio ( servicio_idcs,
                                       servicioc_id,
                                       id );

ALTER TABLE examenes_centro
    ADD CONSTRAINT examenes_centro__fk FOREIGN KEY ( examenes_codigo )
        REFERENCES examenes ( codigo );

ALTER TABLE examenes_centro
    ADD CONSTRAINT examenes_centro_servicio_fk FOREIGN KEY ( centroservicio_usuario_id )
        REFERENCES centroservicio ( usuario_id );

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
    ADD CONSTRAINT plan_afiliado_fk FOREIGN KEY ( afiliado_usuario_id )
        REFERENCES afiliado ( usuario_id );

ALTER TABLE plan
    ADD CONSTRAINT plan_empleadosafepet_fk FOREIGN KEY ( empleadosafepet_usuario_id )
        REFERENCES empleadosafepet ( usuario_id );

ALTER TABLE historialservicio
    ADD CONSTRAINT planservicio_plan_fk FOREIGN KEY ( plan_id )
        REFERENCES plan ( id );

ALTER TABLE planservicio
    ADD CONSTRAINT planservicio_plan_fkv2 FOREIGN KEY ( plan_id )
        REFERENCES plan ( id );

ALTER TABLE planservicio
    ADD CONSTRAINT planservicio_servicio_fk FOREIGN KEY ( servicio_id )
        REFERENCES servicio ( id );

ALTER TABLE historialservicio
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
    ADD CONSTRAINT servicio_centroservicio_fk FOREIGN KEY ( centroservicio_usuario_id )
        REFERENCES centroservicio ( usuario_id );

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

insert into SERVICIO values('1','Vacunas',5000);
insert into SERVICIO values('2','Desparasitación',7500);
insert into SERVICIO values('3','Consultas',10000);
insert into SERVICIO values('4','Hospitalización',10000);
insert into SERVICIO values('5','Medicamentos',3000);
insert into SERVICIO values('6','Cirugias',40000);
insert into SERVICIO values('7','Funeraria',9000);
insert into SERVICIO values('8','Arreglo de Uñas',6000);
insert into SERVICIO values('9','Corte de Pelo',6000);
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

--- PLAN

insert into plan values ('1',22500,5000,'1094901333','1094952956');
insert into plan values ('2',69000,5000,'1094901334','1094901343');
insert into plan values ('3',84500,2000,'1094901334','1094952956');
insert into plan values ('4',19000,10000,'1094901331','1094973555');
insert into plan values ('5',22500,5000,'1094901332','1094901343');
insert into plan values ('6',69000,5000,'1094901336','1094952956');
insert into plan values ('7',845000,2000,'1094901335','1094952956');
insert into plan values ('8',19000,10000,'1094901336','1094756098');
insert into plan values ('9',28125,5000,'1094901333','1094952956');
insert into plan values ('10',30375,5000,'1094901333','1094876543');

-- PLAN SERVICIO

insert into planservicio values ('1','1');
insert into planservicio values ('1','2');
insert into planservicio values ('1','3');
insert into planservicio values ('2','3');
insert into planservicio values ('2','4');
insert into planservicio values ('2','6');
insert into planservicio values ('2','7');
insert into planservicio values ('3','1');
insert into planservicio values ('3','2');
insert into planservicio values ('3','3');
insert into planservicio values ('3','4');
insert into planservicio values ('3','5');
insert into planservicio values ('3','6');
insert into planservicio values ('3','7');
insert into planservicio values ('4','9');
insert into planservicio values ('4','3');
insert into planservicio values ('5','1');
insert into planservicio values ('5','2');
insert into planservicio values ('5','3');
insert into planservicio values ('6','3');
insert into planservicio values ('6','4');
insert into planservicio values ('6','6');
insert into planservicio values ('6','7');
insert into planservicio values ('7','1');
insert into planservicio values ('7','2');
insert into planservicio values ('7','3');
insert into planservicio values ('7','4');
insert into planservicio values ('7','5');
insert into planservicio values ('7','6');
insert into planservicio values ('7','7');
insert into planservicio values ('8','3');
insert into planservicio values ('8','9');
insert into planservicio values ('9','1');
insert into planservicio values ('9','2');
insert into planservicio values ('9','3');
insert into planservicio values ('10','1');
insert into planservicio values ('10','2');
insert into planservicio values ('10','3');

-- INSERT EVALUACIÓN

insert into evaluacion values ('1', 4, '3','2','10','1094901333');
insert into evaluacion values ('2', 3, '7','7','2','1094901334');
insert into evaluacion values ('3', 5, '1','2','10','1094901333');
insert into evaluacion values ('4', 1, '1','1','10','1094901333');
insert into evaluacion values ('5', 3, '3','2','1','1094901333');
insert into evaluacion values ('6', 4, '1','2','1','1094901333');
insert into evaluacion values ('7', 4, '1','1','10','1094901333');
insert into evaluacion values ('8', 5, '7','9','4','1094901331');
insert into evaluacion values ('9', 2, '2','3','4','1094901331');
insert into evaluacion values ('10', 3, '2','3','8','1094756098');


-- INSERT MASCOTA

insert into mascota values ('1','Lulu','2020-01-01','Hembra', '1', '1','2');
insert into mascota values ('2','Tony','2015-10-02','Macho', '2','1','1');
insert into mascota values ('3','Nina','2014-12-02','Hembra', '3','10','4');
insert into mascota values ('4','Iker','2019-08-02','Macho', '4','2','1');
insert into mascota values ('5','Rocket','2018-01-02','Macho', '5','6','1');
insert into mascota values ('6','Noah','2015-10-02','Hembra', '6','7','2');
insert into mascota values ('7','Frida','2015-10-02','Hembra', '7','8','2');
insert into mascota values ('8','Candy','2015-10-02','Hembra', '8','8','2' )
insert into mascota values ('9','Azabache','2015-10-02','Macho', '9','1','3');
insert into mascota values ('9','Teo','2021-05-13','Macho', '9','5','1');
insert into mascota values ('10','Jerry','2015-11-01','Macho', '10','9','2');
insert into mascota values ('10','Junior','2017-10-02','Macho', '10','1','1');
insert into mascota values ('10','Gaspar','2020-08-12','Macho', '10','1','2');

-- INSERT HISTORIA CLINICA

insert into historiaclinica values ('Lulu', 'Hembra', '2021-02-14', '2021-02-15','1');
insert into historiaclinica values ('Lulu', 'Hembra', '2021-03-14', '2021-03-16','1');
insert into historiaclinica values ('Teo', 'Macho', '2021-04-14', '2021-04-15','9');
insert into historiaclinica values ('Lulu', 'Hembra', '2021-07-14', '2021-07-20','1');
insert into historiaclinica values ('Rocket', 'Macho', '2021-05-14', '2021-05-15','5');
insert into historiaclinica values ('Candy', 'Hembra', '2020-08-04', '2021-08-15','8');
insert into historiaclinica values ('Frida', 'Hembra', '2021-01-14', '2021-01-15','7');
insert into historiaclinica values ('Frida', 'Hembra', '2021-02-14', '2021-02-15','7');
insert into historiaclinica values ('Lulu', 'Hembra', '2021-08-20', '2021-08-23','1');
insert into historiaclinica values ('Junior', 'Macho', '2021-05-24', '2021-05-26','1');

--- INSERT EXAMENES_CENTRO

insert into examenes_centro values ('1','4');
insert into examenes_centro values ('1','8');
insert into examenes_centro values ('2','2');
insert into examenes_centro values ('3','4');
insert into examenes_centro values ('6','4');
insert into examenes_centro values ('5','4');
insert into examenes_centro values ('3','8');
insert into examenes_centro values ('3','4');
insert into examenes_centro values ('8','8');
insert into examenes_centro values ('9','2');


-- INSERT REGISTRO
-- codigo, observaciones, concepto, decisiones, fechaRegistro, codigo examen, histoClin masoca id.


-- INSERT CONSULTA
-- codigo, fechacita, descripcion, idMascota, idCentroServicio



-- SERVICIO CENTRO
-- idservicio, idcentro (corregir en datamodeler




-- HISTORIAL SERVICIO
-- corregir datamodeler