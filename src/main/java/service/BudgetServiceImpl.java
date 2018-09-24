package service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;

import categories.Category;
import categories.Length;

@Service
public class BudgetServiceImpl implements BudgetService {
	
	private Map<String, Category> categories;

	public Category createACategory(String name, Length length, BigDecimal budget) {
		Category category = new Category(name, length, budget);
		return categories.put(name, category);
	}

	public void removeACategory(String name) {
		categories.remove(name);
	}

	public void updateBudget(String name, BigDecimal amount) {
		Category category = categories.get(name);
		category.setBudget(amount);
		categories.put(name, category);
	}
	
	public void updateLength(String name, Length length) {
		Category category = categories.get(name);
		category.setLength(length);;
		categories.put(name, category);
	}
}
