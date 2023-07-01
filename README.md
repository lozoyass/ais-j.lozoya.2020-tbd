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

### Clonamos el repositorio 
En nuestro caso, se nos proporciona una url a un repositorio plantilla desde el cual crearemos nuestro repositorio. 

### Modificamos el nombre de la rama principal
Como vamos a trabajar aplicando TBD, es fundamental que el nombre de la rama principal se llame "trunk" y no "master" como se llama actualmente. Vamos a realizar esa modificación. 

- Nos ubicamos en el repositorio.
```
$ cd  ais-d.garciar.2020-j.lozoya.2020-2023-tbd
```
<br>

- Vemos qué ramas hay en el repositorio. Este comando muestra tanto las locales como las remotas.
```
$ git branch -a
* master
  remotes/origin/HEAD -> origin/master
  remotes/origin/master
```
<br>

- Nos aseguramosque estamos situados en la rama que queremos cambiar (rama principal). 
```
$ git checkout master
Already on 'master'
Your branch is up to date with 'origin/master'.
```
<br>

- Le cambiamos el nombre.
```
$ git branch -m trunk
```
<br>

- Verificamos que se haya realizado el cambio. 
```
$ git branch -a
* trunk
  remotes/origin/HEAD -> origin/master
  remotes/origin/master
```
<br>

Podemos ver que efectivamente le hemos cambiado el nombre, pero solo en local. Al intentar hacer push de esta rama, configurarla como la principal e intentar borrar la rama master, nos encontramos con que no tenemos permisos. Por lo tanto, en lugar de hacer push de la rama trunk, hemos decidido que cambiar el nombre de la rama master en local a trunk es requisito suficiente para empezar con el desarrollo TBD. 

- Por último, configuramos la rama trunk como principal en lugar de master con este comando.
```
$ git symbolic-ref refs/remotes/origin/HEAD refs/remotes/origin/trunk
```
<br>

### Creación de los workflows
En nuestro caso los metemos directamente sobre trunk ya que esta rama siempre se considera lista para producción.
```
git commit -a -m "Workflows creados"
git push 
```
<br>

Una vez añadidos los workflows al repositorio, podemos comenzar a desarrollar la nueva funcionalidad. 

### Desarrollo de una nueva funcionalidad
- Creamos una nueva rama
```
$ git checkout -b feature/book-description-limit
Switched to a new branch 'feature/book-description-limit'
```
<br>

- Publicamos la rama en el repositorio remoto. Lo hacemos lo antes posible ya que así otros miembros del equipo pueden verla y colaborar en ella. 
```
$ git push origin feature/book-description-limit
Total 0 (delta 0), reused 0 (delta 0), pack-reused 0
remote:
remote: Create a pull request for 'feature/book-description-limit' on GitHub by visiting:
remote:      https://github.com/lozoyass/ais-d.garciar.2020-j.lozoya.2020-2023-tbd/pull/new/feature/book-description-limit
remote:
To https://github.com/lozoyass/ais-d.garciar.2020-j.lozoya.2020-2023-tbd.git
 * [new branch]      feature/book-description-limit -> feature/book-description-limit
```
<br>

- Después de completar la nueva funcionalidad, hacemos un commit
```
git commit -a -m "Limite en la descripcion de los libros implementado"
```
<br>

#### Se activa el workflow 1 tras hacer el push de la nueva rama feature:
(descripción del workflow 1 y enlace a la ejecución del mismo)
[Wokflow1](enlace)
<br>

Este workflow se encarga de asegurar que todos los cambios realizados en la rama feature funcionen con la build actual. Como workflow no lanza ningún error, esto significa que todo está correcto y que podemos integrar con la rama de producción.

Ahora creamos una pull request desde GitHub, de la rama que acabamos de subir (feature/book-description-limit) a la rama trunk
Mergeamos los cambios
````
comando
````
<br>

#### Se activa el workflow 2 tras la pull request que acabamos de crear
(descripción del workflow 2 y enlace a la ejecución del mismo)
[Wokflow2](enlace)
<br>

Este workflow se ejecuta cuando integramos con la rama de producción (trunk). A diferencia del anterior, este ejecuta todas las pruebas a excepción del sanity test. 

Desde la rama trunk actualizamos. 
```
$ git checkout trunk
$ git pull
```
<br>

Una vez mergeados los cambios que implementan la nueva funcionalidad, pasamos a crear la rama de la release
```
git checkout -b release/0-2-0
```
### Se activa el workflow 3 tras crear la rama release:
(descripción del workflow 3 y enlace a la ejecución del mismo)
[Wokflow3](enlace)
<br>

Este workflow está configurado de tal manera que se ejecutará cuando se cree una rama release. Tiene varios pasos: 
- Ejecuta todas las pruebas 
- Publica una versión de la aplicación como imagen Docker en DockerHub. Su versión es el hash del commit. 
Enlace a la imagen: []()
- Desplegará dicha versión en Okteto. El nombre de la aplicación es **books-reviewer**. 
Captura de pantalla del navegador: 
![Aplicación books-reviewer](images/books-reviewer.jpeg)

<br>

Por último, 

