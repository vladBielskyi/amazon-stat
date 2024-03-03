package ua.vbielslyi.amazon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.vbielslyi.amazon.util.ObjectUtil;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportSpecification {
    private String reportType;
    private ReportOptions reportOptions;
    private LocalDate dataStartTime;
    private LocalDate dataEndTime;
    private List<String> marketplaceIds;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
