package hu.home.sbv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="rsus")
public class Rsu {

    @Id
    @GeneratedValue
    private long id;

    private Date settleDate;

    private int count;

    private BigDecimal value;

    private BigDecimal usdhuf;

    private boolean isTaxPaid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getSettleDate() {
        return settleDate;
    }

    public void setSettleDate(Date settleDate) {
        this.settleDate = settleDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getUsdhuf() {
        return usdhuf;
    }

    public void setUsdhuf(BigDecimal usdhuf) {
        this.usdhuf = usdhuf;
    }

    public boolean isTaxPayed() {
        return isTaxPaid;
    }

    public void setTaxPayed(boolean taxPaid) {
        isTaxPaid = taxPaid;
    }

    @Override
    public String toString() {
        return "Rsu{" +
                "id=" + id +
                ", settleDate=" + settleDate +
                ", count=" + count +
                ", value=" + value +
                ", usdhuf=" + usdhuf +
                ", isTaxPaid=" + isTaxPaid +
                '}';
    }
}
