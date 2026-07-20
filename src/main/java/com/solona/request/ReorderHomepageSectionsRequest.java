package com.solona.request;

import lombok.Data;

import java.util.List;

@Data
public class ReorderHomepageSectionsRequest {

    private List<Long> orderedSectionIds;

}