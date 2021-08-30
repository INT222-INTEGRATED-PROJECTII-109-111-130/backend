package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Long> {

}
