# Proyecto de Gestión de Productos, Ventas y Clientes

Este proyecto es una aplicación que consta de una API para gestionar productos, ventas y clientes, junto con páginas estáticas para consumir dicha API. El desarrollo incluye tanto el backend como el frontend, y se han implementado funcionalidades completas para la administración de datos y la interacción del usuario. Si bien la tarea de backend finaliza al desarrollar los endpoints y la lógica de negocio (entre otras cosas) me pareció interesante utilizar herramientas como html css y js sin librerias para comprender la implementación y la ejecución del programa en su totalidad.

## Página Principal

**https://proyectotc.onrender.com**

## Descripción

La aplicación permite:

- **Gestionar productos**: Crear, actualizar, eliminar y listar productos.
- **Gestionar ventas**: Crear, actualizar, eliminar y listar ventas.
- **Gestionar clientes**: Crear, actualizar, eliminar y listar clientes.

## Tecnologías Utilizadas

- **Backend**: Java, Spring Boot, JPA/Hibernate
- **Frontend**: HTML, CSS, JavaScript
- **Base de Datos**: MYSQL (para pruebas) y PostgreSQL en producción
- **Herramientas**: Maven, Lombok, Postman

## Estructura del Proyecto

### Backend

El backend está desarrollado en Java utilizando Spring Boot. Incluye:

- **Entidades Principales**: `Producto`, `Venta`, `Cliente` son los elementos principales que maneja la aplicación.
- **Relaciones**: 
  - Los productos pueden estar asociados a varias ventas, y viceversa.
  - Cada venta está ligada a un cliente específico, permitiendo gestionar las compras de los clientes.
- **Validaciones**: Se han implementado reglas para asegurar que los datos ingresados sean correctos, como verificar que siempre haya stock disponible. 
- **Manejo de Transacciones**: Se han agregado controles para asegurar que las operaciones con la base de datos se realicen correctamente, evitando problemas en caso de errores. También se implementó la funcionalidad de reducir stock al realizar una venta, verificar y "devolver" stock al editar una venta o eliminarla.
- **Endpoints RESTful**: Se han creado puntos de acceso para que la aplicación pueda agregar, modificar, eliminar y consultar productos, clientes y ventas de manera eficiente.

**Endpoints Backend**

***Clientes***

- Listar: GET https://proyectotc.onrender.com/clientes
- Crear: POST https://proyectotc.onrender.com/clientes/crear
- Obtener por ID: GET https://proyectotc.onrender.com/clientes/id/{id_cliente}
- Eliminar: DELETE https://proyectotc.onrender.com/clientes/eliminar/{id_cliente}
- Editar: PUT https://proyectotc.onrender.com/clientes/editar/{id_cliente}

***Productos***

- Listar: GET https://proyectotc.onrender.com/productos
- Crear: POST https://proyectotc.onrender.com/productos/crear
- Obtener por ID: GET https://proyectotc.onrender.com/productos/id/{codigo_producto}
- Eliminar: DELETE https://proyectotc.onrender.com/productos/eliminar/{codigo_producto}
- Editar: PUT https://proyectotc.onrender.com/productos/editar/{codigo_producto}

***Ventas***

- Listar: GET https://proyectotc.onrender.com/ventas
- Crear: POST https://proyectotc.onrender.com/ventas/crear
- Obtener por ID: GET https://proyectotc.onrender.com/ventas/id/{id_venta}
- Eliminar: DELETE https://proyectotc.onrender.com/ventas/eliminar/{id_venta}
- Editar: PUT https://proyectotc.onrender.com/ventas/editar/{id_venta}


### Frontend

El frontend consta de páginas HTML estáticas que consumen las APIs expuestas por el backend. Incluye:

- **Páginas de Gestión**: 
  - Listar y buscar productos, clientes y ventas.
  - Formularios para agregar, editar y eliminar productos, clientes y ventas.
- **Estilo**: Diseño con colores neutros y un estilo de tres columnas en la página principal.

### Lógica de JavaScript

Se ha implementado la lógica de JavaScript para:

- **Consumir las APIs**: Realizar solicitudes HTTP para interactuar con el backend.
- **Manejo de Eventos**: Controlar interacciones del usuario y actualizar la interfaz en consecuencia.

