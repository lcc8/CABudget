package service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import model.Category;
import model.Expense;

public interface MonthlyService {
	void addAnExpense(String name, BigDecimal amount, Date expenseDate);
	void removeAnExpense(int index);
	void addExpenseToCategory(int index, String categoryName);
}
