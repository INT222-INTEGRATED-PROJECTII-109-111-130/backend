package IntegratedProject.int222.repositories;

import IntegratedProject.int222.models.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountsRepository extends JpaRepository<Accounts, Long> {
}
