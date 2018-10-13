package model;
import java.math.BigDecimal;
import java.util.Date;

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
	
	public BigDecimal getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}

	public String getName() {
		return name;
	}
	
	public void setCategory(Category category) {
		if(this.category != null && this.category != category) {
			this.category.removeExpense(this);
		}
		category.addExpense(this);
	}

}
