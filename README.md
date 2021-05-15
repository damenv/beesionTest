# Beesion Test
Prueba de Selenium para hacer login en xoom.com

# Como Utilizar
`mvn clean test`

## Flags
* **seleniumDriver:** Esta bandera indica que explorador utilizar para correr las pruebas. 
  * **Valores aceptados:** _chrome_, _firefox_.
  * **Valor Default:** _chrome_
  * **Ejemplo:**
    `mvn clean test -DseleniumDriver="chrome"`
    
* **operativeSystem:** Esta bandera indica en que sistema operativo se estan corriendo las pruebas.
  * **Valores aceptados:** _macOS_, _windows_.
  * **Valor Default:** _macOS_
  * **Ejemplo:**
    `mvn clean test -DoperativeSystem="macOS"`
