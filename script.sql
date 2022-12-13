CREATE DATABASE javabase DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;

CREATE USER 'pedro'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL ON test.* TO 'pedro'@'localhost';
FLUSH PRIVILEGES;

CREATE TABLE test.proveedores (
    id_proveedor int not null,
    nombre varchar(60),
    fechaDeAlta date,
    id_cliente int,
    
    PRIMARY KEY (id_proveedor)
);

INSERT INTO test.proveedores (id_proveedor, nombre, fechaDeAlta, id_cliente )
VALUES (1,'Coca-cola',STR_TO_DATE('01/11/2022', '%m/%d/%Y'),5);

INSERT INTO test.proveedores (id_proveedor, nombre, fechaDeAlta, id_cliente )
VALUES (2,'Pepsi',STR_TO_DATE('02/12/2022', '%m/%d/%Y'),5);

INSERT INTO test.proveedores (id_proveedor, nombre, fechaDeAlta, id_cliente )
VALUES (3,'Redbull',STR_TO_DATE('07/11/2022', '%m/%d/%Y'),6);

jdbc:mysql://localhost:3702/test