-- Insertar franquicias
INSERT INTO franquicias (nombre, created_at, updated_at) VALUES
('McDonald''s', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Starbucks', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Subway', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar sucursales para McDonald's (id=1)
INSERT INTO sucursales (nombre, franquicia_id, created_at, updated_at) VALUES
('McDonald''s Centro', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('McDonald''s Norte', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('McDonald''s Sur', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar sucursales para Starbucks (id=2)
INSERT INTO sucursales (nombre, franquicia_id, created_at, updated_at) VALUES
('Starbucks Plaza Mayor', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Starbucks Aeropuerto', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar sucursales para Subway (id=3)
INSERT INTO sucursales (nombre, franquicia_id, created_at, updated_at) VALUES
('Subway Centro Comercial', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Subway Universidad', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar productos para McDonald's Centro (sucursal_id=1)
INSERT INTO productos (nombre, stock, sucursal_id, created_at, updated_at) VALUES
('Big Mac', 150, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('McNuggets 10 pzas', 200, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Papas Fritas Medianas', 180, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('McFlurry Oreo', 95, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar productos para McDonald's Norte (sucursal_id=2)
INSERT INTO productos (nombre, stock, sucursal_id, created_at, updated_at) VALUES
('Big Mac', 120, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Quarter Pounder', 140, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('McNuggets 20 pzas', 175, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Coca-Cola Mediana', 250, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar productos para McDonald's Sur (sucursal_id=3)
INSERT INTO productos (nombre, stock, sucursal_id, created_at, updated_at) VALUES
('Happy Meal', 160, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Big Mac', 110, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sundae Chocolate', 130, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar productos para Starbucks Plaza Mayor (sucursal_id=4)
INSERT INTO productos (nombre, stock, sucursal_id, created_at, updated_at) VALUES
('Caffe Latte Grande', 100, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Frappuccino Caramelo', 85, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Cappuccino Venti', 120, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Croissant', 60, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar productos para Starbucks Aeropuerto (sucursal_id=5)
INSERT INTO productos (nombre, stock, sucursal_id, created_at, updated_at) VALUES
('Espresso Doble', 95, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Caffe Americano', 140, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Muffin Arándanos', 75, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar productos para Subway Centro Comercial (sucursal_id=6)
INSERT INTO productos (nombre, stock, sucursal_id, created_at, updated_at) VALUES
('Sub Italiano 30cm', 110, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sub Pollo Teriyaki 15cm', 145, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sub Vegetariano', 90, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Galletas Chocolate Chip', 160, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Insertar productos para Subway Universidad (sucursal_id=7)
INSERT INTO productos (nombre, stock, sucursal_id, created_at, updated_at) VALUES
('Sub B.M.T. 30cm', 125, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Sub Atún 15cm', 105, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Papas Fritas', 170, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Refresco 500ml', 200, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

