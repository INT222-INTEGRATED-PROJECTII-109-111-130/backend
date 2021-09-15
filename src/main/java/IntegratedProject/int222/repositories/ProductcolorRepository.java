package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.Cart;
import IntegratedProject.int222.models.Productcolor;
import IntegratedProject.int222.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ProductcolorRepository extends JpaRepository<Productcolor, Long> {

    Productcolor[] findAllByProductId(Long longs);
    void deleteByProductId(Long longs);





}
