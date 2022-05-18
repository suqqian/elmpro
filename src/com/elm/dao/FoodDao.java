package com.elm.dao;


import com.elm.po.Food;

import java.util.List;

public interface FoodDao {

    public List<Food> listFoodByBusinessId(Integer businessId);
    public int saveFood(Food food);
    public Food getFoodById(Integer foodId);
    public int updateFood(Food food);
    public int removeFood(Integer foodId);
}
