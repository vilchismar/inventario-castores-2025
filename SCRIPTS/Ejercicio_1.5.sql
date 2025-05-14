SELECT DISTINCT p.*
FROM productos p
JOIN ventas v ON p.idProducto = v.idProducto;
