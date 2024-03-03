package ua.vbielslyi.amazon.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.vbielslyi.amazon.exception.ReportNotFoundException;
import ua.vbielslyi.amazon.model.SalesAndTrafficByAsin;
import ua.vbielslyi.amazon.model.SalesAndTrafficByDate;
import ua.vbielslyi.amazon.model.report.SellerRetailReport;
import ua.vbielslyi.amazon.repository.SellerRetailReportRepository;
import ua.vbielslyi.amazon.service.SellerRetailReportService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SellerRetailReportServiceImpl implements SellerRetailReportService {

    private static final String UPLOAD_DIR = "src/main/resources/json";
    private static final String DATA_FILE = "data.json";

    private final SellerRetailReportRepository sellerRetailReportRepository;
    private final ObjectMapper objectMapper;

    @Override
    @Cacheable(value = "salesAndTrafficDateSummary", key = "'dateSummary'")
    public List<SalesAndTrafficByDate> getSalesAndTrafficDateSummary() {
        SellerRetailReport sellerRetailReport = getFirstSellerRetailReport();
        return sellerRetailReport.getSalesAndTrafficByDate();
    }

    @Override
    @Cacheable(value = "salesAndTrafficByDate", key = "'date:' + #date.toString()")
    public SalesAndTrafficByDate getSalesAndTrafficByDate(LocalDate date) {
        SellerRetailReport sellerRetailReport = getFirstSellerRetailReport();
        return sellerRetailReport.getSalesAndTrafficByDate().stream()
                .filter(x -> x.getDate().equals(date))
                .findFirst()
                .orElseThrow(() -> new ReportNotFoundException("Report by date [" + date.toString() + "] not found"));
    }

    @Override
    @Cacheable(value = "salesAndTrafficByDateRange", key = "'dateRange:' + #from.toString() + '-' + #to.toString()")
    public List<SalesAndTrafficByDate> getSalesAndTrafficByDate(LocalDate from, LocalDate to) {
        SellerRetailReport sellerRetailReport = getFirstSellerRetailReport();
        return sellerRetailReport.getSalesAndTrafficByDate().stream()
                .filter(x -> (x.getDate().equals(from) || x.getDate().isAfter(from))
                        && (x.getDate().equals(to) || x.getDate().isBefore(to)))
                .toList();
    }

    @Override
    @Cacheable(value = "salesAndTrafficAsinSummary", key = "'asinSummary'")
    public List<SalesAndTrafficByAsin> getSalesAndTrafficAsinSummary() {
        SellerRetailReport sellerRetailReport = getFirstSellerRetailReport();
        return sellerRetailReport.getSalesAndTrafficByAsin();
    }

    @Override
    @Cacheable(value = "salesAndTrafficByAsin", key = "'asin:' + #asin.toString()")
    public List<SalesAndTrafficByAsin> getSalesAndTrafficByAsin(List<String> asin) {
        SellerRetailReport sellerRetailReport = getFirstSellerRetailReport();
        return sellerRetailReport.getSalesAndTrafficByAsin().stream()
                .filter(x -> asin.contains(x.getParentAsin()))
                .collect(Collectors.toList());
    }

    @Override
    @Scheduled(fixedRate = 3600000) // 1 hour
    public void sync() {
        try {
            String jsonContent = readJsonFile(DATA_FILE);
            SellerRetailReport report = objectMapper.readValue(jsonContent, SellerRetailReport.class);
            List<SellerRetailReport> dbReport = sellerRetailReportRepository.findAll();
            if (!dbReport.isEmpty() && dbReport.get(0).getId() != null) {
                report.setId(dbReport.get(0).getId());
                sellerRetailReportRepository.save(report);
            } else {
                sellerRetailReportRepository.save(report);
            }
            log.info("File synced [{}]", new Date());
        } catch (Exception e) {
            log.error("{}", e);
        }
    }

    @Override
    public void uploadDataFile(MultipartFile multipartFile) {
        Path uploadPath = Path.of(UPLOAD_DIR);
        try {
            Path filePath = uploadPath.resolve(DATA_FILE);
            Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            log.info("File updated [{}]", filePath);
        } catch (IOException e) {
            log.error("{}", e);
        }
    }

    private SellerRetailReport getFirstSellerRetailReport() {
        return sellerRetailReportRepository.findAll().stream()
                .findFirst()
                .orElseThrow(() -> new ReportNotFoundException("Not Found"));
    }

    private String readJsonFile(String fileName) throws IOException {
        Path filePath = Path.of(UPLOAD_DIR, fileName);
        return Files.readString(filePath);
    }
}
