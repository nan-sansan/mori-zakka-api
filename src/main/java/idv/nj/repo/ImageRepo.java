package idv.nj.repo;

import idv.nj.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
