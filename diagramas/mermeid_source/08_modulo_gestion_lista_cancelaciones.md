# Módulo 8: Gestión de Lista de Cancelación de Suscripciones

## Casos de Uso
- CU-032: Crear correo electrónico que canceló su suscripción
- CU-033: Buscar correo electrónico que canceló su suscripción

## Diagrama de Secuencia

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Controller as UnsubscribeController
    participant Service as UnsubscribeService
    participant Repository as UnsubscribeRepository
    participant DB as SQL Server

    Note over Client,DB: CU-032: Crear Email que Canceló Suscripción
    Client->>Controller: POST /unsubscribe/add
    Controller->>Service: addToUnsubscribeList(email, reason, method)
    Service->>Repository: existsByEmail(email)
    Repository->>DB: SELECT COUNT(*) FROM unsubscribe_list WHERE email=?
    DB-->>Repository: count
    
    alt No existe en lista
        Service->>Service: Crear entidad UnsubscribeList
        Service->>Repository: save(unsubscribeRecord)
        Repository->>DB: INSERT INTO unsubscribe_list (email, reason, method, unsubscribed_at)
        DB-->>Repository: UnsubscribeList ID generado
        Repository-->>Service: UnsubscribeList entity
        Service-->>Controller: UnsubscribeDTO
        Controller-->>Client: 201 Created
    else Ya existe
        Service-->>Controller: DuplicateException
        Controller-->>Client: 409 Conflict
    end

    Note over Client,DB: CU-033: Buscar Email que Canceló Suscripción
    Client->>Controller: GET /unsubscribe/check?email=test@example.com
    Controller->>Service: hasUnsubscribed(email)
    Service->>Repository: findByEmail(email)
    Repository->>DB: SELECT * FROM unsubscribe_list WHERE email=?
    DB-->>Repository: UnsubscribeList data or null
    Repository-->>Service: Optional<UnsubscribeList>
    
    alt Email encontrado
        Service-->>Controller: UnsubscribeDTO (exists=true)
        Controller-->>Client: 200 OK + UnsubscribeDTO
    else Email no encontrado
        Service-->>Controller: null (exists=false)
        Controller-->>Client: 404 Not Found
    end
```

