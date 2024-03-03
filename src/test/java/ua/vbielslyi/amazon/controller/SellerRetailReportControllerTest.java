package ua.vbielslyi.amazon.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.vbielslyi.amazon.model.SalesAndTrafficByAsin;
import ua.vbielslyi.amazon.model.SalesAndTrafficByDate;
import ua.vbielslyi.amazon.service.SellerRetailReportService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SellerRetailReportControllerTest {

    @Mock
    private SellerRetailReportService sellerRetailReportService;

    @InjectMocks
    private SellerRetailReportController reportRestController;

    @Test
    void testGetSalesAndTrafficDateSummary() {
        List<SalesAndTrafficByDate> expectedResult = Arrays.asList(new SalesAndTrafficByDate());
        when(sellerRetailReportService.getSalesAndTrafficDateSummary()).thenReturn(expectedResult);

        ResponseEntity<List<SalesAndTrafficByDate>> responseEntity = reportRestController
                .getSalesAndTrafficDateSummary();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResult, responseEntity.getBody());
        verify(sellerRetailReportService, times(1)).getSalesAndTrafficDateSummary();
    }

    @Test
    void testGetSalesAndTrafficByDate() {
        LocalDate date = LocalDate.now();
        SalesAndTrafficByDate expectedResult = new SalesAndTrafficByDate();
        when(sellerRetailReportService.getSalesAndTrafficByDate(date)).thenReturn(expectedResult);

        ResponseEntity<SalesAndTrafficByDate> responseEntity = reportRestController.getSalesAndTrafficByDate(date);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResult, responseEntity.getBody());
        verify(sellerRetailReportService, times(1)).getSalesAndTrafficByDate(date);
    }

    @Test
    void testGetSalesAndTrafficByDateRange() {
        LocalDate from = LocalDate.now().minusDays(7);
        LocalDate to = LocalDate.now();
        List<SalesAndTrafficByDate> expectedResult = Arrays.asList(new SalesAndTrafficByDate());
        when(sellerRetailReportService.getSalesAndTrafficByDate(from, to)).thenReturn(expectedResult);

        ResponseEntity<List<SalesAndTrafficByDate>> responseEntity = reportRestController
                .getSalesAndTrafficByDateRange(from, to);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResult, responseEntity.getBody());
        verify(sellerRetailReportService, times(1)).getSalesAndTrafficByDate(from, to);
    }

    @Test
    void testGetSalesAndTrafficAsinSummary() {
        List<SalesAndTrafficByAsin> expectedResult = Arrays.asList(new SalesAndTrafficByAsin());
        when(sellerRetailReportService.getSalesAndTrafficAsinSummary()).thenReturn(expectedResult);

        ResponseEntity<List<SalesAndTrafficByAsin>> responseEntity = reportRestController
                .getSalesAndTrafficAsinSummary();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResult, responseEntity.getBody());
        verify(sellerRetailReportService, times(1)).getSalesAndTrafficAsinSummary();
    }

    @Test
    void testGetSalesAndTrafficByAsin() {
        List<String> asinList = Arrays.asList("ASIN1", "ASIN2");
        List<SalesAndTrafficByAsin> expectedResult = Arrays.asList(new SalesAndTrafficByAsin());
        when(sellerRetailReportService.getSalesAndTrafficByAsin(asinList)).thenReturn(expectedResult);

        ResponseEntity<List<SalesAndTrafficByAsin>> responseEntity = reportRestController
                .getSalesAndTrafficByAsin(asinList);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResult, responseEntity.getBody());
        verify(sellerRetailReportService, times(1)).getSalesAndTrafficByAsin(asinList);
    }
}
