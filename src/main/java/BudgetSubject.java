import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import categories.Category;

public class BudgetSubject extends Observable{
	Map<String, Category> categories;
	List<Observer> observers;
	
	
	public void setCategory(Category category) {
		categories.put(category.getName(), category);
		
		categoriesChanged();
	}
	
	private void categoriesChanged() {
		setChanged();
		notifyObserver();
	}
	
	private void notifyObserver() {
		for(Observer observer : observers) {
			observer.update(categories);
		}
		
	}

	public Map<String, Category> getAllCategories(){
		return categories;
	}
	
	
	
	
}
