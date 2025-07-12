package idv.nj.repo;

import idv.nj.dto.response.ProductLite;
import idv.nj.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Query("""
            SELECT
                p.id AS id,
                p.name AS name,
                p.category.id AS category,
                p.price AS price,
                p.quantity AS quantity,
                GROUP_CONCAT(CONCAT(i.sortOrder,':',i.imageId)) AS rawImages
            FROM Product p
            LEFT JOIN p.images i
            WHERE :categoryId IS NULL OR p.category.id = :categoryId
            GROUP BY p.id
            """)
    Page<ProductLite> findByCategoryId(@Param("categoryId") String categoryId, Pageable pageable);
}
