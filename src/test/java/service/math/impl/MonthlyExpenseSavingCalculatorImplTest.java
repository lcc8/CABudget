package service.math.impl;

import model.Category;
import model.Expense;
import model.Length;
import org.junit.Test;
import service.display_service.impl.BudgetServiceImpl;
import service.math.ExpenseSavingCalculator;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

public class MonthlyExpenseSavingCalculatorImplTest {

    private ExpenseSavingCalculator classToTest = new MonthlyExpenseSavingCalculatorImpl();
    private static final Category CATEGORY_MONTHLY = new Category("C1", Length.MONTHLY, BigDecimal.TEN);
    private static final Category CATEGORY_YEARLY = new Category("C2", Length.YEARLY, BigDecimal.ONE);
    private static final Category DEFAULT_CATEGORY = new Category(BudgetServiceImpl.UNKNOW_CATEGORY, Length.MONTHLY, BigDecimal.ZERO);


    @Test
    public void givenExpenses_whenGetTotalExpenseToEachCategory_thenTotalExpenseCalculated() {
        Map<String, BigDecimal> expectedResult = new HashMap<String, BigDecimal>();
        expectedResult.put("C1", new BigDecimal(11));
        expectedResult.put("C2", BigDecimal.ZERO);
        assertEquals(expectedResult, classToTest.getTotalExpenseToEachCategory(getExpensesWithCategory()));
    }

    @Test
    public void givenExpensesNoCategory_whenGetTotalExpenseToEachCategory_thenTotalExpenseCalculated(){
        Map<String, BigDecimal> expectedResult = new HashMap<String, BigDecimal>();
        expectedResult.put("unknown", new BigDecimal(11));
        expectedResult.put("unknown", BigDecimal.ZERO);
        classToTest.getTotalExpenseToEachCategory(getExpensesWithNoCategory());
    }

    @Test
    public void givenNoExpenses_whenGetTotalExpenseToEachCategory_thenTotalExpenseCalculated(){
        assertEquals(0, classToTest.getTotalExpenseToEachCategory(new ArrayList<Expense>()).size());
    }

    @Test
    public void givenExpenseAndCategories_whenGetTotalSavingToEachCategory_thenTotalSavingCalculated() {
        Map<String, BigDecimal> expectedResult = new HashMap<String, BigDecimal>();
        expectedResult.put("C1", new BigDecimal(-1));
        expectedResult.put("C2", BigDecimal.ONE);
        expectedResult.put(BudgetServiceImpl.UNKNOW_CATEGORY, BigDecimal.ZERO);
        assertEquals(expectedResult, classToTest.getTotalSavingToEachCategory(getExpensesWithCategory(), getCategories()));
    }

    @Test
    public void givenNoExpenseAndCategories_whenGetTotalSavingToEachCategory_thenTotalSavingCalculated() {
        Map<String, BigDecimal> expectedResult = new HashMap<String, BigDecimal>();
        expectedResult.put("C1", BigDecimal.TEN);
        expectedResult.put("C2", BigDecimal.ONE);
        expectedResult.put(BudgetServiceImpl.UNKNOW_CATEGORY, new BigDecimal(-11));
        assertEquals(expectedResult, classToTest.getTotalSavingToEachCategory(getExpensesWithNoCategory(), getCategories()));
    }

    private Map<String, Category> getCategories(){
        Map<String, Category> categories = new HashMap<String, Category>();
        categories.put("C1", CATEGORY_MONTHLY);
        categories.put("C2", CATEGORY_YEARLY);
        categories.put(BudgetServiceImpl.UNKNOW_CATEGORY, DEFAULT_CATEGORY);
        return categories;
    }

    private List<Expense> getExpensesWithCategory(){
        List<Expense> expenses = new ArrayList<Expense>();
        Expense expense = new Expense("Test", BigDecimal.ONE, new Date());
        expense.setCategory(CATEGORY_MONTHLY);
        Expense expense1 = new Expense("Test1", BigDecimal.TEN, new Date());
        expense1.setCategory(CATEGORY_MONTHLY);
        Expense expense2 = new Expense("Test2", BigDecimal.ZERO, new Date());
        expense2.setCategory(CATEGORY_YEARLY);
        expenses.add(expense);
        expenses.add(expense1);
        expenses.add(expense2);

        return expenses;
    }

    private List<Expense> getExpensesWithNoCategory(){
        List<Expense> expenses = new ArrayList<Expense>();
        Expense expense = new Expense("Test", BigDecimal.ONE, new Date());
        Expense expense1 = new Expense("Test1", BigDecimal.TEN, new Date());
        Expense expense2 = new Expense("Test2", BigDecimal.ZERO, new Date());
        expenses.add(expense);
        expenses.add(expense1);
        expenses.add(expense2);

        return expenses;
    }
}