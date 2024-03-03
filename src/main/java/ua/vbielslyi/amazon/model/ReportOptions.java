package ua.vbielslyi.amazon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vbielslyi.amazon.util.ObjectUtil;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportOptions {
    private String dateGranularity;
    private String asinGranularity;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
