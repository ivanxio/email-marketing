# Módulo 5: Gestión de Archivos Adjuntos

## Casos de Uso
- CU-021: Crear archivo adjunto
- CU-022: Buscar archivo adjunto por id
- CU-023: Recuperar adjunto por fecha de creación y paginación
- CU-024: Editar archivo adjunto

## Diagrama de Secuencia

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Controller as AttachmentController
    participant Service as AttachmentService
    participant FileStorage as FileStorageService
    participant Repository as AttachmentRepository
    participant DB as SQL Server

    Note over Client,DB: CU-021: Crear Archivo Adjunto
    Client->>Controller: POST /attachments/upload (multipart/form-data)
    Controller->>Service: createAttachment(file, metadata)
    Service->>Service: Validar tipo de archivo (PDF)
    Service->>Service: Validar tamaño (max 10MB)
    alt Archivo válido
        Service->>FileStorage: saveFile(file)
        FileStorage->>FileStorage: Generar nombre único
        FileStorage->>FileStorage: Guardar en sistema de archivos
        FileStorage-->>Service: storagePath
        Service->>Service: Crear entidad Attachment
        Service->>Repository: save(attachment)
        Repository->>DB: INSERT INTO attachments
        DB-->>Repository: Attachment ID generado
        Repository-->>Service: Attachment entity
        Service-->>Controller: AttachmentDTO
        Controller-->>Client: 201 Created
    else Archivo inválido
        Service-->>Controller: ValidationException
        Controller-->>Client: 400 Bad Request
    end

    Note over Client,DB: CU-022: Buscar Archivo Adjunto por ID
    Client->>Controller: GET /attachments/{attachmentId}
    Controller->>Service: findById(attachmentId)
    Service->>Repository: findById(attachmentId)
    Repository->>DB: SELECT * FROM attachments WHERE id=?
    DB-->>Repository: Attachment data
    Repository-->>Service: Attachment entity
    Service-->>Controller: AttachmentDTO
    Controller-->>Client: 200 OK + AttachmentDTO

    Note over Client,DB: CU-023: Recuperar Adjuntos Paginados
    Client->>Controller: GET /attachments?page=0&size=20&sort=createdAt,desc
    Controller->>Service: findAllPaginated(pageRequest)
    Service->>Repository: findAll(pageRequest)
    Repository->>DB: SELECT * FROM attachments ORDER BY created_at DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
    DB-->>Repository: List<Attachment> + total count
    Repository-->>Service: Page<Attachment>
    Service-->>Controller: Page<AttachmentDTO>
    Controller-->>Client: 200 OK + Page data

    Note over Client,DB: CU-024: Editar Archivo Adjunto (metadata)
    Client->>Controller: PUT /attachments/{attachmentId}
    Controller->>Service: updateAttachment(attachmentId, attachmentDTO)
    Service->>Repository: findById(attachmentId)
    Repository->>DB: SELECT * FROM attachments WHERE id=?
    DB-->>Repository: Attachment entity
    Service->>Service: Actualizar metadata (nombre, descripción)
    Service->>Repository: update(attachment)
    Repository->>DB: UPDATE attachments SET ... WHERE id=?
    DB-->>Repository: 1 row affected
    Repository-->>Service: Updated Attachment
    Service-->>Controller: AttachmentDTO
    Controller-->>Client: 200 OK
```

