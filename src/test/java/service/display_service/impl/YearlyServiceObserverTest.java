package service.display_service.impl;

import model.Category;
import model.Length;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.display_service.impl.YearlyServiceObserver;
import subject.BudgetSubject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class YearlyServiceObserverTest {

    @Mock
    BudgetSubject observable;

    @InjectMocks
    YearlyServiceObserver classToTest = new YearlyServiceObserver(null);

    private Map<String, Category> createCategoryMap(){
        Map<String, Category> categoryMap = new HashMap<String, Category>();
        Category category = new Category("New", Length.MONTHLY, BigDecimal.ONE);
        categoryMap.put("New", category);
        return categoryMap;
    }

    @Test
    public void givenACategory_whenUpdate_thenCategoryUpdated() {
        Map<String, Category> categoryMap = createCategoryMap();
        classToTest.update(observable, categoryMap);
        assertEquals(categoryMap, classToTest.getCategories());
    }

    @Test
    public void givenNullCategory_whenUpdate_thenCategoryUpdated(){
        classToTest.update(observable, null);
        assertNull(classToTest.getCategories());
    }
}