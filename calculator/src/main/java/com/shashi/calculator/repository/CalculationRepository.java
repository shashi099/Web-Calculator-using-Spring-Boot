package com.shashi.calculator.repository;

import java.util.List;
import com.shashi.calculator.model.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CalculationRepository
        extends JpaRepository<Calculation, Long> {


    /*Spring Data JPA understands the method name.
    // You didn't write that SQL yourself. Spring Data JPA generates the query.
       Derived Query Method -> Because Spring derives the query from the method name.
        Approach 1 — Derived Query
    */
    List<Calculation> findAllByOrderByIdDesc();
    //Show only multiplication calculations.
    List<Calculation> findByOperator(String operator);
    List<Calculation> findByResultGreaterThan(double result);
    //Search Between Results
    List<Calculation> findByResultBetween(
            double min,
            double max
    );

    /*
    Approach 2 — @Query
    We can explicitly write JPQL:

    Important: JPQL Is Not SQL
    This is extremely important for your Spring/Hibernate learning.
     */
    @Query("SELECT c FROM Calculation c WHERE c.operator = :operator")
    List<Calculation> findCalculationsByOperator(
            @Param("operator") String operator
    );
    }


//    JpaRepository
//            ↓
//    Built-in methods
//            ↓
//    Derived Query Methods
//            ↓
//     @Query / JPQL
//            ↓
//     Native SQL when actually necessary