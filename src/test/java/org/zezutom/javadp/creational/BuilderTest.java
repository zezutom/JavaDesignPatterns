package org.zezutom.javadp.creational;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BuilderTest {

    private Builder builder;

    @Before
    public void setUp() {
        builder = new Builder();
    }

    @Test
    public void providesSensibleDefaults() {
        NutritionFacts nutritionFacts = builder.build();
        assertNotNull(nutritionFacts);
        assertTrue(nutritionFacts.getCalories() == 0);
        assertTrue(nutritionFacts.getCarbohydrate() == 0);
        assertTrue(nutritionFacts.getFat() == 0);
        assertTrue(nutritionFacts.getServings() == 0);
        assertTrue(nutritionFacts.getServingSize() == 0);
        assertTrue(nutritionFacts.getSodium() == 0);
    }

    @Test
    public void setsPropertyValues() {
        NutritionFacts nutritionFacts = builder
                .setServingSize(1)
                .setServings(2)
                .setCalories(200)
                .setCarbohydrate(10)
                .setFat(20)
                .setSodium(300)
                .build();

        assertTrue(nutritionFacts.getServingSize() == 1);
        assertTrue(nutritionFacts.getServings() == 2);
        assertTrue(nutritionFacts.getCalories() == 200);
        assertTrue(nutritionFacts.getCarbohydrate() == 10);
        assertTrue(nutritionFacts.getFat() == 20);
        assertTrue(nutritionFacts.getSodium() == 300);
    }
}
