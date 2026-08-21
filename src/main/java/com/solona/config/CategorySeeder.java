package com.solona.config;

import com.solona.modal.Category;
import com.solona.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {

        if (categoryRepository.count() > 0) {
            return;
        }

        // =========================
        // MEN
        // =========================

        Category men = createRoot("Men", 1);

        Category menTopwear = createChild(
                "Topwear",
                men,
                1
        );

        createChild("T-Shirts", menTopwear, 1);
        createChild("Shirts", menTopwear, 2);
        createChild("Hoodies", menTopwear, 3);

        Category menBottomwear = createChild(
                "Bottomwear",
                men,
                2
        );

        createChild("Jeans", menBottomwear, 1);
        createChild("Trousers", menBottomwear, 2);
        createChild("Shorts", menBottomwear, 3);

        Category menFootwear = createChild(
                "Footwear",
                men,
                3
        );

        createChild("Casual Shoes", menFootwear, 1);
        createChild("Sports Shoes", menFootwear, 2);
        createChild("Sandals", menFootwear, 3);


        // =========================
        // WOMEN
        // =========================

        Category women = createRoot("Women", 2);

        Category indianWear = createChild(
                "Indian Wear",
                women,
                1
        );

        createChild("Kurtas", indianWear, 1);
        createChild("Sarees", indianWear, 2);
        createChild("Lehengas", indianWear, 3);

        Category westernWear = createChild(
                "Western Wear",
                women,
                2
        );

        createChild("Dresses", westernWear, 1);
        createChild("Tops", westernWear, 2);
        createChild("Jeans", westernWear, 3);

        Category womenFootwear = createChild(
                "Footwear",
                women,
                3
        );

        createChild("Heels", womenFootwear, 1);
        createChild("Flats", womenFootwear, 2);
        createChild("Sneakers", womenFootwear, 3);


        // =========================
        // ELECTRONICS
        // =========================

        Category electronics =
                createRoot("Electronics", 3);

        Category mobiles = createChild(
                "Mobiles",
                electronics,
                1
        );

        createChild("Smartphones", mobiles, 1);
        createChild("Feature Phones", mobiles, 2);

        Category laptops = createChild(
                "Laptops",
                electronics,
                2
        );

        createChild("Gaming Laptops", laptops, 1);
        createChild("Ultrabooks", laptops, 2);

        Category accessories = createChild(
                "Accessories",
                electronics,
                3
        );

        createChild("Headphones", accessories, 1);
        createChild("Chargers", accessories, 2);
        createChild("Power Banks", accessories, 3);


        // =========================
        // HOME & FURNITURE
        // =========================

        Category homeFurniture =
                createRoot("Home & Furniture", 4);

        Category furniture = createChild(
                "Furniture",
                homeFurniture,
                1
        );

        createChild("Sofas", furniture, 1);
        createChild("Beds", furniture, 2);
        createChild("Tables", furniture, 3);

        Category homeDecor = createChild(
                "Home Decor",
                homeFurniture,
                2
        );

        createChild("Wall Decor", homeDecor, 1);
        createChild("Lamps", homeDecor, 2);
        createChild("Clocks", homeDecor, 3);

        Category kitchen = createChild(
                "Kitchen",
                homeFurniture,
                3
        );

        createChild("Cookware", kitchen, 1);
        createChild("Storage", kitchen, 2);
        createChild("Appliances", kitchen, 3);


        System.out.println(
                "===================================="
        );

        System.out.println(
                "Category seeding completed successfully"
        );

        System.out.println(
                "===================================="
        );
    }


    private Category createRoot(
            String name,
            int displayOrder
    ) {

        Category category = Category.builder()
                .name(name)
                .image(null)
                .enabled(true)
                .displayOrder(displayOrder)
                .parent(null)
                .build();

        return categoryRepository.save(category);
    }


    private Category createChild(
            String name,
            Category parent,
            int displayOrder
    ) {

        Category category = Category.builder()
                .name(name)
                .image(null)
                .enabled(true)
                .displayOrder(displayOrder)
                .parent(parent)
                .build();

        return categoryRepository.save(category);
    }
}