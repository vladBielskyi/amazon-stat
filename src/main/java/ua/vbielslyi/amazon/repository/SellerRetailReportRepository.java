package ua.vbielslyi.amazon.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.vbielslyi.amazon.model.report.SellerRetailReport;

public interface SellerRetailReportRepository extends MongoRepository<SellerRetailReport, String> {
}
