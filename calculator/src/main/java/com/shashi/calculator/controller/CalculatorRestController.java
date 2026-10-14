package com.shashi.calculator.controller;

import com.shashi.calculator.dto.CalculatorRequest;
import com.shashi.calculator.model.Calculation;
import com.shashi.calculator.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CalculatorRestController {
    private final CalculatorService service;

    public CalculatorRestController(CalculatorService service){
        this.service = service;
    }
    @GetMapping("/api/hello")
    public String hello(){
        return "I am Don";
    }

    // Get all the calculations history
    @GetMapping("/api/history")
    public ResponseEntity<List<Calculation>> getAllCalculations(){

        List<Calculation> calculationList =
                service.getCalculationHistory();

        return ResponseEntity.ok(calculationList);
    }

    // GET API: Retrieve One Calculation by ID
    @GetMapping("/api/history/{id}")
    public ResponseEntity<Calculation> getCalculationById(@PathVariable Long id){

        Calculation calculation = service.getCalculationById(id);

        return ResponseEntity.ok(calculation);
    }

    // Create the Calculation
    @PostMapping("/api/calculation")
    public ResponseEntity<Calculation> calculateTask(
            @RequestBody
            CalculatorRequest request){

        Calculation calculation = service.calculate(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(calculation);
    }

    // Clear Calculation History
    @DeleteMapping("/api/history/clear")
    public ResponseEntity<Void> deleteAllHistory() {

        service.clearCalculationHistory();

        return ResponseEntity.noContent().build();
    }

    // Delete Calculation By Id
    @DeleteMapping("/api/history/{id}")
    public ResponseEntity<Void> deleteByHistoryId(
            @PathVariable Long id) {

        service.clearHistoryById(id);

        return ResponseEntity.noContent().build();
    }

    // Update an existing calculation
    @PutMapping("/api/history/{id}")
    public ResponseEntity<Calculation> updateCalculation(
            @PathVariable Long id,
            @RequestBody CalculatorRequest request) {

        Calculation updatedCalculation =
                service.updateCalculation(id, request);

        return ResponseEntity.ok(updatedCalculation);
    }



}
