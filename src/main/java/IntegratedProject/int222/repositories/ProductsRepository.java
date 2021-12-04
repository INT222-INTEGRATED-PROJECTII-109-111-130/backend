package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.productcolor;
import IntegratedProject.int222.models.products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<products, Long> {
    products[] findAllByProductId(Long longs);
    void deleteAllByBrandId(Long longs);

}
