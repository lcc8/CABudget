package service.display_service.impl;

import model.Length;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import subject.BudgetSubject;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BudgetServiceImplTest {

    @Mock
    private BudgetSubject budgetSubject;

    @InjectMocks
    private BudgetServiceImpl classToTest;

    @Test
    public void givenCategoryDetails_whenCreateACategory_thenCategoryCreated() {
        classToTest.createACategory("New", Length.MONTHLY, BigDecimal.ONE);
        assertEquals(1, classToTest.getCategories().size());
    }

    @Test
    public void givenCategoryName_whenRemoveACategory_thenCategoryRemoved() {
        classToTest.createACategory("New", Length.MONTHLY, BigDecimal.ONE);
        classToTest.removeACategory("New");
        assertEquals(0, classToTest.getCategories().size());
    }

    @Test
    public void givenNewBudget_whenUpdateBudget_thenCategoryNewBudget() {
        classToTest.createACategory("New", Length.MONTHLY, BigDecimal.ONE);
        classToTest.updateBudget("New", BigDecimal.TEN);
        assertEquals(BigDecimal.TEN, classToTest.getCategories().get("New").getBudget());
    }

    @Test
    public void givenTargetCategoryNotExist_whenUpdateBudget_thenNoUpdated(){
        classToTest.updateBudget("Unknown", BigDecimal.TEN);
        assertEquals(0, classToTest.getCategories().size());
    }

    @Test
    public void givenNewLength_whenUpdateLength_thenCategoryNewLength() {
        classToTest.createACategory("New", Length.MONTHLY, BigDecimal.ONE);
        classToTest.updateLength("New", Length.YEARLY);
        assertEquals(Length.YEARLY, classToTest.getCategories().get("New").getLength());
    }

    @Test
    public void givenTargetCategoryNotExist_whenUpdateLength_thenNoUpdated(){
        classToTest.updateLength("Unknown", Length.YEARLY);
        assertEquals(0, classToTest.getCategories().size());
    }
}