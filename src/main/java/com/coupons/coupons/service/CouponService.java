package com.coupons.coupons.service;

import com.coupons.coupons.model.Coupon;
import com.coupons.coupons.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    public List<Coupon> findAllCoupons() {
        return couponRepository.findAll();
    }

    public Coupon saveCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    public Optional<Coupon> findById(Long id) {
        return couponRepository.findById(id);
    }

    public Coupon updateCoupon(Long id, Coupon newCoupon) {
        return couponRepository.findById(id)
            .map(coupon -> {
                coupon.setName(newCoupon.getName());
                coupon.setDescription(newCoupon.getDescription());
                coupon.setExpirationDate(newCoupon.getExpirationDate());
                coupon.setStatus(newCoupon.getStatus());
                coupon.setCompanyId(newCoupon.getCompanyId());
                return couponRepository.save(coupon);
            })
            .orElseGet(() -> {
                newCoupon.setId(id);
                return couponRepository.save(newCoupon);
            });
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }
}
