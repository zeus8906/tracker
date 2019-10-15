package hu.home.sbv.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="rsu_transactions")
public class RsuSellTransaction {

    private Long id;

    @OneToMany
    private List<RsuSell> items;

    private BigDecimal transactionFee;

    private BigDecimal income;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RsuSell> getItems() {
        return items;
    }

    public void setItems(List<RsuSell> items) {
        this.items = items;
    }

    public BigDecimal getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(BigDecimal transactionFee) {
        this.transactionFee = transactionFee;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }
}
