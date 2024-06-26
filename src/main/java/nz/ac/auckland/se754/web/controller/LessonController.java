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

import java.util.List;
import java.util.Map;

@Controller
public class LessonController {

    @RequestMapping(value="/lesson", method = RequestMethod.GET)
    public String showLessonPage(ModelMap model){
        model.addAttribute("tasks", List.of(
                Map.of("id", "task1", "question", "What is あ?", "correctAnswer", "a", "status", "Uncompleted"),
                Map.of("id", "task2", "question", "What is い?", "correctAnswer", "i", "status", "Uncompleted")
        ));
        return "lesson";
    }

    @RequestMapping(value="/lesson/getDefinition", method = RequestMethod.POST)
    @ResponseBody
    public String getDefinition(@RequestParam("word") String word) {
        WordLibrary wordLibrary = Mockito.mock(WordLibrary.class);
        Mockito.when(wordLibrary.getDefinition("valid word")).thenReturn("valid definition");
        Mockito.when(wordLibrary.getDefinition("server down")).thenThrow(new RuntimeException());
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
        Mockito.when(wordLibrary.getExamples("server down")).thenThrow(new RuntimeException());
        WordManager wordManager = new WordManager(wordLibrary);
        String[] examples = (String[]) wordManager.retrieveExamples(word);
        return examples[0];
    }

    @RequestMapping(value="/lesson/getSynonymsAndAntonyms", method = RequestMethod.POST)
    @ResponseBody
    public Object getSynonymsAndAntonyms(@RequestParam("word") String word) {
        WordLibrary wordLibrary = Mockito.mock(WordLibrary.class);
        Mockito.when(wordLibrary.getSynonyms("valid word")).thenReturn(new String[]{"synonym1", "synonym2"});
        Mockito.when(wordLibrary.getAntonyms("valid word")).thenReturn(new String[]{"antonym1", "antonym2"});
        Mockito.when(wordLibrary.getSynonyms("invalid word")).thenReturn(null);
        Mockito.when(wordLibrary.getAntonyms("invalid word")).thenReturn(null);
        Mockito.when(wordLibrary.getSynonyms("server down")).thenThrow(new RuntimeException());
        Mockito.when(wordLibrary.getAntonyms("server down")).thenThrow(new RuntimeException());
        WordManager wordManager = new WordManager(wordLibrary);
        return wordManager.retrieveSynonymsAndAntonyms(word);
    }

    @RequestMapping(value="/lesson/getImage", method = RequestMethod.POST)
    @ResponseBody
    public String getImage(@RequestParam("word") String word) {
        WordLibrary wordLibrary = Mockito.mock(WordLibrary.class);
        Mockito.when(wordLibrary.getImagePath("valid word")).thenReturn("/path/to/dummy/image.png");
        Mockito.when(wordLibrary.getImagePath("invalid word")).thenReturn("/path/to/default/image.png");
        Mockito.when(wordLibrary.getImagePath("server down")).thenThrow(new RuntimeException());
        WordManager wordManager = new WordManager(wordLibrary);
        return wordManager.retrieveImagePath(word);
    }

    @RequestMapping(value="/lesson/getPronunciation", method = RequestMethod.POST)
    @ResponseBody
    public String getPronunciation(@RequestParam("word") String word) {
        WordLibrary wordLibrary = Mockito.mock(WordLibrary.class);
        Mockito.when(wordLibrary.getAudioPath("valid word")).thenReturn("/path/to/dummy/audio.wav");
        Mockito.when(wordLibrary.getAudioPath("invalid word")).thenReturn("Unable to find pronunciation");
        Mockito.when(wordLibrary.getAudioPath("server down")).thenThrow(new RuntimeException());
        WordManager wordManager = new WordManager(wordLibrary);
        return wordManager.retrieveAudioPath(word);
    }
}
