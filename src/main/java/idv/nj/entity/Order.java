package idv.nj.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "member_cart")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Lob
    @Column(name = "snap_shot", nullable = false)
    private String snapShot;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
}
