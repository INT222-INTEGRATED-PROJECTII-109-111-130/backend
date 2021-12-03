package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface AccountsRepository extends JpaRepository<accounts, Long> {
    accounts  findByEmail(String s);

}
