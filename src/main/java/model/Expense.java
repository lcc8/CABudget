package model;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
public class Expense {
	private String name;
	private BigDecimal amount;
	private Date date;
	private Category category;
	
	public Expense(String name, BigDecimal amount, Date date) {
		this.name = name;
		this.amount = amount;
		this.date = date;
	}
	
	public void setCategory(Category category) {
		if(this.category != null && this.category != category) {
			this.category.removeExpense(this);
		}
		this.category = category;
		category.addExpense(this);
	}

}
