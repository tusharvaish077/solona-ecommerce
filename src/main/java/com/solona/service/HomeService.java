package com.solona.service;

import com.solona.modal.Home;
import com.solona.modal.HomeCategory;

import java.util.List;

public interface HomeService {
    public Home createHomePageData(List<HomeCategory> allCategories);
}
