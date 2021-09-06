package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.Productcolor;
import IntegratedProject.int222.models.Productsize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsizeRepository extends JpaRepository<Productsize,Long> {
    Productsize[] findAllByProductId(Long longs);

}
