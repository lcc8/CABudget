package service.impl;

import model.Category;
import model.Expense;
import model.Length;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import subject.BudgetSubject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MonthlyServiceObserverTest {

    @Mock
    BudgetSubject observable;

    @InjectMocks
    MonthlyServiceObserver classToTest = new MonthlyServiceObserver(null);

    private Map<String, Category> createCategoryMap(){
        Map<String, Category> categoryMap = new HashMap<String, Category>();
        Category category = new Category("New", Length.MONTHLY, BigDecimal.ONE);
        categoryMap.put("New", category);
        return categoryMap;
    }

    @Test
    public void givenACategory_whenUpdate_thenCategoryUpdated() {
        Map<String, Category> categoryMap = createCategoryMap();
        when(observable.getAllCategories()).thenReturn(categoryMap);
        classToTest.update(observable, null);
        assertEquals(categoryMap, classToTest.getCategories());
    }

    @Test
    public void givenNullCategory_whenUpdate_thenCategoryUpdated(){
        when(observable.getAllCategories()).thenReturn(null);
        classToTest.update(observable, null);
        assertNull(classToTest.getCategories());
    }

    @Test
    public void whenGivenExpenseDetails_whenAddAnExpense_thenExpenseAdded() {
        classToTest.addAnExpense("Expense", BigDecimal.ONE, new Date());
        assertEquals(1, classToTest.getExpenses().size());
    }

    @Test
    public void whenGivenIndex_whenRemoveAnExpense_thenRemoveExpense() {
        List<Expense> expenses = classToTest.getExpenses();
        classToTest.addAnExpense("Expense", BigDecimal.ONE, new Date());
        classToTest.removeAnExpense(0);
        assertEquals( 0, classToTest.getExpenses().size());
    }

    @Test
    public void givenCategory_whenAddExpenseToCategory_thenCategoryAdded() {
        Map<String, Category> categoryMap = createCategoryMap();
        when(observable.getAllCategories()).thenReturn(categoryMap);
        classToTest.update(observable, null);
        classToTest.addAnExpense("Expense", BigDecimal.ONE, new Date());
        classToTest.addExpenseToCategory(0, "New");
        List<Expense> expenses = classToTest.getExpenses();
        assertEquals(categoryMap.get("New"), expenses.get(0).getCategory());
    }
}