package com.solona.service.impl;

import com.solona.config.JwtProvider;
import com.solona.domain.AccountStatus;
import com.solona.exception.SellerException;
import com.solona.modal.Address;
import com.solona.modal.Seller;
import com.solona.repository.AddressRepository;
import com.solona.repository.SellerRepository;
import com.solona.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailfromJwtToken(jwt);
        //System.out.println(email);
        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {

        Seller sellerExists = sellerRepository.findByEmail(seller.getEmail());
        if(sellerExists!=null){
            throw new Exception("Seller already exists, Email is associated with existing account ... ");
        }
        Address savedAddress = addressRepository.save(seller.getPickupAddress());
        Seller newSeller = new Seller();
        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getEmail()));
        newSeller.setSellerName(seller.getSellerName());
        newSeller.setPickupAddress(seller.getPickupAddress());
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(seller.getRole());
        newSeller.setMobile(seller.getMobile());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setBusinessDetails(seller.getBusinessDetails());
        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws SellerException {

        return sellerRepository.findById(id)
                .orElseThrow(() -> new SellerException("Seller not found with Id - " +id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller = sellerRepository.findByEmail(email);
        if(seller == null){
            throw new Exception("Seller not found .. ");
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        //Seller existingSeller = sellerRepository.findById(id).orElseThrow(()->new SellerException("Seller not foudn with id "+ id));
//        Seller existingSeller = sellerRepository.findById(id);
        Seller Existingseller = this.getSellerById(id);

        if(seller.getSellerName() != null){
            Existingseller.setSellerName(seller.getSellerName());
        }
        if(seller.getMobile() != null){
            Existingseller.setMobile(seller.getMobile());
        }
        if(seller.getEmail() != null){
            Existingseller.setEmail(seller.getEmail());
        }
        if(seller.getBusinessDetails() != null && seller.getBusinessDetails().getBusinessName()
        != null){
            Existingseller.getBusinessDetails().setBusinessName(seller.getBusinessDetails().getBusinessName());
        }
        if(seller.getBusinessDetails() != null
            && seller.getBankDetails().getAccountHolderName() != null
            && seller.getBankDetails().getIfscCode()!=null
            && seller.getBankDetails().getAccountNumber()!= null){

            Existingseller.getBankDetails().setAccountHolderName(seller.getBankDetails().getAccountHolderName());
            Existingseller.getBankDetails().setAccountNumber(seller.getBankDetails().getAccountNumber());
            Existingseller.getBankDetails().setIfscCode(seller.getBankDetails().getIfscCode());

        }
        if(seller.getPickupAddress() != null
            && seller.getPickupAddress().getAddress() != null
            && seller.getPickupAddress().getMobile() != null
            && seller.getPickupAddress().getCity() != null
            && seller.getPickupAddress().getState() != null
            && seller.getPickupAddress().getPincode() !=null){

            Existingseller.getPickupAddress().setAddress(seller.getPickupAddress().getCity());
            Existingseller.getPickupAddress().setCity(seller.getPickupAddress().getState());
            Existingseller.getPickupAddress().setState(seller.getPickupAddress().getMobile());
            Existingseller.getPickupAddress().setMobile(seller.getPickupAddress().getMobile());
            Existingseller.getPickupAddress().setPincode(seller.getPickupAddress().getPincode());
        }
        if (seller.getGSTIN() != null){
            Existingseller.setGSTIN(seller.getGSTIN());
        }
        return sellerRepository.save(Existingseller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller = this.getSellerById(id);
        sellerRepository.delete(seller);
    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        Seller seller = getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSellerAccoutnStatus(Long sellerId, AccountStatus status) throws Exception {
        Seller seller = getSellerById(sellerId);
        seller.setAccountStatus(status);
        return sellerRepository.save(seller);
    }
}
