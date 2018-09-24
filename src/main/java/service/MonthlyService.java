package service;

import java.math.BigDecimal;
import java.util.Date;

import expense.Expense;

public interface MonthlyService {
	BigDecimal addAnExpense(String name, BigDecimal amount, Date expenseDate);
	void removeAnExpense(String name);
	void addExpenseToCategory(Expense expense, String categoryName);
}
