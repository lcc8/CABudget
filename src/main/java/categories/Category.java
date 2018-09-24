package categories;

import java.math.BigDecimal;
import java.util.List;

import expense.Expense;

public class Category {
	private String name; 
	private BigDecimal budget;
	private List<Expense> expenses;
	private Length length;
	
	public Category(String name, Length length, BigDecimal budget) {
		this.name = name;
		this.length = length;
		this.budget = budget;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getBudget() {
		return budget;
	}
	public void setBudget(BigDecimal budget) {
		this.budget = budget;
	}
	public void removeExpense(Expense expense) {
		expenses.remove(expense);
	}
	public void addExpense(Expense expense) {
		expenses.add(expense);
	}

	public Length getLength() {
		return length;
	}

	public void setLength(Length length) {
		this.length = length;
	}
}
