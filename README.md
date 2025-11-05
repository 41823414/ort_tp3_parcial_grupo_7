# 22C Yatay ORT - Grupo 7 - Parcial Android

## Miembros del grupo
-Agustín Montenegro
-Luis Choque
-Alejandro Jursza
-Axel Dumas

Se trabajó con ramas individuales y luego al final del desarrollo, en el último día, se fue mergeando uno por uno y luego arreglos de detalles finales en el último día, siendo el branch merging-ramas, y ahora está todo en el Main.

## Respuestas de preguntas

### ¿Qué tipo de arquitectura usaron y por qué? ¿Podría mejorarla? 

La arquitectura que se usó es Jetpack Compose 3 dividiendo en diferentes pantallas (la carpeta "proyecto/ui/screens") con un montón de componentes reutilizables (proyecto/ui/components) para hacer más fácil el trabajo entre distintas escenas, menos redundancia y más fácil de modificar a uno, y que se aplioque en todas la pantallas, junto al MVVM para la navegación de vistas con un MainActivity. 
Hay muchos aspectos a mejorar debido al trabajo paralelo en poco tiempo, pero podríamos haber unificado más los nombres de componentes, colores a usar en modo claro/oscuro y tema de backend para guardar/cargar datos.

### ¿Tuvieron objetos stateful y stateless? ¿Cómo definen la elección de los mismos?

Tenemos ambos tipos de objetos, según sus usos y modificaciones necesarias según su complejidad de renderizado, la mayoría son componentes stateless que solo reciben parámetros y se muestran como las filas de transacciones, y las barra de progresos. Algunas son stateful que cambian internamente según variables externos como los botones desplegables de Settings/Help y el menú de navegación, que están al tanto de afuera para cambiar. Se diferencia principalmente por si tienen o no interacción del usuario.


### ¿Qué mejoras detectan que podrían realizarle a la app? Comenten al menos 2 cuestiones a refactorizar y tener en cuenta.

1. Mejor organización de los temas claro/oscuros que no está muy bien implementado en toda la app, porque aunque hicimos un trazado de plan general y crear pantallas bases para trabajar en equipo, nos faltó ver más de esos detalles pequeños como tipografías y colores. Refactorizaríamos mejor los colores, y tener los colores separados según cuáles verdes se cambian entre claro/oscuro y cuáles verdes son estáticos independiente del tema, porque hay elementos que se mantienen con mismo color y no necesitan ser dinámicos.

2. Mejor organización de containers, hay muchas cajas contenedores redundantes a lo largo de todas las pantallas con diferentes anchos.

3. El menú de navegación que no llega hasta abajo de todo, al borde inferior de la pantalla.

### ¿Qué manejo de errores harían? ¿dónde los contemplan a nivel código? Mencionen la estrategia de mapeo que más se adecúe.

Unos manejos de errores que podríamos hacer sería capturar si hay conexión a Internet, si existe usuario logueado (quizás no por credenciales expiradas), si hay acceso correcto al servidor, entre otras cosas.

Se podría hacer este chequeo de errores en MainActivity que es por donde pasa todo para chequear cada capa de código necesario a validar y chequear cada vez que se abre la aplicación.

Incluso hay plataformas como Firebase que pueden manejar analíticas y capturas de errores excepcionales en diferentes contextos, o qué más acciones hacen y qué pantalla están mucho tiempo los usuarios. 


### Si la tendríamos que convertir a Español y conservar el Inglés, qué estrategia utilizaría para extenderla. Y si necesitamos agregar otros idiomas?

Android proporciona un archivo strings.xml que ya de por sí nos ayuda a escribir strings de textos en inglés, y luego crear diferentes versiones del mismo en otros idiomas, y cambiarla. Casi todos los textos del proyecto usan strings que vienen de strings.xml, y si los tradujéramos a español, crearíamos una carpeta values-es (dedicado a español), y copiar el mismo archivo strings.xml pero cambiado a español los textos dejando los mismos nombres de strings. Así de esta forma podríamos implementar más idiomas creando más carpetas de value con siglas de idiomas, como "values-fr" (francés) y "values-it" (italiano).