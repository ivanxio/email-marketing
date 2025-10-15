# Módulo 1: Gestión de Usuarios

## Casos de Uso
- CU-001: Crear usuario
- CU-002: Buscar usuario por username
- CU-003: Actualizar usuario por username
- CU-004: Actualizar última fecha de login por username

## Diagrama de Secuencia

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Controller as UserController
    participant Service as UserService
    participant Repository as UserRepository
    participant DB as SQL Server

    Note over Client,DB: CU-001: Crear Usuario
    Client->>Controller: POST /users/create
    Controller->>Service: createUser(userDTO)
    Service->>Service: Validar datos
    Service->>Service: Cifrar password con BCrypt
    Service->>Repository: save(user)
    Repository->>DB: INSERT INTO users
    DB-->>Repository: User ID generado
    Repository-->>Service: User entity
    Service-->>Controller: UserDTO
    Controller-->>Client: 201 Created

    Note over Client,DB: CU-002: Buscar Usuario por Username
    Client->>Controller: GET /users/search?username=jperez
    Controller->>Service: findByUsername(username)
    Service->>Repository: findByUsername(username)
    Repository->>DB: SELECT * FROM users WHERE username=?
    DB-->>Repository: User data
    Repository-->>Service: User entity
    Service-->>Controller: UserDTO
    Controller-->>Client: 200 OK + UserDTO

    Note over Client,DB: CU-003: Actualizar Usuario por Username
    Client->>Controller: PUT /users/update/{username}
    Controller->>Service: updateByUsername(username, userDTO)
    Service->>Repository: findByUsername(username)
    Repository->>DB: SELECT * FROM users WHERE username=?
    DB-->>Repository: User entity
    Service->>Service: Actualizar campos modificables
    Service->>Repository: update(user)
    Repository->>DB: UPDATE users SET ... WHERE username=?
    DB-->>Repository: Rows affected
    Repository-->>Service: Updated User
    Service-->>Controller: UserDTO
    Controller-->>Client: 200 OK

    Note over Client,DB: CU-004: Actualizar Última Fecha de Login
    Client->>Controller: PATCH /users/{username}/last-login
    Controller->>Service: updateLastLogin(username)
    Service->>Repository: updateLastLoginByUsername(username)
    Repository->>DB: UPDATE users SET last_login=CURRENT_TIMESTAMP WHERE username=?
    DB-->>Repository: 1 row affected
    Repository-->>Service: Success
    Service-->>Controller: Success
    Controller-->>Client: 204 No Content
```

