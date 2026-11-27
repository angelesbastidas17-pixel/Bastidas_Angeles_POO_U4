# Sistema de Contenido Audiovisual (POO – Unidad 4)

Aplicación de consola en **Java** que gestiona distintos tipos de contenido audiovisual (películas, series, documentales, podcasts y videos musicales). Permite cargar los datos desde un archivo **CSV**, mostrarlos, agregar nuevo contenido y guardar el estado actual en otro CSV.

Proyecto desarrollado para la **Tarea #1 de la Unidad 4: Programación Limpia** de la asignatura Programación Orientada a Objetos.

---

## Características

- Lectura de datos desde `contenido.csv` y creación automática de los objetos del modelo.
- Escritura del estado actual del sistema en `contenido_actualizado.csv`.
- Menú de consola para cargar, mostrar, agregar y guardar contenido.
- Jerarquía de clases con herencia y polimorfismo (`convertirACsv()` y `toString()`).
- Arquitectura **MVC** con la vista separada del modelo (el modelo no imprime nada en consola).
- Pruebas unitarias con **JUnit 4**.

## Estructura del proyecto

```
Bastidas_Angeles_POO_U4/
├── contenido.csv                
├── src/
│   ├── Modelo/                 
│   │   ├── ContenidoAudiovisual 
│   │   ├── Pelicula, SerieDeTV, Documental, Podcast, VideoMusical
│   │   └── Actor, Investigador, Temporada
│   ├── archivo/
│   │   └── ConvertibleCSV       
│   ├── controlador/
│   │   ├── ControladorCA       
│   │   ├── ArchivosCSV          
│   │   └── NuevoContenido      
│   └── vista/
│       ├── VistaCA             
│       └── Main                
└── test/test/                   
    ├── ArchivosCSVTest, DocumentalTest, PeliculaTest
    └── PodcastTest, VistaCATest
```

## Diagrama UML
<img width="4504" height="3828" alt="src" src="https://github.com/user-attachments/assets/bb3ac5e2-65c9-459b-aa9a-d570c03ded67" />


## Modelo de clases

`ContenidoAudiovisual` es una clase abstracta que implementa `ConvertibleCSV` y guarda los datos comunes (id, título, duración y género). Cada tipo de contenido la extiende y agrega sus atributos propios:

| Clase | Atributos propios |
|---|---|
| `Pelicula` | estudio, lista de `Actor` |
| `SerieDeTV` | `Temporada` (número de temporada) |
| `Documental` | tema, `Investigador` (puede ser nulo) |
| `Podcast` | anfitrión, número de episodio, lista de `Investigador` |
| `VideoMusical` | nombre del artista, discográfica |

## Formato del archivo CSV

La primera línea es el encabezado y cada fila siguiente es un contenido:

```
TIPO,TITULO,DURACION,GENERO,ATRIBUTO_EXTRA,RELACIONADOS
```

| TIPO | ATRIBUTO_EXTRA | RELACIONADOS |
|---|---|---|
| `PELICULA` | estudio | actores separados por `\|` |
| `SERIE` | número de temporada | (vacío) |
| `DOCUMENTAL` | tema | investigador |
| `PODCAST` | `anfitrión;númeroEpisodio` | investigadores separados por `\|` |
| `VIDEOMUSICAL` | artista | discográfica |

Ejemplo:

```
PELICULA,Avatar,125,Acción,20th Century Studios,Sam Worthington|Zoe Saldaña
PODCAST,The Wild Project #11,213,Science,Jordi Wild;11,Javier Santaolalla
```

> Los campos no deben contener comas, porque la coma es el separador de columnas.

## Requisitos

- **JDK 8** o superior
- **JUnit 4.13** (solo para ejecutar las pruebas)
- NetBeans (opcional)

## Cómo ejecutar

**Desde NetBeans:** abrir el proyecto y ejecutar la clase `vista.Main`.

**Desde la terminal** (en la raíz del proyecto):

```bash
mkdir out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out vista.Main
```

> El programa lee `./contenido.csv`, por lo que debe ejecutarse desde la carpeta raíz del proyecto.

### Menú de la aplicación

```
1. Cargar datos desde el .csv
2. Mostrar datos cargados
3. Agregar contenido (Serie de TV)
4. Guardar estado en un archivo .csv
0. Salir
```

Flujo típico: **1** (cargar) → **2** (revisar) → **3** (agregar una serie) → **4** (guardar en `contenido_actualizado.csv`).

## Pruebas unitarias

Las pruebas están en `test/test/` y cubren:

- `PeliculaTest`, `DocumentalTest`, `PodcastTest`: conversión a CSV con y sin elementos relacionados.
- `ArchivosCSVTest`: lectura de un archivo vacío y de un archivo inexistente (ambos lanzan `IOException`).
- `VistaCATest`: validación de entradas no numéricas en `leerEntero`.

Para ejecutarlas desde la terminal (ajustar la ruta de los `.jar` de JUnit y Hamcrest):

```bash
javac -encoding UTF-8 -cp junit-4.13.2.jar:hamcrest-core-1.3.jar -d out $(find src test -name "*.java")
java -cp out:junit-4.13.2.jar:hamcrest-core-1.3.jar org.junit.runner.JUnitCore \
  test.ArchivosCSVTest test.DocumentalTest test.PeliculaTest test.PodcastTest test.VistaCATest
```

En NetBeans basta con clic derecho sobre el proyecto → **Test**.

## Principios aplicados

- **Código limpio:** nombres descriptivos, métodos cortos con una sola tarea y separación de la entrada/salida de datos.
- **SOLID:** polimorfismo mediante la interfaz `ConvertibleCSV` y la clase abstracta `ContenidoAudiovisual`, de modo que `ArchivosCSV` guarda cualquier contenido sin conocer su tipo concreto.
- **MVC:** el modelo no imprime en consola; la vista (`VistaCA`) maneja la interacción y el controlador (`ControladorCA`) coordina el flujo.
