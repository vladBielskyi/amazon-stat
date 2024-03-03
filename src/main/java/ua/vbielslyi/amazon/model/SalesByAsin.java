package ua.vbielslyi.amazon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vbielslyi.amazon.util.ObjectUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesByAsin {
    private Integer unitsOrdered;
    private Integer unitsOrderedB2B;
    private OrderedProductSales orderedProductSales;
    private OrderedProductSales orderedProductSalesB2B;
    private Integer totalOrderItems;
    private Integer totalOrderItemsB2B;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
