package hu.home.sbv.repo;

import hu.home.sbv.model.TaxType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxTypeRepo extends CrudRepository<TaxType, Long> {
}
