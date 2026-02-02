# 🚀 Automatización Web - Portal Enlace

[![Serenity BDD](https://img.shields.io/badge/Serenity%20BDD-4.0.1-00C853?style=flat-square&logo=gradle)](https://serenity-bdd.info/)
[![Selenium](https://img.shields.io/badge/Selenium-4.25.0-43B02A?style=flat-square&logo=selenium)](https://www.selenium.dev/)
[![Gradle](https://img.shields.io/badge/Gradle-8.8-02303A?style=flat-square&logo=gradle)](https://gradle.org/)
[![Java](https://img.shields.io/badge/Java-17-007396?style=flat-square&logo=openjdk)](https://openjdk.org/)

Proyecto de automatización de pruebas end-to-end para el Portal de Datos Enlace utilizando el patrón **Screenplay** de Serenity BDD, Selenium WebDriver y Cucumber BDD.

---

## 📋 Tabla de Contenidos

- [Características](#-características)
- [Tecnologías](#️-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Estructura del Proyecto](#-estructura-del-proyecto)
- [Configuración](#️-configuración)
- [Ejecución de Pruebas](#-ejecución-de-pruebas)
- [Reportes](#-reportes)
- [Escenarios Implementados](#-escenarios-implementados)
- [API Endpoints](#-api-endpoints)
- [Buenas Prácticas](#-buenas-prácticas)
- [Troubleshooting](#-troubleshooting)

---

## ✨ Características

- ✅ **Patrón Screenplay**: Diseño orientado a tareas y actores
- ✅ **BDD con Cucumber**: Escenarios escritos en Gherkin en español
- ✅ **Reportes HTML**: Generación automática de reportes detallados con Serenity
- ✅ **Logs de Auditoría**: Trazabilidad completa de cada acción ejecutada
- ✅ **Manejo de Descargas**: Verificación automática de archivos CSV descargados
- ✅ **Esperas Inteligentes**: WaitUntil con timeouts configurables
- ✅ **Cross-Browser**: Configuración preparada para múltiples navegadores
- ✅ **Screenshots**: Capturas automáticas en caso de fallo

---

## 🛠️ Tecnologías

| Tecnología | Versión | Propósito |
|------------|---------|-----------|
| **Java** | 17 | Lenguaje base del proyecto |
| **Gradle** | 8.8 | Gestor de dependencias y construcción |
| **Serenity BDD** | 4.0.1 | Framework de pruebas y reportes |
| **Selenium WebDriver** | 4.25.0 | Automatización del navegador |
| **Cucumber** | 7.14.0 | Definición de escenarios BDD |
| **Logback** | 1.4.11 | Sistema de logging |
| **AssertJ** | 3.24.2 | Assertions fluidas |
| **JUnit** | 4.13.2 | Runner de pruebas |

---

## 📦 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java Development Kit (JDK) 17** o superior
  ```bash
  java -version
  ```
  
- **Gradle 8.8+** (opcional, el proyecto incluye Gradle Wrapper)
  ```bash
  gradle -version
  ```

- **Google Chrome** actualizado (versión 144+)

- **Git** para clonar el repositorio
  ```bash
  git --version
  ```

---

## 🚀 Instalación

### 1️⃣ Clonar el repositorio

```bash
git clone https://github.com/tu-usuario/pruebaPortalDatos.git
cd pruebaPortalDatos
```

### 2️⃣ Verificar configuración de Java

```bash
# Windows PowerShell
$env:JAVA_HOME
java -version

# Linux/Mac
echo $JAVA_HOME
java -version
```

### 3️⃣ Instalar dependencias

```bash
# Windows
.\gradlew.bat clean build -x test

# Linux/Mac
./gradlew clean build -x test
```

---

## 📁 Estructura del Proyecto

```
pruebaPortalDatos/
├── src/
│   └── test/
│       ├── java/
│       │   └── enlace/
│       │       └── screenplay/
│       │           ├── config/          # Configuraciones (URLs, credenciales)
│       │           ├── questions/       # Preguntas (verificaciones)
│       │           ├── stepdefinitions/ # Definiciones de pasos Cucumber
│       │           ├── tasks/           # Tareas de alto nivel
│       │           ├── ui/              # Localizadores de elementos
│       │           ├── utils/           # Utilidades y helpers
│       │           └── CucumberTestSuite.java  # Runner principal
│       └── resources/
│           ├── features/                # Archivos .feature (Gherkin)
│           ├── logback-test.xml         # Configuración de logs
│           ├── serenity.conf            # Configuración de Serenity
│           └── serenity.properties      # Propiedades adicionales
├── downloadsCSV/                        # Directorio para descargas temporales
├── build.gradle                         # Configuración de Gradle
├── gradle.properties                    # Propiedades de Gradle
└── README.md                            # Este archivo
```

---

## ⚙️ Configuración

### 🔐 Credenciales

Las credenciales se encuentran en `serenity.conf`:

```hocon
serenity {
  enlace {
    url = "https://pruebasportaldatos.enlace.com.co/login"
    usuario = "pruebasqa@enlace.com.co"
    password = "Prueba1234567890*"
  }
}
```

### 📥 Directorio de Descargas

Los archivos CSV se descargan en:
```
C:\Users\julia\OneDrive\Desktop\julian\Repositorios\pruebaPortalDatos\downloadsCSV
```

Este directorio se crea automáticamente. Los archivos descargados se eliminan después de validarse.

### 🌐 Configuración del Navegador

Ubicado en `serenity.conf`:

```hocon
webdriver {
  driver = chrome
  
  capabilities {
    "goog:chromeOptions" {
      args = [
        "--start-maximized",
        "--disable-notifications",
        "--no-sandbox",
        "--disable-dev-shm-usage"
      ]
      
      prefs {
        "download.default_directory" = "ruta/a/downloadsCSV"
        "download.prompt_for_download" = false
      }
    }
  }
}
```

---

## 🧪 Ejecución de Pruebas

### Ejecutar TODOS los tests

```bash
# Windows
.\gradlew.bat test

# Linux/Mac
./gradlew test
```

### Ejecutar tests con un tag específico

```bash
# Windows
.\gradlew.bat test -Dcucumber.filter.tags="@test"

# Linux/Mac
./gradlew test -Dcucumber.filter.tags="@test"
```

### Ejecutar un test específico

```bash
# Windows
.\gradlew.bat test --tests "enlace.screenplay.CucumberTestSuite"

# Linux/Mac
./gradlew test --tests "enlace.screenplay.CucumberTestSuite"
```

### Limpiar y ejecutar

```bash
# Windows
.\gradlew.bat clean test

# Linux/Mac
./gradlew clean test
```

---

## 📊 Reportes

### Generar Reportes Serenity

Los reportes se generan automáticamente después de ejecutar los tests:

```bash
.\gradlew.bat test aggregate
```

### Ver Reportes

El reporte principal se encuentra en:
```
build/site/serenity/index.html
```

Ábrelo en tu navegador:
```bash
# Windows
start build/site/serenity/index.html

# Linux
xdg-open build/site/serenity/index.html

# Mac
open build/site/serenity/index.html
```

### Características del Reporte

- ✅ Resumen ejecutivo de pruebas
- ✅ Desglose por feature y escenario
- ✅ Screenshots de cada paso
- ✅ Tiempos de ejecución
- ✅ Logs detallados
- ✅ Gráficos de cobertura

---

## 🎯 Escenarios Implementados

### 1️⃣ **Login al Portal**

**Feature**: `login.feature`

```gherkin
@test
Escenario: Login exitoso con credenciales válidas
  Dado que el usuario navega al portal de Enlace
  Cuando el usuario ingresa con credenciales válidas
  Entonces el usuario debería ver el dashboard del sistema
  Y el login debería ser exitoso
```

```gherkin
@test
Escenario: Login fallido con credenciales inválidas
  Dado que el usuario navega al portal de Enlace
  Cuando el usuario intenta ingresar con credenciales inválidas
  Entonces el usuario debería ver un mensaje de error
  Y el login debería fallar
```

### 2️⃣ **Consulta de Cotizantes RCI**

**Feature**: `consulta_cotizantes.feature`

```gherkin
@test
Escenario: Consulta exitosa y exportación de documento a CSV
  Dado que el usuario ha iniciado sesión exitosamente
  Y el usuario navega a la opción de Cotizantes RCI
  Cuando el usuario selecciona el tipo de documento "CC"
  Y el usuario ingresa el número de documento "1143382658"
  Y el usuario hace clic en el botón Buscar
  Entonces el usuario debería ver el mensaje de consulta exitosa
  Cuando el usuario hace clic en el botón Exportar a CSV
  Entonces el archivo CSV debería descargarse correctamente
```

---

---

#### 3. **Exportar a CSV**
```
POST https://pruebasportaldatos.enlace.com.co/api/cotizantes/exportar
Authorization: Bearer {{token}}
Content-Type: application/json

{
  "tipoDocumento": "CC",
  "numeroDocumento": "1143382658",
  "formato": "csv"
}
```

**Respuesta**: Descarga directa del archivo CSV

---

## ✅ Buenas Prácticas

### Patrón Screenplay

```java
// ✅ CORRECTO - Separación clara de responsabilidades
actor.attemptsTo(
    NavigateToPortal.atURL(),
    DoLogin.withCredentials(user, pass)
);
actor.should(seeThat(TheDashboard.isVisible(), is(true)));

// ❌ INCORRECTO - Lógica mezclada
WebElement button = driver.findElement(By.id("login"));
button.click();
```

---

## 👤 Autor

**Julian Isaza QA**

---
