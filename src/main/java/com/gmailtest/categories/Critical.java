package com.gmailtest.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;

@RunWith(Categories.class)
@Categories.IncludeCategory({Critical.class})
public class Critical {
}
