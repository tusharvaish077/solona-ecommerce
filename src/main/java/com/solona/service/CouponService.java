package com.solona.service;

import com.solona.modal.Cart;
import com.solona.modal.Coupon;
import com.solona.modal.User;
import java.util.*;

public interface CouponService {
    Cart applyCoupon(String code, double orderValue, User user) throws Exception;
    Cart removeCoupon(String code, User user) throws Exception;
    Coupon findCouponById(Long id) throws Exception;
    Coupon createCoupon(Coupon coupon);
    List<Coupon>findAllCoupons();
    void deleteCoupon(Long id) throws Exception;
}
