package com.maids.springbootquiz.controller;

import com.maids.springbootquiz.exception.ResourceNotFoundException;
import com.maids.springbootquiz.model.Sale;
import com.maids.springbootquiz.model.SaleItem;
import com.maids.springbootquiz.repository.SaleItemRepository;
import com.maids.springbootquiz.repository.SaleRepository;
import com.maids.springbootquiz.response.CustomResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.maids.springbootquiz.response.CustomResponseHelper.failureResponse;
import static com.maids.springbootquiz.response.CustomResponseHelper.successResponse;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final static Logger logger = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @GetMapping("")
    public CustomResponse<List<Sale>> getAllSales()
    {
        CustomResponse<List<Sale>> listCustomResponse;
        try {
            listCustomResponse = successResponse(saleRepository.findAll());
        }
        catch (Exception e){
            listCustomResponse = failureResponse(e.getMessage());
        }
        return listCustomResponse;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable(value = "id") Long saleId)
            throws ResourceNotFoundException {
        Sale sale =
                saleRepository
                        .findById(saleId)
                        .orElseThrow(() -> new ResourceNotFoundException("Sale not found on :: " + saleId));
        return ResponseEntity.ok().body(sale);
    }

    @PostMapping("")
    public CustomResponse<Sale> createSale(@Valid @RequestBody Sale sale) {
        try {
            List<SaleItem> temp = sale.getSaleItem();
            sale.setSaleItem(new ArrayList<>());
            sale = saleRepository.save(sale);
            for (SaleItem saleItem : temp) {
                saleItem.setSale(sale);
                saleItemRepository.save(saleItem);
            }
            logger.info(String.format("%s created", sale));
            return successResponse(sale);
        }
        catch (Exception e){
            return failureResponse(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public CustomResponse<Sale> updateSale(
            @PathVariable(value = "id") Long saleId, @Valid @RequestBody Sale saleDetails){
        try {
            List<SaleItem> updatedSaleItems = new ArrayList<>();
            for (SaleItem saleItem : saleDetails.getSaleItem()) {
                SaleItem newSaleItem =
                        saleItemRepository
                                .findById(saleItem.getId())
                                .orElseThrow(() -> new ResourceNotFoundException("Sale Item not found on :: " + saleItem.getId()));

                newSaleItem.setQuantity(saleItem.getQuantity());
                final SaleItem updatedSaleItem = saleItemRepository.save(newSaleItem);
                updatedSaleItems.add(updatedSaleItem);
            }
            Sale newSale =
                    saleRepository
                            .findById(saleId)
                            .orElseThrow(() -> new ResourceNotFoundException("Sale not found on :: " + saleId));

            newSale.setSaleItem(updatedSaleItems);
            final Sale updatedSale = saleRepository.save(newSale);
            logger.info(String.format("%s updated", updatedSale));
            return successResponse(updatedSale);
        }
        catch (Exception e){
            return failureResponse(e.getMessage());
        }
    }
}
