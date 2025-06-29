package com.solona.service;

import com.solona.modal.Seller;
import com.solona.modal.SellerReport;

public interface SellerReportService {
    SellerReport getSellerReport(Seller seller);
    SellerReport updateSellerReport(SellerReport sellerReport);


}
