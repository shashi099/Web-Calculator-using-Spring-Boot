package com.shashi.calculator.service;

import com.shashi.calculator.dto.CalculatorRequest;
import com.shashi.calculator.exception.CalculatorException;
import com.shashi.calculator.model.Calculation;
import com.shashi.calculator.repository.CalculationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CalculatorService {
    private final CalculationRepository calculationRepository;

    public CalculatorService(CalculationRepository calculationRepository){
        this.calculationRepository = calculationRepository;
    }
    public Calculation calculate(CalculatorRequest request) {

        double firstNumber = request.getFirstNumber();
        double secondNumber = request.getSecondNumber();
        String operator = request.getOperator();

        double result;

        switch (operator) {

            case "+":
                result = firstNumber + secondNumber;
                break;

            case "-":
                result = firstNumber - secondNumber;
                break;

            case "*":
                result = firstNumber * secondNumber;
                break;

            case "/":

                if (secondNumber == 0) {
                    throw new CalculatorException(
                            "Cannot divide by zero"
                    );
                }

                result = firstNumber / secondNumber;
                break;

            case "%":

                if (secondNumber == 0) {
                    throw new CalculatorException(
                            "Cannot calculate modulus with zero"
                    );
                }

                result = firstNumber % secondNumber;
                break;

            default:
                throw new CalculatorException(
                        "Invalid operator"
                );
        }

        Calculation calculation = new Calculation(firstNumber, secondNumber, operator, result);

        // save the data into the postgresql
        calculationRepository.save(calculation);

        return calculation;
    }

    public List<Calculation> getCalculationHistory() {

        return calculationRepository.findAll();
    }

//    Delete the calculation history
    public void clearCalculationHistory(){

        calculationRepository.deleteAll();

    }

    // Delete the single history
    public void clearHistoryById(Long id){

        calculationRepository.deleteById(id);
    }

    // Edit the Calculation
    public Calculation getCalculationById(Long id) {

        return calculationRepository.findById(id)
                .orElseThrow(() ->
                        new CalculatorException("Calculation not found"));
    }

    public Calculation updateCalculation(Long id, CalculatorRequest request){

        Calculation calculation = getCalculationById(id);

        double firstNumber = request.getFirstNumber();
        double secondNumber = request.getSecondNumber();
        String operator = request.getOperator();

        double result;

        switch (operator) {

            case "+":
                result = firstNumber + secondNumber;
                break;

            case "-":
                result = firstNumber - secondNumber;
                break;

            case "*":
                result = firstNumber * secondNumber;
                break;

            case "/":

                if (secondNumber == 0) {
                    throw new CalculatorException(
                            "Cannot divide by zero"
                    );
                }

                result = firstNumber / secondNumber;
                break;

            case "%":

                if (secondNumber == 0) {
                    throw new CalculatorException(
                            "Cannot calculate modulus with zero"
                    );
                }

                result = firstNumber % secondNumber;
                break;

            default:
                throw new CalculatorException(
                        "Invalid operator"
                );
        }

        calculation.setFirstNumber(firstNumber);
        calculation.setSecondNumber(secondNumber);
        calculation.setOperator(operator);
        calculation.setResult(result);

        calculationRepository.save(calculation);
        return calculation;
    }

}