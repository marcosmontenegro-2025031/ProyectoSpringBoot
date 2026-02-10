Drop database if exists DBRepuestosAutomotriz_in5cm;
create database DBRepuestosAutomotriz_in5cm;
use DBRepuestosAutomotriz_in5cm;


create table Proveedores(
id_proveedor int auto_increment not null,
 nombre_proveedor varchar(60) not null,
 telefono_proveedor int not null,
 direccion varchar(100) not null,
 email_proveedor varchar(100) not null,
 primary key PK_id_proveedor(id_proveedor)
);

 
create table Empleados(
id_empleado int auto_increment not null,
 nombre_empleado varchar(60) not null,
 apellido_empleado varchar(60) not null,
 puesto_empleado varchar(20) null,
 email_empleado varchar(100) not null,
 primary key PK_id_empleado(id_empleado)
);


create table Repuestos(
id_repuesto int auto_increment not null,
 nombre_repuesto varchar(60) not null,
 categoria_repuesto varchar(60) not null,
 precio_compra double not null,
 precio_venta double not null,
 id_proveedor int not null,
 primary key PK_id_repuesto(id_repuesto),
 constraint FK_repuesto_proveedor foreign key (id_proveedor)
references proveedores(id_proveedor) on delete cascade
);


create table Ventas(
id_venta int auto_increment not null,
 fecha_venta date not null,
 cantidad int not null,
 total double not null,
id_empleado int not null,
 id_repuesto int not null,
 primary key PK_id_venta(id_venta),
 constraint FK_ventas_empleado foreign key (id_empleado)
references Empleados(id_empleado) on delete cascade,
 constraint FK_ventas_repuestos foreign key (id_repuesto)
references Repuestos(id_repuesto) on delete cascade
);

-- REGISTROS

-- INSERTAR PROVEEDORES
insert into Proveedores(nombre_proveedor, telefono_proveedor, direccion, email_proveedor) values
('AutoPartes GT', 22334455, 'Zona 1, Guatemala', 'contacto@autopartesgt.com'),
('Repuestos Centro', 33445566, 'Zona 5, Guatemala', 'ventas@repuestoscentro.com'),
('Motores y Más', 44556677, 'Zona 7, Guatemala', 'info@motoresymas.com'),
('Distribuidora El Motor', 55667788, 'Zona 10, Guatemala', 'distribuidora@elmotor.com'),
('Importadora Automotriz', 66778899, 'Zona 12, Guatemala', 'importadora@auto.com'),
('Repuestos Premium', 77889900, 'Zona 14, Guatemala', 'premium@repuestos.com'),
('AutoSuministros', 88990011, 'Zona 3, Guatemala', 'autosum@correo.com'),
('Mega Repuestos', 99001122, 'Mixco, Guatemala', 'mega@repuestos.com'),
('Todo Motor', 22113344, 'Villa Nueva, Guatemala', 'todomotor@correo.com'),
('Repuestos Express', 33224455, 'Amatitlán, Guatemala', 'express@repuestos.com');

-- INSERTAR EMPLEADOS
insert into Empleados(nombre_empleado, apellido_empleado, puesto_empleado, email_empleado) values
('Carlos', 'Ramirez', 'Vendedor', 'carlos@empresa.com'),
('Ana', 'Lopez', 'Cajera', 'ana@empresa.com'),
('Luis', 'Hernandez', 'Administrador', 'luis@empresa.com'),
('Maria', 'Gomez', 'Vendedor', 'maria@empresa.com'),
('Jorge', 'Castillo', 'Supervisor', 'jorge@empresa.com'),
('Sofia', 'Morales', 'Cajera', 'sofia@empresa.com'),
('Pedro', 'Reyes', 'Vendedor', 'pedro@empresa.com'),
('Laura', 'Mendez', 'Inventario', 'laura@empresa.com'),
('Miguel', 'Torres', 'Supervisor', 'miguel@empresa.com'),
('Elena', 'Vasquez', 'Vendedor', 'elena@empresa.com');

