README - Decisiones de Implementación

En este proyecto, decidí estructurar el juego de forma modular para facilitar tanto su comprensión como su mantenimiento y futuras mejoras. A continuación, detallo las principales decisiones tomadas:

Estructura del Juego
- Clase Kata:
Es el corazón de la aplicación. Aquí se implementa el bucle principal que controla el flujo del juego, alternando los turnos entre los equipos, cargando las preguntas desde ficheros y verificando la condición de victoria. Se controla que cada equipo reciba preguntas al azar, y se evita la repetición de preguntas ya usadas.
- Clase Topic:
Cada tema o categoría de preguntas se encapsula en la clase Topic. Se carga desde ficheros en el directorio questions, donde cada archivo representa un tema. Cada archivo se procesa leyendo bloques de 6 líneas: la primera es la pregunta, las siguientes cuatro son las opciones de respuesta, y la sexta indica la opción correcta. Esto permite agregar nuevos temas simplemente añadiendo archivos con el formato adecuado.
- Clase Equipo:
La gestión de cada equipo se centraliza en esta clase. Además de llevar un registro de puntos, se incorpora la lógica de los “quesitos”. Cada equipo debe obtener 5 quesitos únicos para ganar el juego. Para garantizar la unicidad de los quesitos se utiliza un HashSet, lo que permite verificar de forma rápida y eficiente si un equipo ya posee un quesito concreto.
- Clase Pregunta:
Aunque su implementación es sencilla, esta clase agrupa la información esencial de cada pregunta: el título, las opciones de respuesta y la respuesta correcta. Esta separación ayuda a mantener la claridad y la responsabilidad única de cada clase.

Lógica de los Quesitos
- Al contestar correctamente, el equipo activo es premiado con un quesito aleatorio, siempre y cuando aún no lo tenga.
- La elección del quesito se realiza de forma aleatoria (números del 1 al 5), y se repite el sorteo hasta obtener un número que aún no esté en el conjunto de quesitos del equipo.
- La condición de victoria se comprueba tras cada respuesta correcta: el juego finaliza en cuanto un equipo obtiene los 5 quesitos.
