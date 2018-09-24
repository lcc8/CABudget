package expense;
import java.math.BigDecimal;
import java.util.Date;

import categories.Category;

public class Expense {
	private BigDecimal amount;
	private Date date;
	private Category category;
	
	public Expense(BigDecimal amount, Date date) {
		this.amount = amount;
		this.date = date;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public Date getDate() {
		return date;
	}
	
	public void setCategory(Category category) {
		if(this.category != null && this.category != category) {
			this.category.removeExpense(this);
		}
		category.addExpense(this);
	}
}
