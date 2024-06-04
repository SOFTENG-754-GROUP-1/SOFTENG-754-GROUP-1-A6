package nz.ac.auckland.se754.web.controller;

import nz.ac.auckland.se754.web.model.Category;
import nz.ac.auckland.se754.web.model.CourseItem;
import org.mockito.Mockito;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;

@Controller
public class CourseCategoryController {
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String showCategoryPage(ModelMap model) {
        initialiseCategories(model);
        return "category";
    }

    private void initialiseCategories(ModelMap model) {
        List<Category> categoryList = Arrays.asList(
                new Category("Category-1", new String[]{"course1", "course2"}),
                new Category("Category-2", new String[]{"course3", "course4"}),
                new Category("Category-3", new String[]{"course5"})
        );

        model.addAttribute("categoryList", categoryList);

    }
}
