package idv.nj.entity;

import idv.nj.entity.enums.ImageMimeType;
import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "mime_type", nullable = false)
    @Convert(converter = ImageMimeType.DbConverter.class)
    private ImageMimeType mimeType;

    @Column(name = "main")
    private boolean isMain = false;
    
    @Lob
    @Column(name = "data", nullable = false, length = 2 * 1024 * 1024) // 最大2MB
    private byte[] data;

    @Column(name = "product_id", nullable = false)
    private Long productId;
}

