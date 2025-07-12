package idv.nj.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
@IdClass(ProductImage.ProductImageId.class)
public class ProductImage {
    @Id
    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Id
    @Column(name = "image_id", nullable = false)
    private Long imageId;

    @Column(name = "sort_order", columnDefinition = "INT UNSIGNED DEFAULT 0", nullable = false)
    private Long sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_id",insertable = false, updatable = false)
    private Image image;

    public static class ProductImageId {
        private Long productId;
        private Long imageId;
    }
}

