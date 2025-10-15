# Módulo 7: Gestión de Lista Negra de Correos Electrónicos

## Casos de Uso
- CU-032: Crear correo electrónico en lista negra
- CU-031: Buscar correo electrónico en lista negra

## Diagrama de Secuencia

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Controller as FatalEmailController
    participant Service as FatalEmailService
    participant Repository as FatalEmailRepository
    participant DB as SQL Server

    Note over Client,DB: CU-032: Crear Email en Lista Negra
    Client->>Controller: POST /fatal-emails/add
    Controller->>Service: addToFatalList(email, reason)
    Service->>Repository: existsByEmail(email)
    Repository->>DB: SELECT COUNT(*) FROM fatal_emails WHERE email=?
    DB-->>Repository: count
    
    alt No existe en lista negra
        Service->>Service: Crear entidad FatalEmail
        Service->>Repository: save(fatalEmail)
        Repository->>DB: INSERT INTO fatal_emails (email, reason, added_at)
        DB-->>Repository: FatalEmail ID generado
        Repository-->>Service: FatalEmail entity
        Service-->>Controller: FatalEmailDTO
        Controller-->>Client: 201 Created
    else Ya existe
        Service-->>Controller: DuplicateException
        Controller-->>Client: 409 Conflict
    end

    Note over Client,DB: CU-031: Buscar Email en Lista Negra
    Client->>Controller: GET /fatal-emails/check?email=test@example.com
    Controller->>Service: isInFatalList(email)
    Service->>Repository: findByEmail(email)
    Repository->>DB: SELECT * FROM fatal_emails WHERE email=?
    DB-->>Repository: FatalEmail data or null
    Repository-->>Service: Optional<FatalEmail>
    
    alt Email encontrado
        Service-->>Controller: FatalEmailDTO (exists=true)
        Controller-->>Client: 200 OK + FatalEmailDTO
    else Email no encontrado
        Service-->>Controller: null (exists=false)
        Controller-->>Client: 404 Not Found
    end
```

