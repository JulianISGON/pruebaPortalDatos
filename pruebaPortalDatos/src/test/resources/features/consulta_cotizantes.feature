# language: es
Característica: Consulta de Cotizantes RCI
  Como usuario autenticado del portal de Enlace
  Quiero consultar información de cotizantes por documento
  Para exportar los datos en formato CSV

  Antecedentes:
    Dado que el usuario ha iniciado sesión exitosamente
    Y el usuario navega a la opción de Cotizantes RCI

  @test
  Escenario: Consulta exitosa y exportación de documento a CSV
    Cuando el usuario selecciona el tipo de documento "CC"
    Y el usuario ingresa el número de documento "1143382658"
    Y el usuario hace clic en el botón Buscar
    Entonces el usuario debería ver el mensaje de consulta exitosa
    Cuando el usuario hace clic en el botón Exportar a CSV
    Entonces el archivo CSV debería descargarse correctamente
