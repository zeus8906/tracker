package hu.home.sbv.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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

    private boolean isSoldOut;

    @OneToMany(mappedBy = "source")
    private List<RsuSell> sells;

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

    public boolean isTaxPaid() {
        return isTaxPaid;
    }

    public void setTaxPaid(boolean taxPaid) {
        isTaxPaid = taxPaid;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Rsu{");
        sb.append("id=").append(id);
        sb.append(", settleDate=").append(settleDate);
        sb.append(", count=").append(count);
        sb.append(", value=").append(value);
        sb.append(", usdhuf=").append(usdhuf);
        sb.append(", isTaxPaid=").append(isTaxPaid);
        sb.append(", isSoldOut=").append(isSoldOut);
        sb.append(", sells=").append(sells);
        sb.append('}');
        return sb.toString();
    }
}
