package service.math.impl;

import model.Category;
import org.springframework.stereotype.Service;
import service.math.BudgetCalculator;

import java.math.BigDecimal;

@Service
public class BudgetCalculatorImpl implements BudgetCalculator {

    private static final BigDecimal MONTHS_IN_ONE_YEAR = new BigDecimal(12);

    public BigDecimal calculateYearlyTotalForMonthlyBudget(Category category) {

        if (!category.isMonthlyBudget()) {
            throw new IllegalArgumentException("Cannot calculate total budget. Please provide a monthly budget.");
        }

        return category.getBudget().multiply(MONTHS_IN_ONE_YEAR);
    }
}
