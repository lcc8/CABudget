package service.math.impl;

import javafx.util.converter.BigDecimalStringConverter;
import lombok.extern.slf4j.Slf4j;
import model.Category;
import model.Expense;
import org.relaxng.datatype.DatatypeException;
import service.display_service.BudgetService;
import service.display_service.impl.BudgetServiceImpl;
import service.math.ExpenseSavingCalculator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class MonthlyExpenseSavingCalculatorImpl implements ExpenseSavingCalculator {
    private static BigDecimal MINUS_ONE = new BigDecimal(-1);

    public Map<String, BigDecimal> getTotalExpenseToEachCategory(List<Expense> expenses) {

        Map<String, BigDecimal> totalExpenseToEachCategory = new HashMap<String, BigDecimal>();

        for (Expense expense : expenses) {
            Category category = expense.getCategory();
            String categoryName = BudgetServiceImpl.UNKNOW_CATEGORY;

            if (category == null) {
                log.warn(expense.getName() + " does not have a category.");

            } else {
                categoryName = expense.getCategory().getName();
            }

            if (!totalExpenseToEachCategory.containsKey(categoryName)) {
                totalExpenseToEachCategory.put(categoryName, expense.getAmount());
            } else {
                totalExpenseToEachCategory.put(categoryName, addAmount(totalExpenseToEachCategory.get(categoryName), expense.getAmount()));
            }
        }

        return totalExpenseToEachCategory;
    }

    public Map<String, BigDecimal> getTotalSavingToEachCategory(List<Expense> expenses, Map<String, Category> categories) {
        Map<String, BigDecimal> totalExpense = getTotalExpenseToEachCategory(expenses);
        Map<String, BigDecimal> totalSavings = getSavingWithoutSpending(categories);
        for (String categoryName : totalExpense.keySet()) {
            if (!categories.containsKey(categoryName)) {
                categoryName = BudgetServiceImpl.UNKNOW_CATEGORY;
                totalSavings.put(BudgetServiceImpl.UNKNOW_CATEGORY, minusAmount(categories.get(categoryName).getBudget(), totalExpense.get(categoryName)));
            } else {
                totalSavings.put(categoryName, minusAmount(categories.get(categoryName).getBudget(), totalExpense.get(categoryName)));
            }
        }

        return totalSavings;
    }

    private Map<String, BigDecimal> getSavingWithoutSpending(Map<String, Category> categories){
        Map<String, BigDecimal> totalSavings = new HashMap<String, BigDecimal>();
        for(String categoryName : categories.keySet()){
            totalSavings.put(categoryName, categories.get(categoryName).getBudget());
        }

        return totalSavings;
    }

    private BigDecimal addAmount(BigDecimal originalAmount, BigDecimal newAmount) {
        return originalAmount.add(newAmount);
    }

    private BigDecimal minusAmount(BigDecimal budget, BigDecimal expense) {
        return budget.subtract(expense);
    }
}
