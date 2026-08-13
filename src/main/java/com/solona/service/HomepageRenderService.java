package com.solona.service;

import com.solona.dto.HomepageSectionResponse;

import java.util.List;

public interface HomepageRenderService {

    List<HomepageSectionResponse> buildHomepage();

}