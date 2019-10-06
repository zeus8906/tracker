package hu.home.sbv.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@Entity
@Table(name="rsu_sells")
public class RsuSell {

    @GeneratedValue
    @Id
    private long id;

    @Column(name="count")
    private int count;

    @Column(name="usdhuf")
    private BigDecimal usdhuf;

    @Column(name="value")
    private BigDecimal value;

    @Column(name="sell_date")
    private Date sellDate;

    @ManyToOne
    private Rsu source;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getUsdhuf() {
        return usdhuf;
    }

    public void setUsdhuf(BigDecimal usdhuf) {
        this.usdhuf = usdhuf;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public Rsu getSource() {
        return source;
    }

    public void setSource(Rsu source) {
        this.source = source;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RsuSell{");
        sb.append("id=").append(id);
        sb.append(", count=").append(count);
        sb.append(", usdhuf=").append(usdhuf);
        sb.append(", value=").append(value);
        sb.append(", sellDate=").append(sellDate);
        sb.append(", source=").append(source);
        sb.append('}');
        return sb.toString();
    }
}
