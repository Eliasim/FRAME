create database final;
create table Concepto(idConcepto bigint primary key, descripcion varchar(30));


create table Pago(idPago bigint primary key, concepto varchar(30), fecha varchar(10),monto int, estado varchar(30),observacion varchar(50));

create table PacientePagoRef(idPago bigint,idPaciente bigint,idTurno varchar(3));

create table Turno(idTurno bigint primary key,fechaTurno varchar(10), estado varchar(30), observacion varchar(50));


create table TurnoEstado(idEstado bigint primary key, descripcion varchar(50));

create table Pais(idPais bigint primary key,pais varchar(20));

create table MedicoEspecialidad(idMedico bigint primary key, idEspecialidad bigint);

create table Especialidad(idEspecialidad bigint primary key, especialidad varchar(30));

create table Medico(idMedico bigint primary key, nombreMedico varchar(50), apellidosMedico varchar(50));

create table TurnoPaciente(idTurno bigint primary key, idPaciente bigint, idMedico bigint);

create table Paciente(idPaciente bigint primary key, nombre varchar(50), apellidos varchar(50), fNacimiento varchar(10), domicilio varchar(60), idPais bigint, telefono varchar(13), email varchar(50), observacion varchar(50));

create table HistoriaClinica(idHistoria bigint primary key, fechaHistoria varchar(10), observacion varchar(50), idPaciente bigint, idMedico bigint);


create table HistoriaPaciente(idHistoria bigint primary key, idPaciente bigint, idMedico bigint);

