package com.solona.Controller;

import com.solona.modal.Deal;
import com.solona.response.ApiResponse;
import com.solona.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/deals")
public class DealController {
    private final DealService dealService;

    @GetMapping
    public ResponseEntity<List<Deal>> getDeals(

    ){
        List<Deal> deals=dealService.getDeals();

        return new ResponseEntity<>(deals, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<Deal> createDeal(
            @RequestBody Deal deals
    ){
        Deal createDeal = dealService.createDeal(deals);
        return new ResponseEntity<>(createDeal, HttpStatus.ACCEPTED);
    }

    @PatchMapping("?{id}")
    public ResponseEntity<Deal> updateDeal(
            @PathVariable Long id,@RequestBody Deal deal
    ) throws Exception{
        Deal updatedDeal = dealService.updateDeal(deal, id);
        return ResponseEntity.ok(updatedDeal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDeal(
            @PathVariable Long id
    ) throws Exception{
        dealService.deleteDeal(id);
        ApiResponse apiResponse= new ApiResponse();
        apiResponse.setMessage("Deal Deleted");

        return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
    }
}
