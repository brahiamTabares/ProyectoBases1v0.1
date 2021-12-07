
CREATE TABLE afiliado (
                          id          VARCHAR2(20) NOT NULL,
                          nombre      VARCHAR2(20),
                          contrasenia VARCHAR2(30),
                          telefono    VARCHAR2(30)
);

ALTER TABLE afiliado ADD CONSTRAINT afiliado_pk PRIMARY KEY ( id );

CREATE TABLE centroservicio (
                                id                VARCHAR2(5) NOT NULL,
                                nombre            VARCHAR2(30),
                                contrasenia       VARCHAR2(20),
                                telefono          VARCHAR2(30),
                                tipocentro_codigo VARCHAR2(20) NOT NULL
);

ALTER TABLE centroservicio ADD CONSTRAINT veterinaria_pk PRIMARY KEY ( id );

CREATE TABLE consulta (
                          codigo            VARCHAR2(20) NOT NULL,
                          fecha_cita        DATE,
                          descripcion       VARCHAR2(300),
                          centroservicio_id VARCHAR2(5) NOT NULL,
                          mascota_id        INTEGER NOT NULL
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
                            "puntuacion "              INTEGER
                            planservicio_id            INTEGER NOT NULL,
                            planservicio_servicio_idcs VARCHAR2(30) NOT NULL,
                            planservicio_servicioc_id  INTEGER NOT NULL,
                            afiliado_id                VARCHAR2(20) NOT NULL
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

CREATE TABLE historiaclinica (
                                 nombre        VARCHAR2(20) NOT NULL,
                                 sexo          VARCHAR2(20),
                                 fecha_ingreso DATE,
                                 fechasalida   DATE,
                                 mascota_id    INTEGER NOT NULL
);

CREATE UNIQUE INDEX historiaclinica__idx ON
    historiaclinica (
                     mascota_id
                     ASC );

ALTER TABLE historiaclinica ADD CONSTRAINT historiaclinica_pk PRIMARY KEY ( mascota_id );

CREATE TABLE mascota (
                         id               INTEGER NOT NULL,
                         nombre           VARCHAR2(20),
                         fecha_nacimiento DATE,
                         genero           VARCHAR2(20),
                         plan_id          INTEGER NOT NULL,
                         tipomascota_id   INTEGER NOT NULL,
                         raza_codigo      VARCHAR2(20) NOT NULL
);

ALTER TABLE mascota ADD CONSTRAINT mascota_pk PRIMARY KEY ( id );

CREATE TABLE plan (
                      id                 INTEGER NOT NULL,
                      mensualidad        NUMBER,
                      copago             NUMBER,
                      afiliado_id        VARCHAR2(20) NOT NULL,
                      empleadosafepet_id VARCHAR2(20) NOT NULL
);

ALTER TABLE plan ADD CONSTRAINT plan_pk PRIMARY KEY ( id );

CREATE TABLE planservicio (
                              id              INTEGER NOT NULL,
                              fecha_servicio  DATE,
                              servicio_idcs   VARCHAR2(30) NOT NULL,
                              servicioc_id    INTEGER NOT NULL,
                              servicioc_idser INTEGER NOT NULL,
                              servicioc_idcen VARCHAR2(5) NOT NULL
);

ALTER TABLE planservicio
    ADD CONSTRAINT planservicio_pk PRIMARY KEY ( id,
                                                 servicio_idcs,
                                                 servicioc_id );

CREATE TABLE raza (
                      codigo VARCHAR2(20) NOT NULL,
                      nombre VARCHAR2(20) NOT NULL
);

ALTER TABLE raza ADD CONSTRAINT raza_pk PRIMARY KEY ( codigo );

CREATE TABLE registro (
                          codigo                     VARCHAR2(20) NOT NULL,
                          "observaciones "           VARCHAR2(20),
                          concepto                   VARCHAR2(20),
                          decisiones                 VARCHAR2(20),
                          fecharegistro              DATE,
                          examenes_codigo            VARCHAR2(20) NOT NULL,
                          historiaclinica_mascota_id INTEGER NOT NULL
);

ALTER TABLE registro ADD CONSTRAINT registro_pk PRIMARY KEY ( codigo );

CREATE TABLE servicio (
                          id     INTEGER NOT NULL,
                          nombre VARCHAR2(50),
                          valor  INTEGER
);

ALTER TABLE servicio ADD CONSTRAINT servicio_pk PRIMARY KEY ( id );

CREATE TABLE serviciocentro (
                                idser INTEGER NOT NULL,
                                idcen VARCHAR2(5) NOT NULL
);

ALTER TABLE serviciocentro ADD CONSTRAINT servicio_centro_pk PRIMARY KEY ( idser,
                                                                           idcen );

CREATE TABLE tipocentro (
                            codigo VARCHAR2(20) NOT NULL,
                            nombre VARCHAR2(20)
);

ALTER TABLE tipocentro ADD CONSTRAINT tipocentro_pk PRIMARY KEY ( codigo );

CREATE TABLE tipomascota (
                             id   INTEGER NOT NULL,
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
    ADD CONSTRAINT evaluacion_planservicio_fk FOREIGN KEY ( planservicio_id,
                                                            planservicio_servicio_idcs,
                                                            planservicio_servicioc_id )
        REFERENCES planservicio ( id,
                                  servicio_idcs,
                                  servicioc_id );

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
    ADD CONSTRAINT planservicio_plan_fk FOREIGN KEY ( id )
        REFERENCES plan ( id );

ALTER TABLE planservicio
    ADD CONSTRAINT planservicio_serviciocentro_fk FOREIGN KEY ( servicioc_idser,
                                                                servicioc_idcen )
        REFERENCES serviciocentro ( idser,
                                    idcen );

ALTER TABLE registro
    ADD CONSTRAINT registro_examenes_fk FOREIGN KEY ( examenes_codigo )
        REFERENCES examenes ( codigo );

ALTER TABLE registro
    ADD CONSTRAINT registro_historiaclinica_fk FOREIGN KEY ( historiaclinica_mascota_id )
        REFERENCES historiaclinica ( mascota_id );

ALTER TABLE serviciocentro
    ADD CONSTRAINT servicio_centro_servicio_fk FOREIGN KEY ( idser )
        REFERENCES servicio ( id );

ALTER TABLE serviciocentro
    ADD CONSTRAINT servicio_centroservicio_fk FOREIGN KEY ( idcen )
        REFERENCES centroservicio ( id );










