package ua.vbielslyi.amazon.model.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vbielslyi.amazon.util.ObjectUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Amountable {
    private Double amount;
    private String currencyCode;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
