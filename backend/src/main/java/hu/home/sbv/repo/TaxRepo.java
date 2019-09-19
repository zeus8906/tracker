package hu.home.sbv.repo;

import hu.home.sbv.model.Tax;
import org.springframework.data.repository.CrudRepository;

public interface TaxRepo extends CrudRepository<Tax, Long> {
}
