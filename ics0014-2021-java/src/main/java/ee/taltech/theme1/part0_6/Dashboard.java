package ee.taltech.theme1.part0_6;

import java.time.LocalDateTime;
import java.util.Objects;

//example of class with instance methods only
//code copied from real project
public class Dashboard {

    private Long id;
    private String code;
    private String nameEt;
    private String nameEn;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }

    public Dashboard setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Dashboard setCode(String code) {
        this.code = code;
        return this;
    }

    public String getNameEt() {
        return nameEt;
    }

    public Dashboard setNameEt(String nameEt) {
        this.nameEt = nameEt;
        return this;
    }

    public String getNameEn() {
        return nameEn;
    }

    public Dashboard setNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Dashboard setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Dashboard setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dashboard dashboard = (Dashboard) o;
        return Objects.equals(id, dashboard.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Dashboard{");
        sb.append("id=").append(id);
        sb.append(", code='").append(code).append('\'');
        sb.append(", nameEt='").append(nameEt).append('\'');
        sb.append(", nameEn='").append(nameEn).append('\'');
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }
}
