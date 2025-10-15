# Módulo 9: Gestión de Lista Blanca de Correos Electrónicos

## Casos de Uso
- CU-034: Crear correo electrónico que pasó todas sus validaciones
- CU-034: Buscar correo electrónico que pasó todas sus validaciones

## Diagrama de Secuencia

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Controller as ValidContactController
    participant Service as ValidContactService
    participant Repository as ValidContactRepository
    participant DB as SQL Server

    Note over Client,DB: CU-034: Crear Email Validado (Lista Blanca)
    Client->>Controller: POST /valid-contacts/add
    Controller->>Service: addToWhitelist(email, validationDetails)
    Service->>Repository: existsByEmail(email)
    Repository->>DB: SELECT COUNT(*) FROM valid_contacts WHERE email=?
    DB-->>Repository: count
    
    alt No existe en lista blanca
        Service->>Service: Crear entidad ValidContact
        Service->>Repository: save(validContact)
        Repository->>DB: INSERT INTO valid_contacts (email, validated_at, validation_method)
        DB-->>Repository: ValidContact ID generado
        Repository-->>Service: ValidContact entity
        Service-->>Controller: ValidContactDTO
        Controller-->>Client: 201 Created
    else Ya existe
        Service->>Service: Actualizar fecha de validación
        Service->>Repository: update(validContact)
        Repository->>DB: UPDATE valid_contacts SET validated_at=? WHERE email=?
        DB-->>Repository: 1 row affected
        Service-->>Controller: ValidContactDTO
        Controller-->>Client: 200 OK
    end

    Note over Client,DB: CU-034: Buscar Email Validado
    Client->>Controller: GET /valid-contacts/check?email=test@example.com
    Controller->>Service: isValidated(email)
    Service->>Repository: findByEmail(email)
    Repository->>DB: SELECT * FROM valid_contacts WHERE email=?
    DB-->>Repository: ValidContact data or null
    Repository-->>Service: Optional<ValidContact>
    
    alt Email encontrado
        Service-->>Controller: ValidContactDTO (isValid=true)
        Controller-->>Client: 200 OK + ValidContactDTO
    else Email no encontrado
        Service-->>Controller: null (isValid=false)
        Controller-->>Client: 404 Not Found
    end
```

