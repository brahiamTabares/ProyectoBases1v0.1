drop database safepetb1;
create database safepetb1;
use safepetb1;
CREATE TABLE usuario ( id VARCHAR(20) NOT NULL, nombre VARCHAR(50), contrasenia VARCHAR(30), telefono VARCHAR(30) );
ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id );
CREATE TABLE afiliado ( usuario_id VARCHAR(20) NOT NULL );
CREATE INDEX afiliado__idx ON afiliado ( usuario_id ASC );
ALTER TABLE afiliado ADD CONSTRAINT afiliado_pk PRIMARY KEY ( usuario_id );
CREATE TABLE centroservicio ( usuario_id VARCHAR(20) NOT NULL, tipocentro_codigo VARCHAR(20) NOT NULL);
ALTER TABLE centroservicio ADD CONSTRAINT centroservicio_pk PRIMARY KEY ( usuario_id );
CREATE TABLE consulta (codigo VARCHAR(20) NOT NULL,fecha_cita DATE,descripcion VARCHAR(300),mascota_id VARCHAR(20) NOT NULL,centroservicio_usuario_id VARCHAR(20) NOT NULL);
ALTER TABLE consulta ADD CONSTRAINT consulta_pk PRIMARY KEY ( codigo );
CREATE TABLE empleadosafepet ( usuario_id VARCHAR(20) NOT NULL );
ALTER TABLE empleadosafepet ADD CONSTRAINT empleado_pk PRIMARY KEY ( usuario_id );
CREATE TABLE evaluacion ( id_evaluacion VARCHAR(20) NOT NULL, puntuacion INTEGER, planservicio_servicio_idcs VARCHAR(30) NOT NULL, planservicio_servicioc_id INTEGER NOT NULL, planservicio_id VARCHAR(20) NOT NULL, afiliado_usuario_id VARCHAR(20) NOT NULL );
CREATE UNIQUE INDEX evaluacion__idx ON evaluacion ( id_evaluacion ASC );
ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_pk PRIMARY KEY ( id_evaluacion );
CREATE TABLE examenes (codigo VARCHAR(20) NOT NULL,nombre VARCHAR(20) );
ALTER TABLE examenes ADD CONSTRAINT examenes_pk PRIMARY KEY ( codigo );
CREATE TABLE examenes_centro ( centroservicio_id VARCHAR(20) NOT NULL, examenes_codigo VARCHAR(20) NOT NULL, centroservicio_usuario_id VARCHAR(20) NOT NULL );
ALTER TABLE examenes_centro ADD CONSTRAINT examenes_centro_pk PRIMARY KEY ( centroservicio_id, examenes_codigo );
-- se agrega id a historiaclinica
CREATE TABLE historiaclinica ( id VARCHAR(20) NOT NULL,nombre VARCHAR(20) NOT NULL, sexo VARCHAR(20), fecha_ingreso DATE, fechasalida DATE, mascota_id VARCHAR(20) NOT NULL );
-- CREATE UNIQUE INDEX historiaclinica__idx ON historiaclinica ( mascota_id ASC );
ALTER TABLE historiaclinica ADD CONSTRAINT historiaclinica_pk PRIMARY KEY ( id);
-- PENDIENTE DE CORREGIR ATRIBUTOS DE HISTORIALSERVICIO
CREATE TABLE historialservicio ( id VARCHAR(20) NOT NULL, fecha_servicio DATE, plan_id VARCHAR(20) NOT NULL, serviciocentro_idcen VARCHAR(30) NOT NULL, serviciocentro_idser VARCHAR(20) NOT NULL );
ALTER TABLE historialservicio ADD CONSTRAINT planservicio_pk PRIMARY KEY ( id, serviciocentro_idcen, serviciocentro_idser );
--
CREATE TABLE mascota ( id VARCHAR(20) NOT NULL, nombre VARCHAR(20), fecha_nacimiento DATE, genero VARCHAR(20), plan_id VARCHAR(5) NOT NULL, raza_codigo VARCHAR(20) NOT NULL, tipomascota_id VARCHAR(20) NOT NULL );
ALTER TABLE mascota ADD CONSTRAINT mascota_pk PRIMARY KEY ( id );
CREATE TABLE plan ( id VARCHAR(20) NOT NULL, mensualidad INTEGER , copago INTEGER, afiliado_usuario_id VARCHAR(20) NOT NULL, empleadosafepet_usuario_id VARCHAR(20) NOT NULL );
ALTER TABLE plan ADD CONSTRAINT plan_pk PRIMARY KEY ( id );
CREATE TABLE planservicio ( plan_id VARCHAR(20) NOT NULL, servicio_id VARCHAR(20) NOT NULL );
ALTER TABLE planservicio ADD CONSTRAINT planservicio_pk PRIMARY KEY ( plan_id, servicio_id );
CREATE TABLE raza ( codigo VARCHAR(20) NOT NULL, nombre VARCHAR(20) NOT NULL );
ALTER TABLE raza ADD CONSTRAINT raza_pk PRIMARY KEY ( codigo );
-- Se eliminan los atributos de registro dejan
CREATE TABLE registro (codigo VARCHAR(20) NOT NULL,concepto VARCHAR(20),fecharegistro DATE,examenes_codigo VARCHAR(20) NOT NULL,historiaclinica_mascota_id VARCHAR(20) NOT NULL );
ALTER TABLE registro ADD CONSTRAINT registro_pk PRIMARY KEY ( codigo );
--
CREATE TABLE servicio (id VARCHAR(20) NOT NULL,nombre VARCHAR(50),valor INTEGER );
ALTER TABLE servicio ADD CONSTRAINT servicio_pk PRIMARY KEY ( id );
CREATE TABLE serviciocentro (servicio_id VARCHAR(20) NOT NULL, centroservicio_usuario_id VARCHAR(20) NOT NULL );
ALTER TABLE serviciocentro ADD CONSTRAINT servicio_centro_pk PRIMARY KEY ( servicio_id, centroservicio_usuario_id );
CREATE TABLE tipocentro ( codigo VARCHAR(20) NOT NULL, nombre VARCHAR(20) );
ALTER TABLE tipocentro ADD CONSTRAINT tipocentro_pk PRIMARY KEY ( codigo );
CREATE TABLE tipomascota (id VARCHAR(20) NOT NULL,tipo VARCHAR(20) );
ALTER TABLE tipomascota ADD CONSTRAINT tipomascota_pk PRIMARY KEY ( id );
ALTER TABLE afiliado ADD CONSTRAINT afiliado_usuario_fk FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id );
ALTER TABLE centroservicio ADD CONSTRAINT centroservicio_tipocentro_fk FOREIGN KEY ( tipocentro_codigo ) REFERENCES tipocentro ( codigo );
ALTER TABLE consulta ADD CONSTRAINT consulta_centroservicio_fk FOREIGN KEY ( centroservicio_usuario_id ) REFERENCES centroservicio ( usuario_id );
ALTER TABLE consulta ADD CONSTRAINT consulta_mascota_fk FOREIGN KEY ( mascota_id ) REFERENCES mascota ( id );
ALTER TABLE empleadosafepet ADD CONSTRAINT empleadosafepet_usuario_fk FOREIGN KEY ( usuario_id ) REFERENCES usuario ( id );
ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_afiliado_fk FOREIGN KEY ( afiliado_usuario_id ) REFERENCES afiliado ( usuario_id );
ALTER TABLE evaluacion ADD CONSTRAINT evaluacion_planservicio_fk FOREIGN KEY ( planservicio_servicio_idcs, planservicio_servicioc_id, planservicio_id ) REFERENCES historialservicio ( servicio_idcs, servicioc_id, id );
ALTER TABLE examenes_centro ADD CONSTRAINT examenes_centro__fk FOREIGN KEY ( examenes_codigo ) REFERENCES examenes ( codigo );
ALTER TABLE examenes_centro ADD CONSTRAINT examenes_centro_servicio_fk FOREIGN KEY ( centroservicio_usuario_id ) REFERENCES centroservicio ( usuario_id );
ALTER TABLE historiaclinica ADD CONSTRAINT historiaclinica_mascota_fk FOREIGN KEY ( mascota_id ) REFERENCES mascota ( id );
ALTER TABLE mascota ADD CONSTRAINT mascota_plan_fk FOREIGN KEY ( plan_id ) REFERENCES plan ( id );
ALTER TABLE mascota ADD CONSTRAINT mascota_raza_fk FOREIGN KEY ( raza_codigo ) REFERENCES raza ( codigo );
ALTER TABLE mascota ADD CONSTRAINT mascota_tipomascota_fk FOREIGN KEY ( tipomascota_id ) REFERENCES tipomascota ( id );
ALTER TABLE plan ADD CONSTRAINT plan_afiliado_fk FOREIGN KEY ( afiliado_usuario_id ) REFERENCES afiliado ( usuario_id );
ALTER TABLE plan ADD CONSTRAINT plan_empleadosafepet_fk FOREIGN KEY ( empleadosafepet_usuario_id ) REFERENCES empleadosafepet ( usuario_id );
ALTER TABLE historialservicio ADD CONSTRAINT planservicio_plan_fk FOREIGN KEY ( plan_id ) REFERENCES plan ( id );
ALTER TABLE planservicio ADD CONSTRAINT planservicio_plan_fkv2 FOREIGN KEY ( plan_id ) REFERENCES plan ( id );
ALTER TABLE planservicio ADD CONSTRAINT planservicio_servicio_fk FOREIGN KEY ( servicio_id ) REFERENCES servicio ( id );
ALTER TABLE historialservicio ADD CONSTRAINT planservicio_serviciocentro_fk FOREIGN KEY ( serviciocentro_idcen, serviciocentro_idser ) REFERENCES serviciocentro ( idcen, idser );
ALTER TABLE registro ADD CONSTRAINT registro_examenes_fk FOREIGN KEY ( examenes_codigo ) REFERENCES examenes ( codigo );
ALTER TABLE registro ADD CONSTRAINT registro_historiaclinica_fk FOREIGN KEY ( historiaclinica_mascota_id ) REFERENCES historiaclinica ( mascota_id );
ALTER TABLE serviciocentro ADD CONSTRAINT servicio_centroservicio_fk FOREIGN KEY ( centroservicio_usuario_id ) REFERENCES centroservicio ( usuario_id );
ALTER TABLE serviciocentro ADD CONSTRAINT serviciocentro_servicio_fk FOREIGN KEY ( servicio_id ) REFERENCES servicio ( id );
-- INSERT RAZA
INSERT INTO raza (codigo, nombre) VALUES ('01','CRIOLLO');
INSERT INTO raza (codigo, nombre) VALUES ('02','BULDOG');
INSERT INTO raza (codigo, nombre) VALUES ('03','CHIHUAHUA');
INSERT INTO raza (codigo, nombre) VALUES ('04','LABRADOR');
INSERT INTO raza (codigo, nombre) VALUES ('05','DOBERMAN');
INSERT INTO raza (codigo, nombre) VALUES ('06','ROTTWEILER');
INSERT INTO raza (codigo, nombre) VALUES ('07','PERSA');
INSERT INTO raza (codigo, nombre) VALUES ('08','SIAMES');
INSERT INTO raza (codigo, nombre) VALUES ('09','RAGDOLL');
INSERT INTO raza (codigo, nombre) VALUES ('10','HOLANDES');
-- INSERT TIPOMASCOTA
INSERT INTO tipomascota (id, tipo) VALUES ('01', 'PERRO');
INSERT INTO tipomascota (id, tipo) VALUES ('02', 'GATO');
INSERT INTO tipomascota (id, tipo) VALUES ('03', 'HAMSTER');
INSERT INTO tipomascota (id, tipo) VALUES ('04', 'CONEJO');
INSERT INTO tipomascota (id, tipo) VALUES ('05', 'PAJARO');
INSERT INTO tipomascota (id, tipo) VALUES ('06', 'HURON');
INSERT INTO tipomascota (id, tipo) VALUES ('07', 'MINI PIG');
INSERT INTO tipomascota (id, tipo) VALUES ('08', 'CABALLO');
INSERT INTO tipomascota (id, tipo) VALUES ('09', 'BOVINO');
INSERT INTO tipomascota (id, tipo) VALUES ('10', 'REPTIL');
-- INSERT TIPOCENTRO
INSERT INTO tipocentro(codigo, nombre) VALUES ('01', 'FARMACEUTICA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('02', 'PELUQUERIA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('03', 'LABORATORIO');
INSERT INTO tipocentro(codigo, nombre) VALUES ('04', 'RECREACION');
INSERT INTO tipocentro(codigo, nombre) VALUES ('05', 'ADIESTRAMIENTO');
INSERT INTO tipocentro(codigo, nombre) VALUES ('06', 'SPA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('07', 'GUARDERIA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('08', 'FUNERARIA');
INSERT INTO tipocentro(codigo, nombre) VALUES ('09', 'HOSPITAL');
INSERT INTO tipocentro(codigo, nombre) VALUES ('10', 'VETERINARIA');
-- INSERT EXAMENES
INSERT INTO examenes (codigo, nombre) VALUES ('01', 'HEPATICO');
INSERT INTO examenes (codigo, nombre) VALUES ('02', 'RADIOGRAFICO');
INSERT INTO examenes (codigo, nombre) VALUES ('03', 'LEUCEMICO');
INSERT INTO examenes (codigo, nombre) VALUES ('04', 'TOXICOLOGICO');
INSERT INTO examenes (codigo, nombre) VALUES ('05', 'DIGESTIVO');
INSERT INTO examenes (codigo, nombre) VALUES ('06', 'ESTERILIZACION');
INSERT INTO examenes (codigo, nombre) VALUES ('07', 'PRUEBA DE TIROIDES');
INSERT INTO examenes (codigo, nombre) VALUES ('08', 'EXAMEN DE LA RETINA');
INSERT INTO examenes (codigo, nombre) VALUES ('09', 'LEPTOSPIRA');
INSERT INTO examenes (codigo, nombre) VALUES ('10', 'FRUCTOSAMINA');
-- INSERT SERVICIO
insert into servicio values('01','Vacunas',5000);
insert into servicio values('02','Desparasitación',7500);
insert into servicio values('03','Consultas',10000);
insert into servicio values('04','Hospitalización',10000);
insert into Servicio values('05','Medicamentos',3000);
insert into servicio values('06','Cirugias',40000);
insert into Servicio values('07','Funeraria',9000);
insert into servicio values('08','Arreglo de Uñas',6000);
insert into servicio values('09','Corte de Pelo',6000);
insert into servicio values('10','Paseos matutinos',5000);
-- INSERT USUARIO Empleado
INSERT INTO usuario values ('1094901343', 'Milena Rodriguez', '123456', '3124567892');
INSERT INTO usuario values ('1097902111', 'Juan Diaz', '123456', '7456321');
INSERT INTO usuario values ('1094876543', 'Yeferson Cardona', '123456', '3003452165');
INSERT INTO usuario values ('1094952956', 'Brahiam Tabares', '123456', '3177074523');
INSERT INTO usuario values ('41902453', 'Olga Leal', '123456', '3164297865');
INSERT INTO usuario values ('1094876545', 'Juan Tabares', '123456', '3014356798');
INSERT INTO usuario values ('76453221', 'Lilia Patricia Vallejo', '123456', '3101365475');
INSERT INTO usuario values ('1096764321', 'Over Tabares', '123456', '3112345643');
INSERT INTO usuario values ('1094973555', 'Daniel Castro', '123456', '3152777654');
INSERT INTO usuario values ('1094756098', 'Vanessa Rubio', '123456', '3184563214');
-- Afiliado
INSERT INTO usuario values ('1094901333', 'Sandra Quintero', '123456', '3124567123');
INSERT INTO usuario values ('1094901331', 'Pablo Diaz', '123456', '312456435');
INSERT INTO usuario values ('1094901332', 'Carolina Torres', '123456', '3104789800');
INSERT INTO usuario values ('1094901334', 'Duvan Molina', '123456', '3204567456');
INSERT INTO usuario values ('1094901335', 'Margarita Centeno', '123456', '3004567789');
INSERT INTO usuario values ('1094901336', 'Diego Ipiales', '123456', '319087889966');
INSERT INTO usuario values ('1094901337', 'Paula Gutierrez', '123456', '312345687');
INSERT INTO usuario values ('1094901338', 'Sebastian Londoño', '123456', '310998877665');
INSERT INTO usuario values ('1094901339', 'Alba Marín', '123456', '3124567892');
INSERT INTO usuario values ('1094901340', 'Cristian Benitez', '123456', '3124567892');
-- centros
INSERT INTO usuario values ('01', 'Patitas', '123456', '7345567');
INSERT INTO usuario values ('02', 'Clínica Pet', '123456', '7345567');
INSERT INTO usuario values ('03', 'Pelos Spa', '123456', '7345567');
INSERT INTO usuario values ('04', 'Laboratorio Huellas', '123456', '7345567');
INSERT INTO usuario values ('05', 'Caninos Adiestrados', '123456', '7345567');
INSERT INTO usuario values ('06', 'Almacen Veterinario', '123456', '7345567');
INSERT INTO usuario values ('07', 'Mascotas Bellas', '123456', '7345567');
INSERT INTO usuario values ('08', 'FarmaPet', '123456', '7345567');
INSERT INTO usuario values ('09', 'AgroPets', '123456', '7345567');
INSERT INTO usuario values ('10', 'Especialistas Pets', '123456', '7345567');
INSERT INTO usuario values ('11', 'Guardería Mis Amores', '123456', '3177304567');
-- INSERT EMPLEADO SAFEPET
insert into empleadosafepet values('1094952956');
insert into empleadosafepet values('1094901343');
insert into empleadosafepet values('1097902111');
insert into empleadosafepet values('1094876543');
insert into empleadosafepet values('41902453');
insert into empleadosafepet values('1094876543');
insert into empleadosafepet values('76453221');
insert into empleadosafepet values('1096764321');
insert into empleadosafepet values('1094973555');
insert into empleadosafepet values('1094756098');
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
insert into centroservicio values('01','10');
insert into centroservicio values('02','09');
insert into centroservicio values('03','06');
insert into centroservicio values('04','03');
insert into centroservicio values('05','05');
insert into centroservicio values('06','01');
insert into centroservicio values('07','02');
insert into centroservicio values('08','01');
insert into centroservicio values('09','01');
insert into centroservicio values('10','09');
insert into centroservicio values('11','07');
-- PLAN
insert into plan values ('01',22500,5000,'1094901333','1094952956');
insert into plan values ('02',69000,5000,'1094901334','1094901343');
insert into plan values ('03',84500,2000,'1094901334','1094952956');
insert into plan values ('04',19000,10000,'1094901331','1094973555');
insert into plan values ('05',22500,5000,'1094901332','1094901343');
insert into plan values ('06',69000,5000,'1094901336','1094952956');
insert into plan values ('07',84500,2000,'1094901335','1094952956');
insert into plan values ('08',19000,10000,'1094901336','1094756098');
insert into plan values ('09',28125,5000,'1094901333','1094952956');
insert into plan values ('10',30375,5000,'1094901333','1094876543');
-- PLAN SERVICIO
insert into planservicio values ('01','01');
insert into planservicio values ('01','02');
insert into planservicio values ('01','03');
insert into planservicio values ('02','03');
insert into planservicio values ('02','04');
insert into planservicio values ('02','06');
insert into planservicio values ('02','07');
insert into planservicio values ('03','01');
insert into planservicio values ('03','02');
insert into planservicio values ('03','03');
insert into planservicio values ('03','04');
insert into planservicio values ('03','05');
insert into planservicio values ('03','06');
insert into planservicio values ('03','07');
insert into planservicio values ('04','09');
insert into planservicio values ('04','03');
insert into planservicio values ('05','01');
insert into planservicio values ('05','02');
insert into planservicio values ('05','03');
insert into planservicio values ('06','03');
insert into planservicio values ('06','04');
insert into planservicio values ('06','06');
insert into planservicio values ('06','07');
insert into planservicio values ('07','01');
insert into planservicio values ('07','02');
insert into planservicio values ('07','03');
insert into planservicio values ('07','04');
insert into planservicio values ('07','05');
insert into planservicio values ('07','06');
insert into planservicio values ('07','07');
insert into planservicio values ('08','03');
insert into planservicio values ('08','09');
insert into planservicio values ('09','01');
insert into planservicio values ('09','02');
insert into planservicio values ('09','03');
insert into planservicio values ('10','01');
insert into planservicio values ('10','02');
insert into planservicio values ('10','03');
-- INSERT EVALUACIÓN
insert into evaluacion values ('01', 4, '03','02','10','1094901333');
insert into evaluacion values ('02', 3, '07','07','02','1094901334');
insert into evaluacion values ('03', 5, '01','02','10','1094901333');
insert into evaluacion values ('04', 1, '01','01','10','1094901333');
insert into evaluacion values ('05', 3, '03','02','01','1094901333');
insert into evaluacion values ('06', 4, '01','02','01','1094901333');
insert into evaluacion values ('07', 4, '01','01','10','1094901333');
insert into evaluacion values ('08', 5, '07','09','04','1094901331');
insert into evaluacion values ('09', 2, '02','03','04','1094901331');
insert into evaluacion values ('10', 3, '02','03','08','1094756098');
-- INSERT MASCOTA
insert into mascota values ('01','Lulu','2020-01-01','Hembra', '1', '1','2');
insert into mascota values ('02','Tony','2015-10-02','Macho', '2','1','1');
insert into mascota values ('03','Nina','2014-12-02','Hembra', '3','10','4');
insert into mascota values ('04','Iker','2019-08-02','Macho', '4','2','1');
insert into mascota values ('05','Rocket','2018-01-02','Macho', '5','6','1');
insert into mascota values ('06','Noah','2015-10-02','Hembra', '6','7','2');
insert into mascota values ('07','Frida','2015-10-02','Hembra', '7','8','2');
insert into mascota values ('08','Candy','2015-10-02','Hembra', '8','8','2' )
insert into mascota values ('09','Azabache','2015-10-02','Macho', '9','1','3');
insert into mascota values ('09','Teo','2021-05-13','Macho', '9','5','1');
insert into mascota values ('10','Jerry','2015-11-01','Macho', '10','9','2');
insert into mascota values ('11','Junior','2017-10-02','Macho', '10','1','1');
insert into mascota values ('12','Gaspar','2020-08-12','Macho', '10','1','2');
-- INSERT HISTORIA CLINICA
insert into historiaclinica values ('01', 'Lulu', 'Hembra', '2021-02-14', '2021-02-15','1');
insert into historiaclinica values ('02','Tony', 'Macho', '2021-03-14', '2021-03-16','2');
insert into historiaclinica values ('03','Teo', 'Macho', '2021-04-14', '2021-04-15','9');
insert into historiaclinica values ('04','Nina', 'Hembra', '2021-07-14', '2021-07-20','3');
insert into historiaclinica values ('05','Rocket', 'Macho', '2021-05-14', '2021-05-15','5');
insert into historiaclinica values ('06','Candy', 'Hembra', '2020-08-04', '2021-08-15','8');
insert into historiaclinica values ('07','Iker', 'Macho', '2021-01-14', '2021-01-15','4');
insert into historiaclinica values ('08','Frida', 'Hembra', '2021-02-14', '2021-02-15','7');
insert into historiaclinica values ('09','Noah', 'Hembra', '2021-08-20', '2021-08-23','6');
insert into historiaclinica values ('10','Junior', 'Macho', '2021-05-24', '2021-05-26','10');
-- INSERT EXAMENES_CENTRO
insert into examenes_centro values ('01','04');
insert into examenes_centro values ('01','08');
insert into examenes_centro values ('02','02');
insert into examenes_centro values ('03','04');
insert into examenes_centro values ('06','04');
insert into examenes_centro values ('05','04');
insert into examenes_centro values ('03','08');
insert into examenes_centro values ('03','04');
insert into examenes_centro values ('08','08');
insert into examenes_centro values ('09','02');
-- INSERT REGISTRO
-- codigo, concepto, fechaRegistro, codigo examen, histoClin masoca id.
insert into registro values ('01','Se realiza examen', '2021-02-14', '1','1');
insert into registro values ('02','Se realiza examen', '2021-02-14', '2','1');
insert into registro values ('03','Se realiza examen', '2021-02-14', '3','1');
insert into registro values ('04','Se realiza examen', '2021-08-20', '6','1');
insert into registro values ('05','Se realiza examen', '2021-08-21', '7','1');
insert into registro values ('06','Se realiza examen', '2021-01-14', '1','7');
insert into registro values ('07','Se realiza examen', '2021-01-14', '1','7');
insert into registro values ('08','Se realiza examen', '2021-02-14', '5','1');
insert into registro values ('09','Se realiza examen', '2021-08-04', '2','8');
insert into registro values ('10','Se realiza examen', '2021-02-14', '6','1');
-- INSERT CONSULTA
-- codigo, fechacita, descripcion, idMascota, idCentroServicio
insert into consulta values ('01','2021-02-12', 'Hinchazon estomago', '1', '2');
insert into consulta values ('02','2021-02-14', 'Falta de apetito', '1', '2');
insert into consulta values ('03','2021-02-19', 'Revisión General', '1', '1');
insert into consulta values ('04','2021-03-12', 'Revisión General', '1', '1');
insert into consulta values ('05','2021-02-12', 'Control enfermedad', '1', '10');
insert into consulta values ('06','2021-04-23', 'Revisión General', '1', '1');
insert into consulta values ('07','2021-01-02', 'Malestar General', '1', '2');
insert into consulta values ('08','2021-02-12', 'Hinchazon estomago', '1', '1');
insert into consulta values ('09','2021-02-12', 'Hinchazon Corporal', '1', '1');
insert into consulta values ('10','2020-06-10', 'Falta de apetito', '1', '2');
-- SERVICIO CENTRO
-- idservicio, idcentro (corregir en datamodeler
insert into serviciocentro values ('01','01');
insert into serviciocentro values ('02','01');
insert into serviciocentro values ('03','02');
insert into serviciocentro values ('04','02');
insert into serviciocentro values ('05','08');
insert into serviciocentro values ('06','10');
insert into serviciocentro values ('07','01');
insert into serviciocentro values ('08','07');
insert into serviciocentro values ('09','07');
insert into serviciocentro values ('10','05');
-- HISTORIAL SERVICIO
-- corregir datamodeler id, fechaser, planId, centroId, servId
insert into historialservicio values ('01','2021-02-12','01','01','01');
insert into historialservicio values ('02','2021-02-12','01','02','01');
insert into historialservicio values ('03','2021-02-12','01','04','02');
insert into historialservicio values ('04','2021-03-20','04','01','03');
insert into historialservicio values ('05','2021-01-04','01','02','04');
insert into historialservicio values ('06','2021-06-09','01','01','01');
insert into historialservicio values ('07','2021-04-25','04','01','09');
insert into historialservicio values ('08','2021-02-14','02','08','06');
insert into historialservicio values ('09','2021-01-21','01','01','01');
insert into historialservicio values ('10','2021-08-02','01','10','06');