package service.display_service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Category;
import model.Length;
import service.display_service.BudgetService;
import subject.BudgetSubject;

@Service
@Getter
@Slf4j
public class BudgetServiceImpl implements BudgetService {
	
	private Map<String, Category> categories;
	public static final String UNKNOW_CATEGORY = "unknown";

	@Autowired
	private BudgetSubject budgetSubject;

	public BudgetServiceImpl() {
		categories = new HashMap<String, Category>();
		// add a default category
		categories.put(UNKNOW_CATEGORY, new Category(UNKNOW_CATEGORY, Length.MONTHLY, BigDecimal.ZERO));
	}

	public void createACategory(String name, Length length, BigDecimal budget) {
		Category category = new Category(name, length, budget);
		categories.put(name, category);
		log.info("Created new category with name=" + name + " length=" + length + " budget=" + budget.toString());
		budgetSubject.setCategories(categories);
	}

	public void removeACategory(String name) {
		categories.remove(name);
		log.info("Removed category " + name);
		budgetSubject.setCategories(categories);
	}

	public void updateBudget(String name, BigDecimal amount) {
		Category category = categories.get(name);
		if(category == null){
			log.warn("Unable to update budget category " + name + " because it does not exist.");
			return;
		}
		category.setBudget(amount);
		categories.put(name, category);
		budgetSubject.setCategories(categories);
	}
	
	public void updateLength(String name, Length length) {
		Category category = categories.get(name);
		if(category == null){
			log.warn("Unable to update length category " + name + " because it does not exist.");
			return;
		}
		category.setLength(length);
		categories.put(name, category);
		budgetSubject.setCategories(categories);
	}
}
