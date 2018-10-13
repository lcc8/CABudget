package service.impl;

import lombok.extern.slf4j.Slf4j;
import model.Category;
import model.Expense;
import service.MonthlyService;
import subject.BudgetSubject;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
public class MonthlyServiceObserver implements Observer, MonthlyService {

    List<Expense> expenses;
    Map<String, Category> categories;

    public MonthlyServiceObserver(Map<String, Category> categories) {
        expenses = new ArrayList<Expense>();
        this.categories = categories==null ? new HashMap<String, Category>() : categories;
    }

    public void update(Observable o, Object arg) {
        if(o instanceof BudgetSubject){
            Map<String, Category> categoryMap = ((BudgetSubject) o).getAllCategories();
            this.categories = categoryMap;
        }
    }

    public void addAnExpense(String name, BigDecimal amount, Date expenseDate) {
        expenses.add(new Expense(name, amount, expenseDate));
        log.info("Add a new expense with name= " + name + ", amount=" + amount + ", date=" + expenseDate);
    }

    public void removeAnExpense(int index) {
        Expense expense = expenses.get(index);
        expenses.remove(index);
        log.info(("Remove an expense " + expense.getName() + " with amount " + expense.getAmount()));
    }

    public void addExpenseToCategory(int index, String categoryName) {
        Expense expense = expenses.get(index);
        Category category = categories.get(categoryName);

        if(category == null){
            log.warn("Cannot set " + expense.getName() + " to category " + categoryName + " because this category does not exist.");
        }else{
            expense.setCategory(category);
            log.info("Set expense " + expense.getName() + " to category " + categoryName);
        }
    }
}