-- INSERTAR REPUESTOS
insert into Repuestos(nombre_repuesto, categoria_repuesto, precio_compra, precio_venta, id_proveedor) values
('Filtro de Aceite', 'Filtros', 30.00, 50.00, 1),
('Bujías', 'Encendido', 15.00, 25.00, 2),
('Pastillas de Freno', 'Frenos', 80.00, 120.00, 3),
('Batería', 'Eléctrico', 350.00, 500.00, 4),
('Radiador', 'Refrigeración', 400.00, 600.00, 5),
('Aceite 10W40', 'Lubricantes', 90.00, 140.00, 6),
('Amortiguadores', 'Suspensión', 250.00, 380.00, 7),
('Correa de Tiempo', 'Motor', 120.00, 180.00, 8),
('Sensor de Oxígeno', 'Sensores', 200.00, 320.00, 9),
('Alternador', 'Eléctrico', 600.00, 850.00, 10);

-- INSERTAR VENTAS
insert into Ventas(fecha_venta, cantidad, total, id_empleado, id_repuesto) values
('2026-01-10', 2, 100.00, 1, 1),
('2026-01-11', 4, 100.00, 2, 2),
('2026-01-12', 1, 120.00, 3, 3),
('2026-01-13', 1, 500.00, 4, 4),
('2026-01-14', 2, 1200.00, 5, 5),
('2026-01-15', 3, 420.00, 6, 6),
('2026-01-16', 2, 760.00, 7, 7),
('2026-01-17', 1, 180.00, 8, 8),
('2026-01-18', 2, 640.00, 9, 9),
('2026-01-19', 1, 850.00, 10, 10);

-- PROCEDIMIENTOS ALMACENADOS

-- EMPLEADOS

delimiter $$
create procedure sp_empleados_create(
	in p_nombre varchar(60),
    in p_apellidon varchar(60),
    in p_puesto varchar(20),
    in p_email varchar(100)
)
begin
	insert into Empleados(nombre_empleado, apellido_empleado, puesto_empleado, email_empleado)
	values (p_nombre, p_apellido, p_puesto, p_email);
end$$
delimiter ;

delimiter $$
create procedure sp_empleados_read_all()
begin
	select * from Empleados;
end$$
delimiter ;

delimiter $$
create procedure sp_empleados_read_by_id(p_id int)
begin
	select * from Empleados where id_empleado = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_empleados_update(
	in p_id int,
    in p_nombre varchar(60),
    in p_apellidon varchar(60),
    in p_puesto varchar(20),
    in p_email varchar(100)
)
begin
	update Empleados
    set nombre_empleado = p_nombre,
    apellido_empleado = p_apellido,
    puesto_empleado = p_puesto,
    email_empleado = p_email
    where id_empleado = p_id;
    
    select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_empleados_delete(p_id int)
begin
	delete from Empleados where id_empleado = p_id;
    
    select row_count() as filas_afectadas;
end$$
delimiter ;

-- PROVEEDORES

delimiter $$
create procedure sp_proveedores_create(
	in p_nombreProveedor varchar(60),
    in p_telefono int,
    in p_direccion varchar(100),
    in p_emaiProveeedor varchar(100)
)
begin
	insert into Proveedores(nombre_proveedor, telefono_proveedor, direccion, email_proveedor)
	values (p_nombreProveedor, p_telefono, p_direccion, p_emaiProveeedor);
end$$
delimiter ;

delimiter $$
create procedure sp_proveedores_read_all()
begin
	select * from Proveedores;
end$$
delimiter ;

delimiter $$
create procedure sp_proveedores_read_by_id(p_id int)
begin
	select * from Proveedores where id_proveedor = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_proveedores_update(
	in p_id int,
	in p_nombreProveedor varchar(60),
    in p_telefono int,
    in p_direccion varchar(100),
    in p_emaiProveeedor varchar(100)
)
begin
	update Proveedor
    set nombre_proveedor = p_nombreProveedor,
    telefono_proveedor = p_telefono,
    direccion = p_direccion,
    email_proveedor = p_emaiProveeedor
    where id_proveedor = p_id;
    
    select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_proveedores_delete(p_id int)
