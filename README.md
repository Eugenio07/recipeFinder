# recipeFinder
Proyecto para el programa de formación de Architect Coders. Esta app permite al usuario realizar búsquedas de recetas de comida, según los parámetros que indique

Consta de una pantalla principal en la cual se puede realizar busqueda por nombre de receta, por pais, ingreditente principal o categoria. Tambien se puede buscar una receta de manera aleatorea y vizualizar la lista de favoritos.

Para la realización de esta app se utilza la API de recetas https://www.themealdb.com/api.php y la de paises https://restcountries.com/

Si en la busqueda por nombre se introduce algun nombre que no esté incluido en la lista de recetas de la API entonces no se obtendrá ningun resultado.
Recomiendo probar con palabras como "honey", "beef", "apple" que si estan incluidas en la lista.

En la busqueda por pais se tiene un listado de todos los paises que forman parte de la lista de la API. En esa misma pantalla se tiene un botón en la parte superior que es un acceso rapido al pais del usuario.
Si el pais donde esta ubicado el usuario no forma parte de la lista de la API entonces el boton se mostara como "Unknown".

Una vez se selecciona una receta se puede encontrar la vista detallada donde se encontrará un imagen del platillo, los ingrediendientes, las instrucciones de preparacion y un botón de favorito. AL pulsarlo la receta se agregará a la lista de favoritos que se podrá consultar desde la pantalla principal.
