package org.zezutom.javadp.creational;

import java.util.function.BiConsumer;

public class Builder {
    private final NutritionFacts nutritionFacts = new NutritionFacts();

    public Builder setServingSize(int servingSize) {
        return set(NutritionFacts::setServingSize, servingSize);
    }

    public Builder setServings(int servings) {
        return set(NutritionFacts::setServings, servings);
    }

    public Builder setCalories(int calories) {
        return set(NutritionFacts::setCalories, calories);
    }

    public Builder setFat(int fat) {
        return set(NutritionFacts::setFat, fat);
    }

    public Builder setSodium(int sodium) {
        return set(NutritionFacts::setSodium, sodium);
    }

    public Builder setCarbohydrate(int carbohydrate) {
        return set(NutritionFacts::setCarbohydrate, carbohydrate);
    }

    public NutritionFacts build() {
        return nutritionFacts;
    }

    private<T> Builder set(BiConsumer<NutritionFacts, T> setter, T value) {
        setter.accept(nutritionFacts, value);
        return this;
    }
}

class NutritionFacts {
    private int servingSize;
    private int servings;
    private int calories;
    private int fat;
    private int sodium;
    private int carbohydrate;

    public int getServingSize() {
        return servingSize;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public int getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(int carbohydrate) {
        this.carbohydrate = carbohydrate;
    }
}
