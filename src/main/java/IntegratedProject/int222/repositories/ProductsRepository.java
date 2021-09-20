package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<products, Long> {

}
