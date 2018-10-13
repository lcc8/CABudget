package service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import model.Category;
import model.Expense;

public interface YearlyService {
	void addAnExpense(String name, BigDecimal amount, Date expenseDate);
	void removeAnExpense(String name);
	void addExpenseToCategory(Expense expense, String categoryName);
}
