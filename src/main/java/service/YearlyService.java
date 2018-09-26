package service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import categories.Category;
import expense.Expense;

public interface YearlyService {
	BigDecimal addAnExpense(String name, BigDecimal amount, Date expenseDate);
	void removeAnExpense(String name);
	void addExpenseToCategory(Expense expense, String categoryName);
	void createAnExpense(String name, BigDecimal amount, Date date);
	void updateCategory(List<Category> categories);
}
