package ua.vbielslyi.amazon.service;

import org.springframework.web.multipart.MultipartFile;
import ua.vbielslyi.amazon.model.SalesAndTrafficByAsin;
import ua.vbielslyi.amazon.model.SalesAndTrafficByDate;

import java.time.LocalDate;
import java.util.List;

public interface SellerRetailReportService {

    List<SalesAndTrafficByDate> getSalesAndTrafficDateSummary();

    SalesAndTrafficByDate getSalesAndTrafficByDate(LocalDate date);

    List<SalesAndTrafficByDate> getSalesAndTrafficByDate(LocalDate from, LocalDate to);

    List<SalesAndTrafficByAsin> getSalesAndTrafficAsinSummary();

    List<SalesAndTrafficByAsin> getSalesAndTrafficByAsin(List<String> asin);

    void sync();

    void uploadDataFile(MultipartFile multipartFile);

}
