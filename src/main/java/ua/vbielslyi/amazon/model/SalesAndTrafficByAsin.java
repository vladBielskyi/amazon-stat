package ua.vbielslyi.amazon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vbielslyi.amazon.util.ObjectUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesAndTrafficByAsin {
    private String parentAsin;
    private SalesByAsin salesByAsin;
    private TrafficByAsin trafficByAsin;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
