# Diagramas de Secuencia - Sistema de Email Marketing

Este directorio contiene los diagramas de secuencia en formato Mermaid para cada módulo del sistema.

## Estructura de Archivos

| Archivo | Módulo | Casos de Uso |
|---------|--------|--------------|
| `01_modulo_gestion_usuarios.md` | Gestión de Usuarios | CU-001 a CU-004 |
| `02_modulo_gestion_sesiones.md` | Gestión de Sesiones | CU-005 a CU-008 |
| `03_modulo_gestion_campanas.md` | Gestión de Campañas | CU-009 a CU-016 |
| `04_modulo_gestion_plantillas_html.md` | Gestión de Plantillas HTML | CU-017 a CU-020 |
| `05_modulo_gestion_archivos_adjuntos.md` | Gestión de Archivos Adjuntos | CU-021 a CU-024 |
| `06_modulo_gestion_bases_datos_emails.md` | Gestión de Bases de Datos de Emails | CU-025 a CU-031 |
| `07_modulo_gestion_lista_negra.md` | Gestión de Lista Negra | CU-031 a CU-032 |
| `08_modulo_gestion_lista_cancelaciones.md` | Gestión de Lista de Cancelaciones | CU-032 a CU-033 |
| `09_modulo_gestion_lista_blanca.md` | Gestión de Lista Blanca | CU-034 |

## Cómo Visualizar los Diagramas

### Opción 1: GitHub
Los archivos `.md` se pueden visualizar directamente en GitHub, que renderiza automáticamente los diagramas Mermaid.

### Opción 2: VS Code
Instalar la extensión "Markdown Preview Mermaid Support" para visualizar los diagramas en VS Code.

### Opción 3: Mermaid Live Editor
Copiar el código Mermaid y pegarlo en: https://mermaid.live/

### Opción 4: Documentación
Usar herramientas como MkDocs o Docusaurus que soportan Mermaid nativamente.

## Convenciones de Diagrama

- **Participants**: Cliente, Controller, Service, Repository, DB
- **Formato de Notes**: `CU-XXX: Descripción del caso de uso`
- **Respuestas HTTP**: Incluyen código de estado (201 Created, 200 OK, 404 Not Found, etc.)
- **Alt/Else**: Se usan para mostrar flujos alternativos y manejo de errores
- **Loop**: Se usa para operaciones repetitivas

## Arquitectura Representada

Todos los diagramas siguen la arquitectura en capas:

```
Cliente → Controller → Service → Repository → Database
```

### Capas:
1. **Cliente**: Aplicación web o API consumer
2. **Controller**: Capa de presentación (REST Controllers / Managed Beans)
3. **Service**: Lógica de negocio (EJB Stateless)
4. **Repository**: Capa de acceso a datos (JPA Repositories)
5. **Database**: Microsoft SQL Server

## Tecnologías Representadas

- **Java 8**
- **JEE 8**
- **SQL Server**
- **WAS 9.0.5**
- **JPA / Hibernate**
- **REST APIs**

## Notas Importantes

- Los diagramas están en **blanco y negro** para facilitar impresión
- Cada módulo es independiente y puede ser visualizado por separado
- Los DTOs (Data Transfer Objects) se usan para transferir datos entre capas
- Las validaciones se realizan en la capa de servicio antes de persistir
- Todas las operaciones de escritura requieren transacciones (`@Transactional`)

---

**Fecha de Creación**: 15 de Octubre, 2025  
**Versión**: 1.0  
**Autor**: Equipo de Desarrollo - INFONAVIT

