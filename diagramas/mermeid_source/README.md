# Diagramas - Sistema de Email Marketing

Este directorio contiene los diagramas en formato Mermaid para el sistema de email marketing, incluyendo el diagrama entidad-relación completo y los diagramas de secuencia para cada módulo.

## Estructura de Archivos

| Archivo | Tipo | Módulo | Casos de Uso |
|---------|------|--------|--------------|
| `00_diagrama_entidad_relacion_completo.md` | **ER Diagram** | **Modelo de Datos Completo** | **Todos los CU** |
| `01_modulo_gestion_usuarios.md` | Secuencia | Gestión de Usuarios | CU-001 a CU-004 |
| `02_modulo_gestion_sesiones.md` | Secuencia | Gestión de Sesiones | CU-005 a CU-008 |
| `03_modulo_gestion_campanas.md` | Secuencia | Gestión de Campañas | CU-009 a CU-016 |
| `04_modulo_gestion_plantillas_html.md` | Secuencia | Gestión de Plantillas HTML | CU-017 a CU-020 |
| `05_modulo_gestion_archivos_adjuntos.md` | Secuencia | Gestión de Archivos Adjuntos | CU-021 a CU-024 |
| `06_modulo_gestion_bases_datos_emails.md` | Secuencia | Gestión de Bases de Datos de Emails | CU-025 a CU-031 |
| `07_modulo_gestion_lista_negra.md` | Secuencia | Gestión de Lista Negra | CU-031 a CU-032 |
| `08_modulo_gestion_lista_cancelaciones.md` | Secuencia | Gestión de Lista de Cancelaciones | CU-032 a CU-033 |
| `09_modulo_gestion_lista_blanca.md` | Secuencia | Gestión de Lista Blanca | CU-034 |

## Diagrama Entidad-Relación (ER)

El archivo `00_diagrama_entidad_relacion_completo.md` contiene el modelo de datos completo del sistema. Este diagrama incluye:

### Entidades Principales:
- **users**: Usuarios del sistema con autenticación BCrypt
- **sessions**: Sesiones activas con tokens UUID
- **campaigns**: Campañas de email marketing con estados y estadísticas
- **templates**: Plantillas HTML reutilizables
- **attachments**: Archivos PDF adjuntos
- **upload_batches**: ⭐ **Tabla clave** - Gestiona archivos de contactos con validaciones
- **fatal_emails**: Lista negra de correos bloqueados
- **unsubscribe_list**: Correos que cancelaron suscripción
- **valid_contacts**: Lista blanca de correos validados

### Tablas Intermedias (N:M):
- **campaign_templates**: Relaciona campañas con plantillas
- **campaign_attachments**: Relaciona campañas con adjuntos
- **campaign_upload_batches**: Relaciona campañas con batches de contactos

### Características del Diseño:
✅ **NO incluye tabla de emails individuales** - Los emails se gestionan mediante archivos CSV  
✅ **Gestión dual de archivos** - Original (`original_file_path`) + Procesado (`output_file_path`)  
✅ **Estadísticas integradas** - Contadores de validación en `upload_batches`  
✅ **Trazabilidad completa** - Auditoría de creadores y fechas  
✅ **Reutilización de recursos** - Plantillas, adjuntos y batches compartidos  

## Cómo Visualizar los Diagramas

### Opción 1: GitHub
Los archivos `.md` se pueden visualizar directamente en GitHub, que renderiza automáticamente los diagramas Mermaid.

### Opción 2: VS Code
Instalar la extensión "Markdown Preview Mermaid Support" para visualizar los diagramas en VS Code.

### Opción 3: Mermaid Live Editor
Copiar el código Mermaid y pegarlo en: https://mermaid.live/

### Opción 4: Documentación
Usar herramientas como MkDocs o Docusaurus que soportan Mermaid nativamente.

## Convenciones de Diagramas

### Diagramas de Secuencia
- **Participants**: Cliente, Controller, Service, Repository, DB
- **Formato de Notes**: `CU-XXX: Descripción del caso de uso`
- **Respuestas HTTP**: Incluyen código de estado (201 Created, 200 OK, 404 Not Found, etc.)
- **Alt/Else**: Se usan para mostrar flujos alternativos y manejo de errores
- **Loop**: Se usa para operaciones repetitivas

### Diagrama Entidad-Relación
- **PK**: Primary Key
- **FK**: Foreign Key
- **UK**: Unique Key
- **Relaciones**: `||--o{` (uno a muchos), `}o--o{` (muchos a muchos)
- **Cardinalidad**: Expresada en la notación Crow's Foot

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

### Diagramas de Secuencia
- Los diagramas están en **blanco y negro** para facilitar impresión
- Cada módulo es independiente y puede ser visualizado por separado
- Los DTOs (Data Transfer Objects) se usan para transferir datos entre capas
- Las validaciones se realizan en la capa de servicio antes de persistir
- Todas las operaciones de escritura requieren transacciones (`@Transactional`)

### Diagrama Entidad-Relación
- El diseño NO incluye tabla de emails individuales para optimizar performance
- Los contactos se gestionan mediante archivos CSV referenciados en `upload_batches`
- Cada `upload_batch` almacena tanto el archivo original como el procesado
- Las validaciones (formato, DACI, listas) se aplican durante la carga
- El modelo está optimizado para alto volumen (millones de emails)
- Incluye índices recomendados para queries frecuentes

---

**Fecha de Creación**: 15 de Octubre, 2025  
**Versión**: 1.0  
**Autor**: Equipo de Desarrollo - INFONAVIT

