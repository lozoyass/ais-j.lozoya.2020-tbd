# AIS-Practica-3-2023
# Convocatoria Extraordinaria
Autores: Daniel García Redondo y Jorge Lozoya Astudillo

Enlace al [Repositorio](https://github.com/lozoyass/ais-j.lozoya.2020-tbd.git)

Enlace a la [Aplicación Okteto](https://books-reviewer-tbd-lozoyass.cloud.okteto.net/)

<br>

## Introducción

El enfoque de desarrollo que hemos utilizado en este proyecto ha sido Trunk-based development, el cual, a diferencia de otros métodos, se centra en la integración continua y la entrega rápida de cambios en la rama principal "trunk". 

Además, adoptamos el enfoque "TBD para equipos grandes". En el TBD clásico, las ramas de nuevas features pueden durar bastante tiempo, mientras que con este enfoque, el desarrollo se realiza en ramas de vida corta (2 días como máximo).

Otra característica importante de TBD es que el código de trunk siempre está listo para ser desplegado en producción, mientras que con git flow pueden pasar días o semanas hasta que hay un nuevo commit en master para ser desplegado. Por eso, el uso de pruebas (test) automatizadas desempeña un papel fundamental. 

## Desarrollo con TBD

Clonar el repositorio
```
git clone
```
<br>

Creación de los workflows 1, 2, 3, y 4.
En nuestro caso los metemos directamente sobre trunk ya que los hemos completado y testeado su funcionamiento en otro repositorio de prueba.
```
git commit -a -m "Workflows creados"
git push 
```
<br>

### Desarrollo de una nueva funcionalidad
- Creamos una nueva rama
```
git checkout -b feature/book-description-limit
```
<br>

- Después de completar la nueva funcionalidad, hacemos un commit
```
git commit -a -m "Limite en la descripcion de los libros implementado"
```
<br>

- Subimos la rama al repositorio remoto
```
git push -u origin feature/book-description-limit
```
### Se activa el workflow 1 tras hacer el push de la nueva rama feature:
(descripción del workflow 1 y enlace a la ejecución del mismo)

<br>

Ahora creamos una pull request desde GitHub, de la rama que acabamos de subir (feature/book-description-limit) a la rama trunk
Mergeamos los cambios
### Se activa el workflow 2 tras la pull request que acabamos de crear
(descripción del workflow 2 y enlace a la ejecución del mismo)

<br>

Desde la rama trunk actualizamos 
```
git checkout trunk
git pull
```
<br>

Una vez mergeados los cambios que implementan la nueva funcionalidad, pasamos a crear la rama de la release
```
git checkout -b release/0-2-0
```
### Se activa el workflow 3 tras crear la rama release:
(descripción del workflow 3 y enlace a la ejecución del mismo)

<br>

Por último, 

