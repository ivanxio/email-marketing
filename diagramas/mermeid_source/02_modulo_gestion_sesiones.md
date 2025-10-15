# Módulo 2: Gestión de Sesiones

## Casos de Uso
- CU-005: Crear sesión por usuario
- CU-006: Buscar sesión por id
- CU-007: Actualizar fecha de expiración de la sesión por id
- CU-008: Eliminar sesión por id

## Diagrama de Secuencia

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Controller as SessionController
    participant Service as SessionService
    participant Repository as SessionRepository
    participant DB as SQL Server

    Note over Client,DB: CU-005: Crear Sesión por Usuario
    Client->>Controller: POST /sessions/create
    Controller->>Service: createSession(userId)
    Service->>Service: Generar token UUID
    Service->>Service: Calcular fecha expiración (NOW + 30 min)
    Service->>Repository: save(session)
    Repository->>DB: INSERT INTO sessions (user_id, token, expires_at)
    DB-->>Repository: Session ID generado
    Repository-->>Service: Session entity
    Service-->>Controller: SessionDTO
    Controller-->>Client: 201 Created + token

    Note over Client,DB: CU-006: Buscar Sesión por ID
    Client->>Controller: GET /sessions/{sessionId}
    Controller->>Service: findById(sessionId)
    Service->>Repository: findById(sessionId)
    Repository->>DB: SELECT * FROM sessions WHERE id=?
    DB-->>Repository: Session data
    Repository-->>Service: Session entity
    Service-->>Controller: SessionDTO
    Controller-->>Client: 200 OK + SessionDTO

    Note over Client,DB: CU-007: Actualizar Fecha de Expiración
    Client->>Controller: PATCH /sessions/{sessionId}/extend
    Controller->>Service: extendSession(sessionId)
    Service->>Repository: findById(sessionId)
    Repository->>DB: SELECT * FROM sessions WHERE id=?
    DB-->>Repository: Session entity
    Service->>Service: Calcular nueva expiración (+30 min)
    Service->>Repository: updateExpiresAt(sessionId, newExpiration)
    Repository->>DB: UPDATE sessions SET expires_at=? WHERE id=?
    DB-->>Repository: 1 row affected
    Repository-->>Service: Updated Session
    Service-->>Controller: SessionDTO
    Controller-->>Client: 200 OK

    Note over Client,DB: CU-008: Eliminar Sesión por ID
    Client->>Controller: DELETE /sessions/{sessionId}
    Controller->>Service: deleteSession(sessionId)
    Service->>Repository: deleteById(sessionId)
    Repository->>DB: DELETE FROM sessions WHERE id=?
    DB-->>Repository: 1 row affected
    Repository-->>Service: Success
    Service-->>Controller: Success
    Controller-->>Client: 204 No Content
```

