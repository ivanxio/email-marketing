package mx.gob.infonavit.emailmarketing.service.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidad que representa una plantilla HTML de correo electrónico.
 * 
 * Esta entidad almacena plantillas reutilizables para campañas de email marketing.
 * Cada plantilla contiene contenido HTML.
 * 
 * @author Ivan Garcia igarciam@desarrollo-ultrasis.com.mx
 * @version 1.0.0
 * @since 2025-10-20
 */
@Entity
@Table(name = "templates", indexes = {
    @Index(name = "idx_templates_created_by", columnList = "created_by"),
    @Index(name = "idx_templates_is_active", columnList = "is_active"),
    @Index(name = "idx_templates_created_at", columnList = "created_at")
})
@NamedQueries({
    @NamedQuery(
        name = Template.FIND_ALL_ORDERED_BY_DATE,
        query = "SELECT t FROM Template t ORDER BY t.createdAt DESC"
    ),
    @NamedQuery(
        name = Template.FIND_BY_NAME_CONTAINING,
        query = "SELECT t FROM Template t WHERE LOWER(t.name) LIKE LOWER(:name) ORDER BY t.createdAt DESC"
    )
})
public class Template implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String FIND_ALL_ORDERED_BY_DATE = "Template.findAllOrderedByDate";
    public static final String FIND_BY_NAME_CONTAINING = "Template.findByNameContaining";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "El nombre de la plantilla es obligatorio")
    @Size(max = 255, message = "El nombre no puede exceder 255 caracteres")
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    @Column(name = "description", length = 500)
    private String description;

    @NotBlank(message = "El contenido HTML es obligatorio")
    @Lob
    @Column(name = "html_content", nullable = false, columnDefinition = "TEXT")
    private String htmlContent;

    @NotNull(message = "El creador de la plantilla es obligatorio")
    @Column(name = "created_by", nullable = false)
    private Long createdBy;

    @NotNull
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Template() {
    }

    public Template(Long id,String name,
                    String description,
                    String htmlContent,
                    Long createdBy,
                    Boolean isActive,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {

        this.id = id;
        this.name = name;
        this.description = description;
        this.htmlContent = htmlContent;
        this.createdBy = createdBy;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Template)) {
            return false;
        }
        Template that = (Template) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(htmlContent, that.htmlContent) &&
                Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(isActive, that.isActive) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id, 
                name,
                description,
                htmlContent,
                createdBy,
                isActive,
                createdAt,
                updatedAt
        );
    }
}

