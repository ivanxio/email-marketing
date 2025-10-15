# Módulo 4: Gestión de Plantillas HTML

## Casos de Uso
- CU-017: Crear plantilla de correo electrónico HTML
- CU-018: Buscar plantilla de correo electrónico HTML por id
- CU-019: Recuperar plantillas de correo electrónico HTML por fecha de creación y paginación
- CU-020: Editar plantilla de correo electrónico HTML

## Diagrama de Secuencia

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Controller as TemplateController
    participant Service as TemplateService
    participant Validator as HTMLValidator
    participant Repository as TemplateRepository
    participant DB as SQL Server

    Note over Client,DB: CU-017: Crear Plantilla HTML
    Client->>Controller: POST /templates/create
    Controller->>Service: createTemplate(templateDTO)
    Service->>Validator: validateHTML(templateDTO.htmlContent)
    Validator->>Validator: Verificar sintaxis HTML
    Validator->>Validator: Verificar responsive design
    Validator-->>Service: ValidationResult
    alt HTML válido
        Service->>Repository: save(template)
        Repository->>DB: INSERT INTO templates
        DB-->>Repository: Template ID generado
        Repository-->>Service: Template entity
        Service-->>Controller: TemplateDTO
        Controller-->>Client: 201 Created
    else HTML inválido
        Service-->>Controller: ValidationException
        Controller-->>Client: 400 Bad Request
    end

    Note over Client,DB: CU-018: Buscar Plantilla por ID
    Client->>Controller: GET /templates/{templateId}
    Controller->>Service: findById(templateId)
    Service->>Repository: findById(templateId)
    Repository->>DB: SELECT * FROM templates WHERE id=?
    DB-->>Repository: Template data
    Repository-->>Service: Template entity
    Service-->>Controller: TemplateDTO
    Controller-->>Client: 200 OK + TemplateDTO

    Note over Client,DB: CU-019: Recuperar Plantillas Paginadas
    Client->>Controller: GET /templates?page=0&size=20&sort=createdAt,desc
    Controller->>Service: findAllPaginated(pageRequest)
    Service->>Repository: findAll(pageRequest)
    Repository->>DB: SELECT * FROM templates ORDER BY created_at DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
    DB-->>Repository: List<Template> + total count
    Repository-->>Service: Page<Template>
    Service-->>Controller: Page<TemplateDTO>
    Controller-->>Client: 200 OK + Page data

    Note over Client,DB: CU-020: Editar Plantilla HTML
    Client->>Controller: PUT /templates/{templateId}
    Controller->>Service: updateTemplate(templateId, templateDTO)
    Service->>Repository: findById(templateId)
    Repository->>DB: SELECT * FROM templates WHERE id=?
    DB-->>Repository: Template entity
    Service->>Validator: validateHTML(templateDTO.htmlContent)
    Validator-->>Service: ValidationResult
    alt HTML válido
        Service->>Service: Actualizar campos
        Service->>Repository: update(template)
        Repository->>DB: UPDATE templates SET ... WHERE id=?
        DB-->>Repository: 1 row affected
        Service-->>Controller: TemplateDTO
        Controller-->>Client: 200 OK
    else HTML inválido
        Service-->>Controller: ValidationException
        Controller-->>Client: 400 Bad Request
    end
```

