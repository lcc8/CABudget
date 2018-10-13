package service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import model.Category;
import model.Length;
import service.BudgetService;

@Service
@Getter
@Slf4j
public class BudgetServiceImpl implements BudgetService {
	
	private Map<String, Category> categories;

	public BudgetServiceImpl() {
		categories = new HashMap<String, Category>();
	}

	public Category createACategory(String name, Length length, BigDecimal budget) {
		Category category = new Category(name, length, budget);
		log.info("Creating new category with name=" + name + " length=" + length + " budget=" + budget.toString());
		return categories.put(name, category);
	}

	public void removeACategory(String name) {
		categories.remove(name);
		log.info("Removed category " + name);
	}

	public void updateBudget(String name, BigDecimal amount) {
		Category category = categories.get(name);
		if(category == null){
			log.warn("Unable to update budget category " + name + " because it does not exist.");
			return;
		}
		category.setBudget(amount);
		categories.put(name, category);
	}
	
	public void updateLength(String name, Length length) {
		Category category = categories.get(name);
		if(category == null){
			log.warn("Unable to update length category " + name + " because it does not exist.");
			return;
		}
		category.setLength(length);
		categories.put(name, category);
	}
}