begin
	delete from Proveedores where id_proveedor = p_id;
    
    select row_count() as filas_afectadas;
end$$
delimiter ;

-- REPUESTOS

delimiter $$
create procedure sp_repuestos_create(
	in p_nombreRepuesto varchar(60),
    in p_categoria varchar(60),
    in p_precioCompra double,
    in p_precio_venta double,
    in p_id_proveedor int
)
begin
	insert into Repuestos(nombre_repuesto, categoria_repuesto, precio_compra, precio_venta, id_proveedor)
	values (p_nombreRepuesto, p_categoria, p_precioCompra, p_precio_venta, p_id_proveedor);
end$$
delimiter ;

delimiter $$
create procedure sp_repuestos_read_all()
begin
	select * from Repuestos;
end$$
delimiter ;

delimiter $$
create procedure sp_repuestos_read_by_id(p_id int)
begin
	select * from Repuestos where id_repuesto = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_repuestos_update(
	in p_id int,
    in p_nombreRepuesto varchar(60),
    in p_categoria varchar(60),
    in p_precioCompra  double,
    in p_precio_venta  double,
    in p_id_proveedor int
)
begin
	update Repuestos
    set nombre_repuesto = p_nombreRepuesto,
    categoria_repuesto = p_categoria,
    direccion = p_direccion,
    email_proveedor = p_emaiProveeedor,
    precio_compra = p_precioCompra,
    precio_venta = p_precio_venta,
    id_proveedor = p_id_proveedor
    where id_repuesto = p_id;
    
    select row_count() as filas_afectadas;
end$$
delimiter ;

delimiter $$
create procedure sp_repuestos_delete(p_id int)
begin
	delete from Repuestos where id_repuesto = p_id;
    
    select row_count() as filas_afectadas;
end$$
delimiter ;

-- VENTAS

delimiter $$
create procedure sp_ventas_create(
	in p_fecha date,
    in p_cantidad int,
    in p_total double,
    in p_id_empleado int,
    in p_id_repuesto  int
)
begin
	insert into Ventas(fecha_venta, cantidad, total, id_empleado, id_repuesto)
	values (p_fecha, p_cantidad, p_total , p_id_empleado , p_id_repuesto );
end$$
delimiter ;

delimiter $$
create procedure sp_ventas_read_all()
begin
	select * from Ventas;
end$$
delimiter ;

delimiter $$
create procedure sp_ventas_read_by_id(p_id int)
begin
	select * from Ventas where id_venta = p_id;
end$$
delimiter ;

delimiter $$
create procedure sp_ventas_update(
	in p_id int,
    in p_fecha date,
    in p_cantidad int,
    in p_total double,
    in p_id_empleado int,
    in p_id_repuesto  int
)
begin
	update Repuestos
    set fecha_venta = p_fecha ,
    cantidad = p_cantidad ,
    total = p_total ,
    id_empleado = p_id_empleado ,
    id_repuesto  = p_id_repuesto
    where id_repuesto = p_id;
    
    select row_count() as filas_afectadas;
end$$
delimiter ;


delimiter $$
create procedure sp_ventas_delete(p_id int)
begin
	delete from Ventas where id_venempleadosempleadosta = p_id;
    
    select row_count() as filas_afectadas;
end$$
delimiter ;

