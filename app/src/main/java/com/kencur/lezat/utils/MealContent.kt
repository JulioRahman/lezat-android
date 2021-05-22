package com.kencur.lezat.utils

import com.kencur.lezat.model.Meal

object MealContent {

    val MEAL: MutableList<Meal> = ArrayList()

    init {
        MEAL.apply {
            add(
                Meal(
                    "Big Mac",
                    "Beef",
                    "American",
                    listOf(
                        "For the Big Mac sauce, combine all the ingredients in a bowl, season with salt and chill until ready to use.",
                        "To make the patties, season the mince with salt and pepper and form into 4 balls using about 1/3 cup mince each.",
                        "Place each onto a square of baking paper and flatten to form into four x 15cm circles.",
                        "Heat oil in a large frypan over high heat.",
                        "In 2 batches, cook beef patties for 1-2 minutes each side until lightly charred and cooked through.",
                        "Remove from heat and keep warm.",
                        "Repeat with remaining two patties.",
                        "Carefully slice each burger bun into three acrossways, then lightly toast.",
                        "To assemble the burgers, spread a little Big Mac sauce over the bottom base.",
                        "Top with some chopped onion, shredded lettuce, slice of cheese, beef patty and some pickle slices.",
                        "Top with the middle bun layer, and spread with more Big Mac sauce, onion, lettuce, pickles, beef patty and then finish with more sauce.",
                        "Top with burger lid to serve.",
                        "After waiting half an hour for your food to settle, go for a jog."
                    ),
                    "https://www.themealdb.com/images/media/meals/urzj1d1587670726.jpg"
                )
            )
            add(
                Meal(
                    "Beef Rendang",
                    "Beef",
                    "Malaysian",
                    listOf(
                        "Chop the spice paste ingredients and then blend it in a food processor until fine.",
                        "Heat the oil in a stew pot, add the spice paste, cinnamon, cloves, star anise, and cardamom and stir-fry until aromatic.",
                        "Add the beef and the pounded lemongrass and stir for 1 minute.",
                        "Add the coconut milk, tamarind juice, water, and simmer on medium heat, stirring frequently until the meat is almost cooked.",
                        "Add the kaffir lime leaves, kerisik (toasted coconut), sugar or palm sugar, stirring to blend well with the meat.",
                        "Lower the heat to low, cover the lid, and simmer for 1 to 1 1/2 hours or until the meat is really tender and the gravy has dried up.",
                        "Add more salt and sugar to taste.",
                        "Serve immediately with steamed rice and save some for overnight."
                    ),
                    "https://www.themealdb.com/images/media/meals/bc8v651619789840.jpg"
                )
            )
            add(
                Meal(
                    "Chicken Karaage",
                    "Chicken",
                    "Japanese",
                    listOf(
                        "Add the ginger, garlic, soy sauce, sake and sugar to a bowl and whisk to combine.",
                        "Add the chicken, then stir to coat evenly.",
                        "Cover and refrigerate for at least 1 hour.",
                        "Add 1 inch of vegetable oil to a heavy bottomed pot and heat until the oil reaches 360\u00B0F.",
                        "Line a wire rack with 2 sheets of paper towels and get your tongs out.",
                        "Put the potato starch in a bowl.",
                        "Add a handful of chicken to the potato starch and toss to coat each piece evenly.",
                        "Fry the karaage in batches until the exterior is a medium brown and the chicken is cooked through.",
                        "Transfer the fried chicken to the paper towel lined rack.",
                        "If you want the karaage to stay crispy longer, you can fry the chicken a second time, until it's a darker color after it's cooled off once.",
                        "Serve with lemon wedges."
                    ),
                    "https://www.themealdb.com/images/media/meals/tyywsw1505930373.jpg",
                )
            )
        }
    }
}