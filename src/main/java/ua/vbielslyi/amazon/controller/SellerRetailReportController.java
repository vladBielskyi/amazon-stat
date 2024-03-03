package ua.vbielslyi.amazon.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ua.vbielslyi.amazon.model.SalesAndTrafficByAsin;
import ua.vbielslyi.amazon.model.SalesAndTrafficByDate;
import ua.vbielslyi.amazon.service.SellerRetailReportService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class SellerRetailReportController {

    private final SellerRetailReportService sellerRetailReportService;

    @GetMapping("/date-summary")
    public ResponseEntity<List<SalesAndTrafficByDate>> getSalesAndTrafficDateSummary() {
        List<SalesAndTrafficByDate> result = sellerRetailReportService.getSalesAndTrafficDateSummary();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<SalesAndTrafficByDate> getSalesAndTrafficByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        SalesAndTrafficByDate result = sellerRetailReportService.getSalesAndTrafficByDate(date);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-date-range")
    public ResponseEntity<List<SalesAndTrafficByDate>> getSalesAndTrafficByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        List<SalesAndTrafficByDate> result = sellerRetailReportService.getSalesAndTrafficByDate(from, to);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/asin-summary")
    public ResponseEntity<List<SalesAndTrafficByAsin>> getSalesAndTrafficAsinSummary() {
        List<SalesAndTrafficByAsin> result = sellerRetailReportService.getSalesAndTrafficAsinSummary();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/by-asin")
    public ResponseEntity<List<SalesAndTrafficByAsin>> getSalesAndTrafficByAsin(@RequestParam List<String> asin) {
        List<SalesAndTrafficByAsin> result = sellerRetailReportService.getSalesAndTrafficByAsin(asin);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadDataFile(@RequestParam("file") MultipartFile file) {
        sellerRetailReportService.uploadDataFile(file);
        return ResponseEntity.ok().build();
    }
}
