package hu.home.sbv.repo;

import hu.home.sbv.model.Tax;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaxRepo extends CrudRepository<Tax, Long> {

    @Query("SELECT t FROM Tax t WHERE t.rsu=:rsuId")
    public List<Tax> findByRsuId(@Param("rsuId") long rsuId);
}
