package service.math;

import model.Category;

import java.math.BigDecimal;
import java.util.List;

public interface BudgetCalculator {
    BigDecimal calculateYearlyTotalForMonthlyBudget(Category category);
}
