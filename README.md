# recipeFinder
Proyecto para el programa de formación de Architect Coders. Esta app permite al usuario realizar búsquedas de recetas de comida, según los parámetros que indique

Consta de una pantalla principal en la cual se puede realizar búsqueda por nombre de receta, por país, ingrediente principal o categoría. También se puede buscar una receta de manera aleatoria y visualizar la lista de favoritos.

Para la realización de esta app se utiliza la API de recetas https://www.themealdb.com/api.php y la de paises https://restcountries.com/

Si en la búsqueda por nombre se introduce algún nombre que no esté incluido en la lista de recetas de la API entonces no se obtendrá ningún resultado.
Recomiendo probar con palabras como "honey", "beef", "apple" que si están incluidas en la lista.

En la búsqueda por país se tiene un listado de todos los países que forman parte de la lista de la API. En esa misma pantalla se tiene un botón en la parte superior que es un acceso rápido al país del usuario.
Si el país donde esta ubicado el usuario no forma parte de la lista de la API entonces el botón se mostrará como "Unknown".

Una vez se selecciona una receta se puede encontrar la vista detallada donde se encontrará un imagen del platillo, los ingredientes, las instrucciones de preparación y un botón de favorito. AL pulsarlo la receta se agregará a la lista de favoritos que se podrá consultar desde la pantalla principal.

