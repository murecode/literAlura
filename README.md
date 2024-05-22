# LiterAlura

Bienvenidos a **LiterAlura**, una aplicación de consola desarrollada como parte del challenge propuesto por el programa **ONE (Oracle Next Education)** y **Alura Latam**. Esta aplicación permite interactuar con la API de Gutendex para explorar y gestionar libros.

## Descripción

**LiterAlura** es una herramienta que permite:
- Listar libros y autores.
- Buscar libros por títulos e idiomas.
- Buscar autores vivos entre un rango de años especificado.
- Solicitar el top 10 de libros más descargados.

La aplicación está desarrollada utilizando **Spring Boot** y se conecta a una base de datos **PostgreSQL** para gestionar y almacenar información de manera eficiente.

## Características

1. **Listar libros y autores:**
    - Muestra una lista de libros disponibles junto con sus autores.

2. **Buscar libros:**
    - Permite buscar libros por título.
    - Permite buscar libros por idioma.

3. **Buscar autores vivos entre años específicos:**
    - Filtra y muestra autores que estaban vivos entre los años A y B.

4. **Top 10 de libros más descargados:**
    - Muestra los 10 libros más descargados según la API de Gutendex.

## Tecnologías Utilizadas

- **Java**: Lenguaje de programación principal.
- **Spring Boot**: Framework para el desarrollo de la aplicación.
- **Spring Data JPA**: Para la gestión de la persistencia y consultas a la base de datos.
- **Jackson**: Para el procesamiento de JSON.
- **PostgreSQL**: Sistema de gestión de bases de datos.

## Instalación y Configuración

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/tu-usuario/literalura.git
   cd literalura
