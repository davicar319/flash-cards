package com.bixbysdoghouse.flashcards.web;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bixbysdoghouse.flashcards.entity.FlashCard;
import com.bixbysdoghouse.flashcards.entity.FlashCardId;
import com.bixbysdoghouse.flashcards.repository.CardExistsException;
import com.bixbysdoghouse.flashcards.service.AdditionAnswer;
import com.bixbysdoghouse.flashcards.service.AdditionFlashCardService;
import com.bixbysdoghouse.flashcards.service.AdditionQuestion;
import com.bixbysdoghouse.flashcards.to.integer.addition.NewAnswer;
import com.bixbysdoghouse.flashcards.to.integer.addition.NewFlashCard;
import com.bixbysdoghouse.flashcards.to.integer.addition.NewQuestion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AdditionController.class)
class AdditionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdditionFlashCardService additionFlashCardService;

    private static int addend1 = 1;
    private static int addend2 = 4;
    private static int answer = 5;
    private NewQuestion newQuestion = NewQuestion.of(addend1, addend2);
    private NewAnswer newAnswer = NewAnswer.of(answer);
    private NewFlashCard newFlashCard = NewFlashCard.of(newQuestion, newAnswer);


    @Test
    void testCanary() {
        assertNotNull(mockMvc);
        assertNotNull(additionFlashCardService);
    }

    @Test
    void testCreateReturnsFlashCardId() throws Exception {
        Long expectedIdValue = 90210L;
        AdditionQuestion expectedAdditionQuestion = new AdditionQuestion(addend1, addend2);
        AdditionAnswer expectedAdditionAnswer = new AdditionAnswer(answer);
        Mockito.when(additionFlashCardService.createFlashCard(expectedAdditionQuestion,
                                                              expectedAdditionAnswer))
               .thenReturn(new FlashCard(createLongId(expectedIdValue),
                                         new AdditionQuestion(addend1, addend2),
                                         new AdditionAnswer(answer)));

        mockMvc.perform(
                MockMvcRequestBuilders.post("/flash-card/addition")
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(convertToJson(newFlashCard))
        )
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andExpect(MockMvcResultMatchers.header().exists("location"))
               .andExpect(MockMvcResultMatchers.header().string("location",
                                                                Matchers.endsWith(
                                                                        "/flash-card/addition/" + expectedIdValue)));
        Mockito.verify(additionFlashCardService).createFlashCard(expectedAdditionQuestion, expectedAdditionAnswer);
    }

    @Test
    void testCreateFailsBecauseFlashCardAlreadyExists() throws Exception {
        AdditionQuestion expectedAdditionQuestion = new AdditionQuestion(addend1, addend2);
        AdditionAnswer expectedAdditionAnswer = new AdditionAnswer(answer);
        Mockito.when(additionFlashCardService.createFlashCard(expectedAdditionQuestion,
                                                              expectedAdditionAnswer))
               .thenThrow(new CardExistsException("The input flash card already exists"));
        mockMvc.perform(
                MockMvcRequestBuilders.post("/flash-card/addition")
                                      .contentType(MediaType.APPLICATION_JSON)
                                      .content(convertToJson(newFlashCard))
        )
               .andExpect(MockMvcResultMatchers.status().isBadRequest())
               .andExpect(MockMvcResultMatchers.status().reason("Could not create the card: " +
                                                                        "NewFlashCard(question=NewQuestion(addend1=1, " +
                                                                        "addend2=4), answer=NewAnswer(answer=5)) " +
                                                                        "Because that card already exists."));
        Mockito.verify(additionFlashCardService).createFlashCard(expectedAdditionQuestion, expectedAdditionAnswer);
    }

    private static String convertToJson(NewFlashCard newFlashCard) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(newFlashCard);
    }

    FlashCardId createLongId(Long idValue) {
        return () -> idValue;
    }

}