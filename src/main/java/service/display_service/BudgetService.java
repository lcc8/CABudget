package service.display_service;

import java.math.BigDecimal;

import model.Length;

public interface BudgetService {
	void createACategory(String name, Length length, BigDecimal budget);
	void removeACategory(String name);
	void updateBudget(String name, BigDecimal amount);
	void updateLength(String name, Length length);
}
