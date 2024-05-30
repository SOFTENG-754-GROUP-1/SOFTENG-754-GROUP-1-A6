package nz.ac.auckland.se754.web.controller;

import nz.ac.auckland.se754.web.backend.manager.WordManager;
import nz.ac.auckland.se754.web.backend.model.WordLibrary;
import org.mockito.Mockito;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LessonController {

    @RequestMapping(value="/lesson", method = RequestMethod.GET)
    public String showLessonPage(ModelMap model){
        return "lesson";
    }

    @RequestMapping(value="/lesson/getDefinition", method = RequestMethod.POST)
    @ResponseBody
    public String getDefinition(@RequestParam("word") String word) {
        WordLibrary wordLibrary = Mockito.mock(WordLibrary.class);
        Mockito.when(wordLibrary.getDefinition("valid word")).thenReturn("valid definition");
        WordManager wordManager = new WordManager(wordLibrary);
        String definition = wordManager.retrieveDefinition(word);
        return definition;
    }

    @RequestMapping(value="/lesson/getExample", method = RequestMethod.POST)
    @ResponseBody
    public String getExample(@RequestParam("word") String word) {
        WordLibrary wordLibrary = Mockito.mock(WordLibrary.class);
        Mockito.when(wordLibrary.getExamples("valid word")).thenReturn(new String[]{"valid example", "another valid example"});
        Mockito.when(wordLibrary.getExamples("invalid word")).thenReturn(new String[]{"Unable to find any examples"});
        WordManager wordManager = new WordManager(wordLibrary);
        String[] examples = (String[]) wordManager.retrieveExamples(word);
        return examples[0];
    }
}
