package subject;

import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import model.Category;
import org.springframework.stereotype.Service;

@Service
public class BudgetSubject extends Observable{
	Map<String, Category> categories;
	List<Observer> observers;
	
	
	public void setCategories(Map<String, Category> categories) {
		this.categories = categories;
		categoriesChanged();
	}
	
	private void categoriesChanged() {
		setChanged();
		notifyObserver();
	}
	
	private void notifyObserver() {
		for(Observer observer : observers) {
			observer.update(this, categories);
		}
	}
}
