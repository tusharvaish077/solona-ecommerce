package com.solona.repository;

import com.solona.domain.SectionType;
import com.solona.modal.HomepageSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HomepageSectionRepository extends JpaRepository<HomepageSection, Long> {
    List<HomepageSection> findByEnabledTrueOrderByDisplayOrderAsc();

    List<HomepageSection> findAllByOrderByDisplayOrderAsc();

    List<HomepageSection> findBySectionType(SectionType sectionType);
}
