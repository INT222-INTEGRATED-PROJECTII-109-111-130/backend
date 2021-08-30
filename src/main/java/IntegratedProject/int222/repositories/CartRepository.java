package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.Brands;
import IntegratedProject.int222.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Cart[] findAllByAccountId(Long longs);
    Cart[] findAllByProductId(Long longs);

}
