package com.shashi.calculator.controller;

import com.shashi.calculator.dto.CalculatorRequest;
import com.shashi.calculator.exception.CalculatorException;
import com.shashi.calculator.model.Calculation;
import com.shashi.calculator.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
public class CalculatorController {

    public final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    /*
                    GET /calculator
                        ↓
                    "Give me the calculator page."

                    POST /calculator
                            ↓
                    "Here is my calculator data. Process it."
         */
    @GetMapping("/calculator")
    public String showCalculator() {

        return "calculator";
    }

    @PostMapping("/calculator")
    public String calculate(@ModelAttribute CalculatorRequest request,
                            Model model){

        System.out.println("in post mapp");

        try{
            Calculation calculation = calculatorService.calculate(request);

            System.out.println(calculation.getResult());
            model.addAttribute("result", calculation.getResult());

        }catch (CalculatorException e){

            model.addAttribute("error", e.getMessage());

        }

        return "calculator";
    }

    @GetMapping("/history")
    public String showHistory(Model model) {

        List<Calculation> calculations =
                calculatorService.getCalculationHistory();

        System.out.println(calculations);

        model.addAttribute(
                "calculations",
                calculations
        );

        return "history";
    }

    @PostMapping("/history/clear")
    public String clearHistory(){

        calculatorService.clearCalculationHistory();

        return "redirect:/history";
    }

    @PostMapping("/history/delete/{id}")
    public String clearHistoryById(@PathVariable Long id){

        calculatorService.clearHistoryById(id);

        return "redirect:/history";
    }

//    Edit Functionality
    @GetMapping("/history/edit/{id}")
    public String showEditPage(
            @PathVariable Long id,
            Model model) {

        Calculation calculation =
                calculatorService.getCalculationById(id);

        model.addAttribute("calculation", calculation);

        return "edit-calculation";
    }

     @PostMapping("/history/edit/{id}")
     public String updatedCalculation(
             @PathVariable Long id,
             @ModelAttribute CalculatorRequest request){

        Calculation calculation = calculatorService.updateCalculation(id, request);
        System.out.println(calculation);
        return "redirect:/history";
     }

}
