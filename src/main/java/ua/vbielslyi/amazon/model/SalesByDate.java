package ua.vbielslyi.amazon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vbielslyi.amazon.util.ObjectUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesByDate {
    private OrderedProductSales orderedProductSales;
    private OrderedProductSales orderedProductSalesB2B;
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;
    private AverageSalesPerOrderItem averageSalesPerOrderItem;
    private AverageSalesPerOrderItem averageSalesPerOrderItemB2B;
    private Double averageUnitsPerOrderItem;
    private Double averageUnitsPerOrderItemB2B;
    private AverageSellingPrice averageSellingPrice;
    private AverageSellingPrice averageSellingPriceB2B;
    private Integer unitsRefunded;
    private Double refundRate;
    private Integer claimsGranted;
    private ClaimsAmount claimsAmount;
    private ShippedProductSales shippedProductSales;
    private Integer unitsShipped;
    private Integer ordersShipped;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
