package com.elm.view;

import com.elm.po.Food;

import java.util.List;


public interface FoodView {

	public List<Food> showFoodList(Integer businessId);
	public void saveFood(Integer businessId);
	public void updateFood(Integer businessId);
	public void removeFood(Integer businessId);

}
