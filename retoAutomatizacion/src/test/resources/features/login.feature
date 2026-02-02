# language: es
Característica: Login en Portal Enlace
  Como usuario del portal de datos de Enlace
  Quiero poder ingresar con mis credenciales
  Para acceder a las funcionalidades del sistema

  Antecedentes:
    Dado que el usuario navega al portal de Enlace

  @test
  Escenario: Login exitoso con credenciales válidas
    Cuando el usuario ingresa con credenciales válidas
      | usuario                   | password             |
      | pruebasqa@enlace.com.co  | Prueba1234567890*    |
    Entonces el usuario debería ver el dashboard del sistema
    Y el login debería ser exitoso

  @test
  Escenario: Login fallido con credenciales inválidas
    Cuando el usuario intenta ingresar con credenciales inválidas
      | usuario                   | password        |
      | pruebasqa@enlace.com.co  | PasswordIncorrecto123 |
    Entonces el usuario debería ver un mensaje de error
    Y el login debería fallar


