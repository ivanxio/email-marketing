# Módulo 3: Gestión de Campañas

## Casos de Uso
- CU-009: Crear nueva campaña de correo electrónico
- CU-010: Buscar campaña de correo electrónico por id
- CU-012: Recuperar campañas de correo electrónico ordenado por fecha de creación y paginado
- CU-013: Una campaña puede actualizarse en estatus DRAFT, SCHEDULED (Antes de la fecha de ejecución)
- CU-014: Una campaña IN_PROGRESS puede pausarse
- CU-015: Una campaña COMPLETED no puede modificarse
- CU-016: Una campaña se puede Pausar/Reanudar su ejecución

## Diagrama de Secuencia

```mermaid
sequenceDiagram
    participant Client as Cliente
    participant Controller as CampaignController
    participant Service as CampaignService
    participant Repository as CampaignRepository
    participant Validator as CampaignValidator
    participant DB as SQL Server

    Note over Client,DB: CU-009: Crear Nueva Campaña
    Client->>Controller: POST /campaigns/create
    Controller->>Service: createCampaign(campaignDTO)
    Service->>Validator: validateCampaignData(campaignDTO)
    Validator-->>Service: Valid
    Service->>Service: Set status = DRAFT
    Service->>Repository: save(campaign)
    Repository->>DB: INSERT INTO campaigns
    DB-->>Repository: Campaign ID generado
    Repository-->>Service: Campaign entity
    Service-->>Controller: CampaignDTO
    Controller-->>Client: 201 Created

    Note over Client,DB: CU-010: Buscar Campaña por ID
    Client->>Controller: GET /campaigns/{campaignId}
    Controller->>Service: findById(campaignId)
    Service->>Repository: findById(campaignId)
    Repository->>DB: SELECT * FROM campaigns WHERE id=?
    DB-->>Repository: Campaign data
    Repository-->>Service: Campaign entity
    Service-->>Controller: CampaignDTO
    Controller-->>Client: 200 OK + CampaignDTO

    Note over Client,DB: CU-012: Recuperar Campañas Paginadas
    Client->>Controller: GET /campaigns?page=0&size=20&sort=createdAt,desc
    Controller->>Service: findAllPaginated(pageRequest)
    Service->>Repository: findAll(pageRequest)
    Repository->>DB: SELECT * FROM campaigns ORDER BY created_at DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
    DB-->>Repository: List<Campaign> + total count
    Repository-->>Service: Page<Campaign>
    Service-->>Controller: Page<CampaignDTO>
    Controller-->>Client: 200 OK + Page data

    Note over Client,DB: CU-013: Actualizar Campaña DRAFT o SCHEDULED
    Client->>Controller: PUT /campaigns/{campaignId}
    Controller->>Service: updateCampaign(campaignId, campaignDTO)
    Service->>Repository: findById(campaignId)
    Repository->>DB: SELECT * FROM campaigns WHERE id=?
    DB-->>Repository: Campaign entity
    Service->>Service: Validar status (DRAFT o SCHEDULED antes de fecha)
    alt Status permite actualización
        Service->>Repository: update(campaign)
        Repository->>DB: UPDATE campaigns SET ... WHERE id=?
        DB-->>Repository: 1 row affected
        Service-->>Controller: CampaignDTO
        Controller-->>Client: 200 OK
    else Status no permite actualización
        Service-->>Controller: BusinessException
        Controller-->>Client: 400 Bad Request
    end

    Note over Client,DB: CU-014: Pausar Campaña IN_PROGRESS
    Client->>Controller: POST /campaigns/{campaignId}/pause
    Controller->>Service: pauseCampaign(campaignId)
    Service->>Repository: findById(campaignId)
    Repository->>DB: SELECT * FROM campaigns WHERE id=?
    DB-->>Repository: Campaign entity
    Service->>Service: Validar status = IN_PROGRESS
    alt Status es IN_PROGRESS
        Service->>Service: Set status = PAUSED
        Service->>Repository: update(campaign)
        Repository->>DB: UPDATE campaigns SET status='PAUSED' WHERE id=?
        DB-->>Repository: 1 row affected
        Service-->>Controller: CampaignDTO
        Controller-->>Client: 200 OK
    else Status no es IN_PROGRESS
        Service-->>Controller: BusinessException
        Controller-->>Client: 400 Bad Request
    end

    Note over Client,DB: CU-016: Reanudar Campaña PAUSED
    Client->>Controller: POST /campaigns/{campaignId}/resume
    Controller->>Service: resumeCampaign(campaignId)
    Service->>Repository: findById(campaignId)
    Repository->>DB: SELECT * FROM campaigns WHERE id=?
    DB-->>Repository: Campaign entity
    Service->>Service: Validar status = PAUSED
    alt Status es PAUSED
        Service->>Service: Set status = IN_PROGRESS
        Service->>Repository: update(campaign)
        Repository->>DB: UPDATE campaigns SET status='IN_PROGRESS' WHERE id=?
        DB-->>Repository: 1 row affected
        Service-->>Controller: CampaignDTO
        Controller-->>Client: 200 OK
    else Status no es PAUSED
        Service-->>Controller: BusinessException
        Controller-->>Client: 400 Bad Request
    end
```