-- EMPLEADOS
call sp_empleados_create('Carlos', 'Ramirez', 'Vendedor', 'carlos@empresa.com');
call sp_empleados_create('Ana', 'Lopez', 'Cajera', 'ana@empresa.com');
call sp_empleados_create('Luis', 'Hernandez', 'Administrador', 'luis@empresa.com');
call sp_empleados_create('Maria', 'Gomez', 'Vendedor', 'maria@empresa.com');
call sp_empleados_create('Jorge', 'Castillo', 'Supervisor', 'jorge@empresa.com');
call sp_empleados_create('Sofia', 'Morales', 'Cajera', 'sofia@empresa.com');
call sp_empleados_create('Pedro', 'Reyes', 'Vendedor', 'pedro@empresa.com');
call sp_empleados_create('Laura', 'Mendez', 'Inventario', 'laura@empresa.com');
call sp_empleados_create('Miguel', 'Torres', 'Supervisor', 'miguel@empresa.com');
call sp_empleados_create('Elena', 'Vasquez', 'Vendedor', 'elena@empresa.com');


-- PROVEEDORES 
call sp_proveedores_create('AutoPartes GT', 22334455, 'Zona 1, Guatemala', 'contacto@autopartesgt.com');
call sp_proveedores_create('Repuestos Centro', 33445566, 'Zona 5, Guatemala', 'ventas@repuestoscentro.com');
call sp_proveedores_create('Motores y Más', 44556677, 'Zona 7, Guatemala', 'info@motoresymas.com');
call sp_proveedores_create('Distribuidora El Motor', 55667788, 'Zona 10, Guatemala', 'distribuidora@elmotor.com');
call sp_proveedores_create('Importadora Automotriz', 66778899, 'Zona 12, Guatemala', 'importadora@auto.com');
call sp_proveedores_create('Repuestos Premium', 77889900, 'Zona 14, Guatemala', 'premium@repuestos.com');
call sp_proveedores_create('AutoSuministros', 88990011, 'Zona 3, Guatemala', 'autosum@correo.com');
call sp_proveedores_create('Mega Repuestos', 99001122, 'Mixco, Guatemala', 'mega@repuestos.com');
call sp_proveedores_create('Todo Motor', 22113344, 'Villa Nueva, Guatemala', 'todomotor@correo.com');
call sp_proveedores_create('Repuestos Express', 33224455, 'Amatitlán, Guatemala', 'express@repuestos.com');

-- REPUESTOS
call sp_repuestos_create('Filtro de Aceite', 'Filtros', 30.00, 50.00, 1);
call sp_repuestos_create('Bujías', 'Encendido', 15.00, 25.00, 2);
call sp_repuestos_create('Pastillas de Freno', 'Frenos', 80.00, 120.00, 3);
call sp_repuestos_create('Batería', 'Eléctrico', 350.00, 500.00, 4);
call sp_repuestos_create('Radiador', 'Refrigeración', 400.00, 600.00, 5);
call sp_repuestos_create('Aceite 10W40', 'Lubricantes', 90.00, 140.00, 6);
call sp_repuestos_create('Amortiguadores', 'Suspensión', 250.00, 380.00, 7);
call sp_repuestos_create('Correa de Tiempo', 'Motor', 120.00, 180.00, 8);
call sp_repuestos_create('Sensor de Oxígeno', 'Sensores', 200.00, 320.00, 9);
call sp_repuestos_create('Alternador', 'Eléctrico', 600.00, 850.00, 10);

-- VENTAS
call sp_ventas_create('2026-01-10', 2, 100.00, 1, 1);
call sp_ventas_create('2026-01-11', 4, 100.00, 2, 2);
call sp_ventas_create('2026-01-12', 1, 120.00, 3, 3);
call sp_ventas_create('2026-01-13', 1, 500.00, 4, 4);
call sp_ventas_create('2026-01-14', 2, 1200.00, 5, 5);
call sp_ventas_create('2026-01-15', 3, 420.00, 6, 6);
call sp_ventas_create('2026-01-16', 2, 760.00, 7, 7);
call sp_ventas_create('2026-01-17', 1, 180.00, 8, 8);
call sp_ventas_create('2026-01-18', 2, 640.00, 9, 9);
call sp_ventas_create('2026-01-19', 1, 850.00, 10, 10);

