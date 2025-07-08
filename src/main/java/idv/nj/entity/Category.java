package idv.nj.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "id",length = 255, nullable = false)
    private String id;

    @Column(name = "display_name",length = 255, nullable = false)
    private String displayName;
}
