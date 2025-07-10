package com.solona.repository;

import com.solona.modal.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
    Coupon findByCode(String code);
}
