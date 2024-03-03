package ua.vbielslyi.amazon.model.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ua.vbielslyi.amazon.model.ReportSpecification;
import ua.vbielslyi.amazon.model.SalesAndTrafficByAsin;
import ua.vbielslyi.amazon.model.SalesAndTrafficByDate;
import ua.vbielslyi.amazon.util.ObjectUtil;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "seller_retailer_reports")
public class SellerRetailReport {

    @Id
    private String id;

    private ReportSpecification reportSpecification;
    private List<SalesAndTrafficByDate> salesAndTrafficByDate;
    private List<SalesAndTrafficByAsin> salesAndTrafficByAsin;

    @Override
    public String toString() {
        return ObjectUtil.toJson(this);
    }
}
