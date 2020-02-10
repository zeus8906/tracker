package hu.home.sbv.model;

import java.util.List;

public class SellResponseDto {

    private boolean success;
    private List<Tax> taxesForSells;
    private List<RsuSell> sellItems;

    public List<RsuSell> getSellItems() {
        return sellItems;
    }

    public void setSellItems(List<RsuSell> sellItems) {
        this.sellItems = sellItems;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Tax> getTaxesForSells() {
        return taxesForSells;
    }

    public void setTaxesForSells(List<Tax> taxesForSells) {
        this.taxesForSells = taxesForSells;
    }
}
