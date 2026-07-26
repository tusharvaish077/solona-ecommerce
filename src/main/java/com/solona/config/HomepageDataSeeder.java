package com.solona.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solona.domain.SectionType;
import com.solona.modal.HomepageSection;
import com.solona.repository.HomepageSectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class HomepageDataSeeder {

    private final HomepageSectionRepository repository;
    private final ObjectMapper objectMapper;

    @Bean
    public CommandLineRunner seedHomepage() {

        return args -> {

            if (repository.count() > 0) {
                return;
            }

            repository.save(createSection(
                    SectionType.HERO_BANNER,
                    "Hero Banner",
                    1,
                    Map.of(
                            "desktopImage", "https://picsum.photos/1600/600",
                            "mobileImage", "https://picsum.photos/600/800",
                            "heading", "Mega Electronics Sale",
                            "subHeading", "Up to 70% OFF",
                            "buttonText", "Shop Now",
                            "buttonLink", "/products"
                    )
            ));

            repository.save(createSection(
                    SectionType.CATEGORY_SLIDER,
                    "Shop By Category",
                    2,
                    Map.of(
                            "title", "Shop By Category",
                            "maxCategories", 10
                    )
            ));

            repository.save(createSection(
                    SectionType.PRODUCT_CAROUSEL,
                    "Trending Products",
                    3,
                    Map.of(
                            "title", "Trending Products",
                            "category", "electronics",
                            "maxProducts", 10
                    )
            ));

            repository.save(createSection(
                    SectionType.PRODUCT_GRID,
                    "Featured Products",
                    4,
                    Map.of(
                            "title", "Featured Products",
                            "category", "fashion",
                            "columns", 4
                    )
            ));

            repository.save(createSection(
                    SectionType.PROMOTION_BANNER,
                    "Promotion Banner",
                    5,
                    Map.of(
                            "image", "https://picsum.photos/1200/300",
                            "redirectUrl", "/offers"
                    )
            ));

            repository.save(createSection(
                    SectionType.BRAND_SLIDER,
                    "Top Brands",
                    6,
                    Map.of(
                            "title", "Top Brands",
                            "speed", 3000
                    )
            ));

            repository.save(createSection(
                    SectionType.FLASH_SALE,
                    "Flash Sale",
                    7,
                    Map.of(
                            "title", "Flash Sale",
                            "durationHours", 24
                    )
            ));

            repository.save(createSection(
                    SectionType.OFFER_STRIP,
                    "Offer Strip",
                    8,
                    Map.of(
                            "text", "Free Shipping on Orders Above ₹999"
                    )
            ));

            repository.save(createSection(
                    SectionType.SELLER_BANNER,
                    "Become a Seller",
                    9,
                    Map.of(
                            "image", "https://picsum.photos/1200/350",
                            "buttonText", "Start Selling",
                            "buttonLink", "/seller/register"
                    )
            ));

            repository.save(createSection(
                    SectionType.RECENTLY_VIEWED,
                    "Recently Viewed",
                    10,
                    Map.of(
                            "maxProducts", 10
                    )
            ));

            System.out.println("Homepage data seeded successfully.");

        };
    }

    private HomepageSection createSection(
            SectionType sectionType,
            String title,
            Integer displayOrder,
            Map<String, Object> config
    ) {

        HomepageSection section = new HomepageSection();

        section.setSectionType(sectionType);
        section.setTitle(title);
        section.setDisplayOrder(displayOrder);
        section.setEnabled(true);
        section.setConfig(json(config));

        return section;
    }

    private JsonNode json(Map<String, Object> map) {
        return objectMapper.valueToTree(map);
    }

}