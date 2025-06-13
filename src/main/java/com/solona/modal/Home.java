package com.solona.modal;

import lombok.Data;
import java.util.*;

@Data
public class Home {
    private List<HomeCategory> grid;

    private List<HomeCategory> shopByCategories;

    private List<HomeCategory> electrricCategories;

    private List<HomeCategory> dealCategories;

    private List<Deal> deals;
}