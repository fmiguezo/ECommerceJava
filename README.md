# 🛒 Proyecto E-Commerce

Este proyecto es una simulación de un sistema de gestión de productos para un e-commerce. Fue desarrollado como parte de un trabajo práctico, pero con un enfoque profesional: aplicando arquitectura limpia, TDD y DTOs 💡.

---

## 🧠 Conceptos aplicados

- ✅ **Arquitectura limpia (Clean Architecture)**  
  Separación de responsabilidades por capas: dominio, aplicación, infraestructura y adaptadores.

- ✅ **TDD (Test-Driven Development)**  
  El desarrollo se guió escribiendo primero los tests unitarios con **JUnit 5** y **Mockito** 🧪.

- ✅ **Uso de DTOs**  
  Se usaron DTOs (Data Transfer Objects) para desacoplar los datos de entrada/salida del modelo de dominio.

- ✅ **Modelo de dominio rico**  
  Las entidades encapsulan comportamiento y validaciones propias.

---

## 📦 Casos de uso implementados

- 📌 **Crear producto**
- 📌 **Listar productos**
- 📌 **Buscar producto por nombre**
- 📌 **Actualizar producto** (precio y stock)
- 📌 **Eliminar producto**
- 📌 **Aumentar stock**
- 📌 **Disminuir stock** (validando disponibilidad)

---

## ⚠️ Validaciones ante

- Producto duplicado
- Stock insuficiente
- Cantidad negativa

---

## ⚠️ Excepciones personalizadas

- `ProductoYaExistenteException`
- `ProductoNoEncontradoException`
- `StockInsuficienteException`
- `CantidadNegativaException`

Estas excepciones permiten expresar reglas de negocio de forma clara y reutilizable dentro del dominio.

---

## 🛠️ Tecnologías utilizadas

- ☕ Java 17  
- 🧪 JUnit 5  
- 🧰 Mockito  
- 🔧 Maven
- 🧱 Clean Architecture  

---

## 🚀 Cómo correr el proyecto

### Requisitos previos

- ☕ Java 17 instalado
- 🔧 Maven 3.8+ instalado
- 💻 IDE recomendado: IntelliJ IDEA, Eclipse o VS Code con soporte para Java
---

### 💻 ¿Qué podés hacer desde la CLI?
Desde la línea de comandos vas a poder:
📌 Crear productos
📋 Listar productos
🔍 Buscar productos por nombre
✏️ Modificar precio y stock
🗑️ Eliminar productos
➕ Aumentar stock
➖ Disminuir stock (con validación de disponibilidad)

Todo esto sin necesidad de interfaz gráfica ni conexión a base de datos.

---
### 🧪 Ejecutar los tests

Se usó **JUnit 5** y **Mockito** para validar todos los casos de uso importantes del dominio.  
La cobertura incluye:

- Creación de productos
- Validación de duplicados
- Búsqueda, listado, eliminación
- Disminución y aumento de stock
- Manejo de excepciones de negocio

### ▶️ Para correr los tests

```bash
# Maven
./mvnw test
```
---

## 🤓 Sobre el desarrollo

Este proyecto no solo cumple con los requisitos funcionales, sino que **agrega valor extra** al implementar principios de diseño y buenas prácticas que son estándar en el desarrollo profesional de software. Fue pensado para ser **mantenible, escalable y fácil de entender**.

> 🛠️ **Entrega parcial:** este repositorio está en desarrollo activo.  
> Próximamente se implementará:
> 💾 Persistencia de datos en una base de datos
> 🖥️ Interfaz web amigable para gestionar productos desde el navegador


---

## 🙋‍♀️ Contribuciones

Este proyecto fue realizado como práctica individual, pero se aceptan ideas, sugerencias o mejoras.  
¡Todo feedback es bienvenido!
