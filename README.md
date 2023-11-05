# app_moviles_MISO
Repositorio para la materia de Ingeniería de software para aplicaciones móviles MISO

Integrante | Correo Electrónico
-- | --
Daniel Jimenez | d.jimenez112@uniandes.edu.co
Jesús Henríquez | Ja.henriquez@uniandes.edu.co
Carlos Castillo | c.castillof@uniandes.edu.co
Santiago Begambre | s.begambre@uniandes.edu.co

## Descripción
Vinilos es una aplicación para dispositivos Android, diseñada con el propósito de administrar información relacionada con álbumes, coleccionistas, canciones y artistas. En la implementación de esta aplicación, se hace uso de una API REST preexistente proporcionada por el equipo de TSDC. Esta API ha sido desplegada en una instancia de Google Cloud Platform (GCP). La aplicación ha sido programada en el lenguaje Kotlin

Para más información consulte la [wiki](https://github.com/carloscastillof/app_moviles_MISO/wiki) del proyecto.

## Instrucciones para desplegar la Aplicación

**Requisitos previos:**

- Android Studio Giraffe (RECOMENDADO) 
- Kotlin Plugin para Android Studio (si no está incluido en Android Studio)
- JDK (Java Development Kit) 8 o posterior
- Un dispositivo Android o un emulador de Android configurado en Android Studio

**Clonar el repositorio:**

```git clone https://github.com/carloscastillof/app_moviles_MISO.git>```

**Abrir el proyecto en Android Studio:**

Abre Android Studio y selecciona "File" (Archivo) > "Open" (Abrir) y selecciona la carpeta raíz de tu repositorio clonado.

**Configurar el emulador o dispositivo:**
Configura un emulador de Android o conecta un dispositivo Android a tu computadora si aún no lo has hecho. Te dejamos un par de instrucciones para poder realizar estos steps:
- [Cómo ejecutar apps en Android Emulator](https://developer.android.com/studio/run/emulator?hl=es-419)
- [Cómo ejecutar apps en un dispositivo de hardware](https://developer.android.com/studio/run/device?hl=es-419)

## IMPORTANTE
Si Android Studio ya está instalado en tu sistema, asegúrate de sincronizar el archivo build.gradle para evitar posibles conflictos de versiones con las dependencias del proyecto en tu entorno local.

## Backend de la Aplicación - Consideraciones
Las APIs utilizadas dentro de este desarrollo se pueden encontrar en el siguiente repositorio https://github.com/jesushenriquez/BackVynils, es importante mencionar que para efectos practicos estas APIs estan alojadas en GCP.

## Ejecutar pruebas de Espresso

En la carpeta com.example.vynilos (androidTest) ubique los archivos PA###, que son los mismos escenarios de los de nuestro inventario con los ID's PM###.

<img width="380" alt="image" src="https://github.com/carloscastillof/app_moviles_MISO/assets/124113572/c2166b1a-8506-418f-b735-2ffa433d6467">

Seleccione el que desea ejecutar, oprima click derecho y oprime la opción "Run 'PA###'"

<img width="242" alt="image" src="https://github.com/carloscastillof/app_moviles_MISO/assets/124113572/cdc79dba-c23b-4956-a765-6b39ff3c6cfb">

Cuando se ejecute la prueba verá un resultado como este que indica que la prueba fue satisfactoria:

![image](https://github.com/carloscastillof/app_moviles_MISO/assets/124113572/f5d8c11c-219a-4a10-a5eb-cfb13e85d901)


## Archivo APK

Para validar la aplicación en construcción correspondiente al primer sprint en un dispositivo, puedes descargar el archivo APK a través del siguiente enlace [APK](https://uniandes-my.sharepoint.com/:u:/g/personal/ja_henriquez_uniandes_edu_co/Ed7zteu6gTtNgcUGeZcDk1EB7uHWpZbyFBAmRryAROpukQ?e=DuOjyB). Esto le permitirá probar y evaluar la funcionalidad de la aplicación en su propio dispositivo.


https://github.com/carloscastillof/app_moviles_MISO/assets/124113463/4879c52b-a4fc-4cee-ac64-dbf77e283359


