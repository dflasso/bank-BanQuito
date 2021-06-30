INSERT INTO CLIENT (
ID_CLIENTE, FEC_NACIMIENTO_CLIENTE ,CORREO_CLIENTE ,APELLIDO_CLIENTE ,NOMBRE_CLIENTE ,NUM_IDENTIFICATION_CLIENTE ,ESTADO_CIVIL_CLIENTE ) 
values 
(2,'1967-10-09', 'dany@gmail.com' , 'Caiza' ,'Oswaldo', '1708102718' , 'c'),
(3,'1987-09-09', 'stalin@hotmail.com' , 'Llumiquinga' ,'Miriam Mónica', '1708051311' , 's'),
(1, '1977-08-09', 'xavier@hotmail.com' , 'Vaca' ,'Xavier', '1723401715' , 'd'),
(4,'1967-10-09', 'paul@gmail.com' , 'Alcivar' ,'Paul', '1708102718' , 'c'),
(5,'1987-09-09', 'oscar@hotmail.com' , 'Sandoval' ,'Oscar', '1708051311' , 's'),
(6, '1977-08-09', 'juan@hotmail.com' , 'Mendoza' ,'Juan', '1723401715' , 'd');


INSERT INTO CUENTAS_CLIENTE (id_cuenta, num_cuenta, saldo_cuenta, tipo_cuenta, estado_cuenta, id_cliente)
values 
(1, '5557877999', 500.00, 'AHORRO', true, 1),
(2, '5557877998', 15000.00, 'CORRIENTE', true, 1),
(3, '5557877997', 2000.00, 'AHORRO', true, 2),
(4, '5557877996', 900.00, 'CORRIENTE', true, 2),
(5, '5557877995', 3500.88, 'AHORRO', true, 3),
(6, '5557877997', 2000.00, 'AHORRO', true, 4),
(7, '5557877996', 900.00, 'AHORRO', true, 5),
(8, '5557877995', 3500.88, 'AHORRO', true, 6);

INSERT INTO TRANSACCIONES_CUENTA (id_transaccion, tipo_transaccion, valor_transaccion, descripcion_transaccion, fecha_transaccion, id_cuenta) 
values 
(1, 'DEPOSITO', 300000.00, 'DEPOSITO POR VENTANILLA','2021-06-09', 1 ),
(2, 'RETIRO', 800000.00, 'RETIRO POR VENTANILLA','2021-05-09',   1 ),
(3, 'DEPOSITO', 2000000.00, 'DEPOSITO POR VENTANILLA','2021-04-09', 1 ),
(4, 'DEPOSITO', 99000.00, 'DEPOSITO POR VENTANILLA','2021-06-09', 2 ),
(5, 'RETIRO', 50000.00, 'RETIRO POR VENTANILLA','2021-05-09',   2),
(6, 'DEPOSITO', 1000000.00, 'DEPOSITO POR VENTANILLA','2021-04-09', 2),
(7, 'DEPOSITO', 800000.00, 'DEPOSITO POR VENTANILLA','2021-05-09', 3),
(8, 'RETIRO', 200000.00, 'RETIRO POR VENTANILLA','2021-04-09',   4 ),
(9, 'DEPOSITO', 900000.00, 'DEPOSITO POR VENTANILLA','2021-01-09', 5),
(10, 'DEPOSITO', 8000000.00, 'DEPOSITO POR VENTANILLA','2021-05-09', 6),
(11, 'DEPOSITO', 2000000.00, 'RETIRO POR VENTANILLA','2021-04-09',   7 ),
(12, 'DEPOSITO', 9000000.00, 'DEPOSITO POR VENTANILLA','2021-01-09', 8);

INSERT INTO Credito (id_credito, capital_por_cobrar, capital_pagado, capital_prestado, interes_pagado, tasa_interes_anual, cuota_mensual,  cuota_mensual_cancelada, cuota_mensual_pending, estado, fec_inicio, fec_fin, tipo_credito, razon_credito, total_cuotas, id_cliente)
values
(1, 0.00, 5000.00, 5000.00,689.66, 0.16, 366.00, 15, 0, false,'2020-01-09', '2021-01-09', 'AUTO', 'COMPRA DE INSUMOS', 15, 1 ),
(2, 0.00, 5000.00,5000.00, 689.66, 0.16, 366.00, 15, 0, false,'2020-01-09', '2021-01-09', 'AUTO', 'COMPRA DE INSUMOS', 15, 2),
(3, 1830.00, 3170.00, 5000.00, 689.66, 0.16, 366.00, 10, 5, false,'2020-09-09', null , 'AUTO', 'COMPRA DE INSUMOS', 15, 3),
(4, 0.00, 5000.00,5000.00, 689.66, 0.16, 366.00, 15, 0, false,'2020-01-09', '2021-01-09', 'AUTO', 'COMPRA DE INSUMOS', 15, 4),
(5, 1830.00, 3170.00, 5000.00, 689.66, 0.16, 366.00, 10, 5, false,'2020-09-09', null , 'AUTO', 'COMPRA DE INSUMOS', 15, 5),
(6, 0.00, 5000.00,5000.00, 689.66, 0.16, 366.00, 15, 0, false,'2020-01-09', '2021-01-09', 'AUTO', 'COMPRA DE INSUMOS', 15, 6);

INSERT INTO Amortizacion (id_amortizacion, interes_mora, observaciones, fecha_pago, id_credit)
values
(1, 0.00, null, '2020-01-09', 1 ),
(2, 0.00, null, '2020-02-09', 1 ),
(3, 0.00, null, '2020-03-09', 1 ),
(4, 0.00, null, '2020-04-09', 1 ),
(5, 0.00, null, '2020-01-09', 2 ),
(6, 0.00, null, '2020-02-09', 2 ),
(7, 0.00, null, '2020-03-09', 2 ),
(8, 0.00, null, '2020-04-09', 2 ),
(9, 0.00, null, '2020-01-09', 3 ),
(10, 0.00, null, '2020-02-09', 3 ),
(11, 0.00, null, '2020-03-09', 3 ),
(12, 0.00, null, '2020-04-09', 3 );
