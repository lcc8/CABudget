package service.math;

import model.Category;
import model.Expense;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ExpenseSavingCalculator {
    Map<String, BigDecimal> getTotalExpenseToEachCategory(List<Expense> expenses);
    Map<String, BigDecimal> getTotalSavingToEachCategory(List<Expense> expenses, Map<String, Category> categories);
}
