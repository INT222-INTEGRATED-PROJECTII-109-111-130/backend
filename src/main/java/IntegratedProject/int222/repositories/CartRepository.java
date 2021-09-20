package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<cart, Long> {

    cart[] findAllByAccountId(Long longs);
    cart[] findAllByProductId(Long longs);

}
