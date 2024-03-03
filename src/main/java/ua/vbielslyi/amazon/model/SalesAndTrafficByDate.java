package ua.vbielslyi.amazon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vbielslyi.amazon.util.ObjectUtil;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesAndTrafficByDate {
    private LocalDate date;
    private SalesByDate salesByDate;
    private TrafficByDate trafficByDate;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
