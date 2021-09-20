package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.productcolor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductcolorRepository extends JpaRepository<productcolor, Long> {

    productcolor[] findAllByProductId(Long longs);
    void deleteByProductId(Long longs);





}
