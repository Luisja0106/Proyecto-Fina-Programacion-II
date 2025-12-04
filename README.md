# Proyecto Final Programación II

Este proyecto es una aplicación desarrollada en Java utilizando JavaFX. Su objetivo es simular un sistema de gestión de productos y usuarios, incorporando funciones como inicio de sesión, registro, catálogo, carrito de compras, historial y lista de deseos. La estructura del proyecto está organizada siguiendo buenas prácticas de separación entre vistas (FXML), lógica de controladores y modelos de datos.

### Descripción General

La aplicación presenta una interfaz gráfica construida con JavaFX. Cada una de las pantallas se encuentra definida mediante archivos FXML acompañados de hojas de estilo CSS y controladores en Java. El núcleo del proyecto se basa en clases modelo que representan productos, usuarios y estructuras de datos personalizadas necesarias para la gestión interna del sistema.

El flujo principal inicia con la vista de inicio de sesión. Una vez autenticado, el usuario puede navegar entre diferentes módulos como catálogo, perfil, historial y carrito. El sistema mantiene persistencia básica mediante clases utilitarias que administran rutas y carga de recursos.

### Estructura del Proyecto

El directorio principal contiene la carpeta proyectoFinal, donde se encuentran los recursos y clases fuente. Las vistas se localizan en subdirectorios con archivos FXML y sus hojas CSS correspondientes. La lógica se distribuye en paquetes como controllers, model, utils y otros.

El proyecto también incluye una carpeta de imágenes utilizadas dentro de la interfaz de usuario. Cada sección de la aplicación posee controladores dedicados que gestionan eventos, validaciones y la comunicación con las clases modelo.

### Requisitos

Para ejecutar el proyecto se requiere JDK 17 o superior y JavaFX configurado adecuadamente. Es necesario contar con un entorno de desarrollo compatible como NetBeans o IntelliJ IDEA.

### Ejecución

La clase principal se encuentra en el paquete application bajo el nombre App. Esta clase inicializa JavaFX, carga la vista principal y establece los estilos por defecto. Para ejecutar el proyecto desde un IDE, se debe asegurar que la configuración incluya las librerías de JavaFX y los parámetros correspondientes para su carga en tiempo de ejecución.

### Funcionalidades

El sistema contempla registro e inicio de sesión de usuarios, navegación entre vistas mediante controladores, visualización de productos en catálogo, administración del carrito de compras, manejo de listas de deseos, historial de compras y edición de datos de perfil. La interfaz está diseñada para ser intuitiva y organizada mediante componentes modulares.

### Créditos

Proyecto desarrollado como entrega final para la asignatura Programación II. Todos los elementos visuales, controladores y estructuras fueron implementados específicamente para este proyecto académico.
