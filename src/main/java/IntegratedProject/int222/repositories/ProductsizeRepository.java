package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.productsize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsizeRepository extends JpaRepository<productsize,Long> {
    productsize[] findAllByProductId(Long longs);
    void deleteByProductId(Long longs);

}
