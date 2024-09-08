# Emulador_Arquitectura_Computacional
## Índice de Contenido
1. [Descripción del Emulador](#descripción-del-emulador)
2. [Operaciones Soportadas](#operaciones-soportadas)
    - [Movimiento de Datos](#movimiento-de-datos)
    - [Operaciones Aritméticas](#operaciones-aritméticas)
3. [Cómo Funciona: Ejemplo con Bubble Sort](#cómo-funciona-ejemplo-con-bubble-sort)
4. [Cómo Usar el Programa](#cómo-usar-el-programa)
5. [Imágenes del Emulador](#imágenes-del-emulador)
6. [Autor](#autor)

## Descripción del Emulador
Este emulador de CPU simula el funcionamiento de una Unidad Central de Procesamiento (CPU), incluyendo una Unidad Aritmético-Lógica (ALU), una Unidad de Control (CU), registros, memoria principal y caché. El emulador permite ejecutar instrucciones básicas de manipulación de datos y operaciones aritméticas, así como visualizar el estado interno de la CPU durante la ejecución de las instrucciones.

## Operaciones Soportadas

### Movimiento de Datos
- **LOAD**: Carga un valor desde la memoria a un registro.
  - Ejemplo: `LOAD R1, 1000` (Carga el valor en la dirección de memoria 1000 en el registro R1).
  
- **STORE**: Almacena el valor de un registro en una ubicación de memoria.
  - Ejemplo: `STORE R1, 1000` (Almacena el valor del registro R1 en la dirección de memoria 1000).

- **MOVE**: Copia datos de un registro a otro.
  - Ejemplo: `MOVE R1, R2` (Copia el valor de R2 a R1).

### Operaciones Aritméticas
- **ADD**: Suma dos valores.
  - Ejemplo: `ADD R1, R2` (Suma los valores de R1 y R2, y almacena el resultado en R1).

- **SUB**: Resta dos valores.
  - Ejemplo: `SUB R1, R2` (Resta el valor de R1 del valor de R2, y almacena el resultado en R1).

- **MUL**: Multiplica dos valores.
  - Ejemplo: `MUL R1, R2` (Multiplica los valores de R1 y R2, y almacena el resultado en R1).

## Cómo Funciona: Ejemplo con Bubble Sort
<table>
<tr>
<td>

**Descripción:**
El método `realizarBUBBLE` implementa el algoritmo de ordenamiento Bubble Sort usando las operaciones soportadas por el emulador. Este método se encarga de ordenar un conjunto de datos almacenados en la memoria, intercambiando valores según sea necesario hasta que el conjunto esté ordenado.

</td>
<td>
<img src="https://github.com/user-attachments/assets/4bc89594-b82b-4466-a300-2905dbec26bd"alt="Gif Bubble" width="6000">


</td>
</tr>
</table>


```java
private void realizarBUBBLE(int registro1, int regisMemoria) {
    int aux = registro1;
    boolean intercambio; 
    for (int i = 0; i < aux - 1; i++) {
        intercambio = false;
        for (int j = 0; j < aux - 1 - i; j++) {
            realizarLOAD(0, j);        // Cargar valor de la posición j en R0
            realizarLOAD(1, j + 1);    // Cargar valor de la posición j+1 en R1
            realizarSUB(0, 1);         // Comparar R0 y R1 (R0 - R1)

            if (!cpu.getALU().isNegative()) { // Si R0 >= R1, intercambiar
                realizarLOAD(0, j);
                realizarSTORE(1, j);      // Almacenar R1 en la posición j
                realizarSTORE(0, j + 1);  // Almacenar R0 en la posición j+1
                intercambio = true; 
            }
        }
        if (!intercambio) {
            break;
        }
    }
}

````
## Cómo Usar el Programa

1. Clona el repositorio a tu máquina local usando el siguiente comando:

    ```bash
    git clone https://github.com/FernandoHuilca/Emulador_Arquitectura_Computacional.git
    ```

2. Abre el proyecto en **[NetBeans](https://netbeans.apache.org/front/main/download/)**.

3. Compila y ejecuta el proyecto desde NetBeans.

4. La interfaz gráfica del emulador fue desarrollada usando **[Scene Builder](https://gluonhq.com/products/scene-builder/)**, por lo que puedes editarla o personalizarla utilizando esta herramienta si lo deseas.

5. Los datos en memoria se encuentran en un archivo `.txt` incluido en el repositorio. Si deseas modificar los datos directamente, simplemente edita este archivo antes de ejecutar el programa.

## Imágenes del Emulador
<table>
    <tr>
    <td><img src="https://github.com/user-attachments/assets/0b093272-f705-463c-a41e-dd67028c68ab" alt="Interfaz de Pedidos"></td>
    <td><img src="https://github.com/user-attachments/assets/854fb7e8-24e0-4d41-977c-c8d86cf2ee29"></td>
  </tr>
   <tr>
    <td><img src="https://github.com/user-attachments/assets/3f5723cd-bb97-4445-aac3-a9ef57e23063" alt="Interfaz de Pedidos"></td>
    <td><img src="https://github.com/user-attachments/assets/c0deea52-5ca4-47ea-9d9a-872761f67a67"></td>
  </tr>
</table>

## Autor

- **Fernando Huilca** - [@FernandoHuilca](https://github.com/FernandoHuilca)

Si tienes alguna pregunta o necesitas ayuda, no dudes en contactarme a través de [fernando.huilca@epn.edu.ec](mailto:fernando.huilca@epn.edu.ec).

¡Espero que encuentres este repositorio útil!

---

_**Ingeniería de Software** | **Escuela Politécnica Nacional** | **Fernando Huilca**_
