package hu.home.sbv.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

public class SellTransactionRequest {
    private BigDecimal fee;
    private BigDecimal sellPrice;
    private BigDecimal usdhuf;
    private BigDecimal income;
    private Date sellDate;
    private Map<Integer, Integer> items;

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public BigDecimal getFee() {
        return this.fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }

    public BigDecimal getUsdhuf() {
        return usdhuf;
    }

    public void setUsdhuf(BigDecimal usdhuf) {
        this.usdhuf = usdhuf;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Map<Integer, Integer> items) {
        this.items = items;
    }

    public void setItems(int key, int value) {
        this.items.put(key, value);
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SellTransactionRequest{");
        sb.append("fee=").append(fee);
        sb.append(", sellPrice=").append(sellPrice);
        sb.append(", usdhuf=").append(usdhuf);
        sb.append(", income=").append(income);
        sb.append(", sellDate=").append(sellDate);
        sb.append(", items=").append(items);
        sb.append('}');
        return sb.toString();
    }
}
