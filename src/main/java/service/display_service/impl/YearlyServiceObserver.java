package service.display_service.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import model.Category;
import subject.BudgetSubject;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

@Slf4j
@Getter
public class YearlyServiceObserver implements Observer {

    Map<String, Category> categories;

    public YearlyServiceObserver(Map<String, Category> categories) {
        this.categories = categories;
    }


    public void update(Observable o, Object arg) {
        if(o instanceof BudgetSubject){
            Map<String, Category> categoryMap = (Map<String, Category>) arg;
            this.categories = categoryMap;
            log.info("Yearly: Categories have been updated.");
        }
    }
}
