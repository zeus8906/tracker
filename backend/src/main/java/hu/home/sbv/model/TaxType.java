package hu.home.sbv.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="tax_types")
public class TaxType {

    @GeneratedValue
    @Id
    private long id;

    @Column(name="name")
    private String name;


    @Column(name="percentage")
    private BigDecimal percentage;

    @Column(name="on_sell")
    private boolean onSell;

    public boolean isOnSell() {
        return onSell;
    }

    public void setOnSell(boolean onSell) {
        this.onSell = onSell;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("TaxType{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", percentage=").append(percentage);
        sb.append(", onSell=").append(onSell);
        sb.append('}');
        return sb.toString();
    }
}
