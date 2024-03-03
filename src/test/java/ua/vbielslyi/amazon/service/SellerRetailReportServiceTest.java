package ua.vbielslyi.amazon.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.vbielslyi.amazon.model.SalesAndTrafficByAsin;
import ua.vbielslyi.amazon.model.SalesAndTrafficByDate;
import ua.vbielslyi.amazon.model.report.SellerRetailReport;
import ua.vbielslyi.amazon.repository.SellerRetailReportRepository;
import ua.vbielslyi.amazon.service.impl.SellerRetailReportServiceImpl;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SellerRetailReportServiceTest {

    @InjectMocks
    private SellerRetailReportServiceImpl sellerRetailReportService;

    @Mock
    private SellerRetailReportRepository sellerRetailReportRepository;

    @Test
    void getSalesAndTrafficDateSummaryTest() {
        SellerRetailReport mockReport = new SellerRetailReport();
        mockReport.setSalesAndTrafficByDate(Collections.emptyList());
        when(sellerRetailReportRepository.findAll()).thenReturn(Collections.singletonList(mockReport));

        List<SalesAndTrafficByDate> result = sellerRetailReportService.getSalesAndTrafficDateSummary();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getSalesAndTrafficAsinSummaryTest() {
        SellerRetailReport mockReport = new SellerRetailReport();
        mockReport.setSalesAndTrafficByAsin(Collections.emptyList());
        when(sellerRetailReportRepository.findAll()).thenReturn(Collections.singletonList(mockReport));

        List<SalesAndTrafficByAsin> result = sellerRetailReportService.getSalesAndTrafficAsinSummary();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
