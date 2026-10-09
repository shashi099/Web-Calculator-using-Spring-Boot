package com.shashi.calculator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private double firstNumber;
    private double secondNumber;
    private String operator;
    private double result;

    public Calculation() {}

    public Calculation(
            double firstNumber,
            double secondNumber,
            String operator,
            double result
    ) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
        this.result = result;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Calculation{" +
                "id=" + id +
                ", firstNumber=" + firstNumber +
                ", secondNumber=" + secondNumber +
                ", operator='" + operator + '\'' +
                ", result=" + result +
                '}';
    }
}
