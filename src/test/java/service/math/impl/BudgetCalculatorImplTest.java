package service.math.impl;

import model.Category;
import model.Length;
import org.junit.Test;
import service.math.BudgetCalculator;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BudgetCalculatorImplTest {

    private BudgetCalculator classToTest = new BudgetCalculatorImpl();

    @Test
    public void givenMonthlyCategory_whenCalculateTotalBudget_thenBudgetCalculateCorrectly(){
        Category category = new Category("Test", Length.MONTHLY, BigDecimal.ONE);
        assertEquals(new BigDecimal(12), classToTest.calculateYearlyTotalForMonthlyBudget(category));
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenYearlyBudgetCategory_whenCalculateTotalBudget_thenException(){
        Category category = new Category("Test", Length.YEARLY, BigDecimal.ONE);
        classToTest.calculateYearlyTotalForMonthlyBudget(category);
    }

    @Test
    public void givenMonthlyBudgetWith0Budget_whenCalculateTotalBudget_thenBudgetCalculateCorrectly(){
        Category category = new Category("Test", Length.MONTHLY, BigDecimal.ZERO);
        assertEquals(new BigDecimal(0), classToTest.calculateYearlyTotalForMonthlyBudget(category));
    }

}