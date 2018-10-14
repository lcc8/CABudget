package service.display_service;

import java.math.BigDecimal;
import java.util.Date;

public interface MonthlyService {
	void addAnExpense(String name, BigDecimal amount, Date expenseDate);
	void removeAnExpense(int index);
	void addExpenseToCategory(int index, String categoryName);
}
