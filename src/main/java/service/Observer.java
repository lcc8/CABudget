package service;

import java.util.List;

import categories.Category;

public interface Observer {
	void update(List<Category> categories);
}
