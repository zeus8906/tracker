package hu.home.sbv.repo;

import hu.home.sbv.model.Args;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.Collection;

@Repository
@Transactional
public interface ArgsRepo extends org.springframework.data.repository.Repository<Args, String> {

    @Query("SELECT value FROM args a WHERE a.key='TaxBase'")
    String getTaxBase();

    @Modifying
    @Query(value = "UPDATE args a SET a.value= ? WHERE a.key='TaxBase'", nativeQuery = true)
    void updateTaxBase(String newVal);

    @Modifying
    @Query(value = "INSERT INTO args(key, value) VALUES('TaxBase', :newVal)", nativeQuery = true)
    void createTaxBase(@Param("newVal") String newVal);
}
