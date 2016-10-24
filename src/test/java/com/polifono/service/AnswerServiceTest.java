package com.polifono.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.polifono.AbstractTest;
import com.polifono.domain.Answer;
import com.polifono.domain.Question;

/**
 * Unit test methods for the AnswerService.
 * 
 */
@Transactional
public class AnswerServiceTest extends AbstractTest {
	
	@Autowired
    private IAnswerService service;
	
	private final Integer GAME_ID_EXISTENT = 1;
	private final Integer GAME_ID_INEXISTENT = Integer.MAX_VALUE;
	
	private final Integer LEVEL_ID_EXISTENT = 1;
	private final Integer LEVEL_ID_INEXISTENT = Integer.MAX_VALUE;
	
	private final Integer MAP_ID_EXISTENT = 1;
	private final Integer MAP_ID_INEXISTENT = Integer.MAX_VALUE;
	
	private final Integer PHASE_ID_EXISTENT = 1;
	private final Integer PHASE_ID_INEXISTENT = Integer.MAX_VALUE;
	
	private final Integer QUESTION_ID_EXISTENT = 1;
	private final Integer QUESTION_ID_INEXISTENT = Integer.MAX_VALUE;
	
	private final Integer ANSWER_ID_EXISTENT = 1;
	private final Integer ANSWER_ID_INEXISTENT = Integer.MAX_VALUE;

    @Before
    public void setUp() {
        // Do something before each test method.
    }

    @After
    public void tearDown() {
        // Clean up after each test method.
    }

    /* save - begin */
    @Test
    public void save_WhenSaveAnswer_ReturnAnswerSaved() {
    	Question question = new Question();
    	question.setId(QUESTION_ID_EXISTENT);
    	
    	Answer answer = new Answer();
    	answer.setQuestion(question);
    	answer.setName("ANSWER 01");
    	answer.setOrder(1);
    	answer.setRight(true);
    	
    	Answer entitySaved = service.save(answer);
    	
    	Assert.assertNotNull("failure - expected not null", entitySaved);
    	Assert.assertNotEquals("failure - expected id attribute bigger than 0", 0, entitySaved.getId());
    	
    	Answer entity = service.findOne(entitySaved.getId()); 

    	Assert.assertNotNull("failure - expected not null", entity);
    	Assert.assertNotEquals("failure - expected id attribute bigger than 0", 0, entity.getId());
    	
    	// Changing all possible fields.
    	//question.setId(question.getId() + 1);
    	entity.setName(entity.getName() + " CHANGED");
    	entity.setOrder(entity.getOrder() + 1);
    	entity.setRight(!entity.isRight());
    	
    	Answer updatedEntity = service.save(entity);
    	
    	Assert.assertNotNull("failure - expected not null", updatedEntity);
    	Assert.assertEquals("failure - expected id attribute match", entity.getId(), updatedEntity.getId());
    	Assert.assertEquals("failure - expected question attribute match", entity.getQuestion().getId(), updatedEntity.getQuestion().getId());
    	Assert.assertEquals("failure - expected name attribute match", entity.getName(), updatedEntity.getName());
    	Assert.assertEquals("failure - expected order attribute match", entity.getOrder(), updatedEntity.getOrder());
    	Assert.assertEquals("failure - expected right attribute match", entity.isRight(), updatedEntity.isRight());
    }
    /* save - end */
    
    /* delete - begin */
    @Test
    public void delete_WhenAnswerIsExistent_ReturnTrue() {
    	Assert.assertTrue("failure - expected return true", service.delete(ANSWER_ID_EXISTENT));
    	Answer entity = service.findOne(ANSWER_ID_EXISTENT);
    	Assert.assertNull("failure - expected null", entity);
    }
    
    @Test
    public void delete_WhenAnswerIsInexistent_ReturnFalse() {
    	Assert.assertFalse("failure - expected return false", service.delete(ANSWER_ID_INEXISTENT));
    }
    /* delete - end */
    
    /* findOne - begin */
    @Test
    public void findOne_WhenAnswerIsExistent_ReturnAnswer() {
    	Answer entity = service.findOne(ANSWER_ID_EXISTENT);
        Assert.assertNotNull("failure - expected not null", entity);
        Assert.assertEquals("failure - expected id attribute match", ANSWER_ID_EXISTENT.intValue(), entity.getId());
    }

    @Test
    public void findOne_WhenAnswerIsInexistent_ReturnNull() {
    	Answer entity = service.findOne(ANSWER_ID_INEXISTENT);
        Assert.assertNull("failure - expected null", entity);
    }
    /* findOne - end */
    
    /* findAll - begin */
    @Test
    public void findAll_WhenListAllAnswers_ReturnList() {
    	List<Answer> list = service.findAll();
    	Assert.assertNotNull("failure - expected not null", list);
    	Assert.assertNotEquals("failure - not expected list size 0", 0, list.size());
    }
    /* findAll - end */
    
    /* findAnswersByGame - begin */
    @Test
    public void findAnswersByGame_WhenSearchByGameExistent_ReturnList() {
    	List<Answer> list = service.findAnswersByGame(GAME_ID_EXISTENT);
        Assert.assertNotNull("failure - not expected null", list);
        Assert.assertNotEquals("failure - list size not expected 0", 0, list.size());
    }

    @Test
    public void findAnswersByGame_WhenSearchByGameInexistent_ReturnEmptyList() {
    	List<Answer> list = service.findAnswersByGame(GAME_ID_INEXISTENT);
    	Assert.assertEquals("failure - expected empty list", 0, list.size());
    }
    /* findAnswersByGame - end */
    
    /* findAnswersByGameAndLevel - begin */
    @Test
    public void findAnswersByGameAndLevel_WhenSearchByGameAndLevelExistents_ReturnList() {
    	List<Answer> list = service.findAnswersByGameAndLevel(GAME_ID_EXISTENT, LEVEL_ID_EXISTENT);
        Assert.assertNotNull("failure - not expected null", list);
        Assert.assertNotEquals("failure - list size not expected 0", 0, list.size());
    }

    @Test
    public void findAnswersByGameAndLevel_WhenSearchByGameAndLevelInexistents_ReturnEmptyList() {
    	List<Answer> list = service.findAnswersByGameAndLevel(GAME_ID_INEXISTENT, LEVEL_ID_INEXISTENT);
    	Assert.assertEquals("failure - expected empty list", 0, list.size());
    }
    
    @Test
    public void findAnswersByGameAndLevel_WhenSearchByGameExistentButLevelInexistent_ReturnEmptyList() {
    	List<Answer> list = service.findAnswersByGameAndLevel(GAME_ID_EXISTENT, LEVEL_ID_INEXISTENT);
    	Assert.assertEquals("failure - expected empty list", 0, list.size());
    }
    
    @Test
    public void findAnswersByGameAndLevel_WhenSearchByLevelExistentButGameInexistent_ReturnEmptyList() {
    	List<Answer> list = service.findAnswersByGameAndLevel(GAME_ID_INEXISTENT, LEVEL_ID_EXISTENT);
    	Assert.assertEquals("failure - expected empty list", 0, list.size());
    }
    /* findAnswersByGameAndLevel - end */
    
    /* findAnswersByMap - begin */
    @Test
    public void findAnswersByMap_WhenSearchByMapExistent_ReturnList() {
    	List<Answer> list = service.findAnswersByMap(MAP_ID_EXISTENT);
        Assert.assertNotNull("failure - not expected null", list);
        Assert.assertNotEquals("failure - list size not expected 0", 0, list.size());
    }

    @Test
    public void findAnswersByMap_WhenSearchByMapInexistent_ReturnEmptyList() {
    	List<Answer> list = service.findAnswersByMap(MAP_ID_INEXISTENT);
    	Assert.assertEquals("failure - expected empty list", 0, list.size());
    }
    /* findAnswersByMap - end */
    
    /* findAnswersByPhase - begin */
    @Test
    public void findAnswersByPhase_WhenSearchByPhaseExistent_ReturnList() {
    	List<Answer> list = service.findAnswersByPhase(PHASE_ID_EXISTENT);
        Assert.assertNotNull("failure - not expected null", list);
        Assert.assertNotEquals("failure - list size not expected 0", 0, list.size());
    }

    @Test
    public void findAnswersByPhase_WhenSearchByPhaseInexistent_ReturnEmptyList() {
    	List<Answer> list = service.findAnswersByPhase(PHASE_ID_INEXISTENT);
    	Assert.assertEquals("failure - expected empty list", 0, list.size());
    }
    /* findAnswersByPhase - end */
    
    /* findAnswersByQuestion - begin */
    @Test
    public void findAnswersByQuestion_WhenSearchByQuestionExistent_ReturnList() {
    	List<Answer> list = service.findAnswersByQuestion(QUESTION_ID_EXISTENT);
        Assert.assertNotNull("failure - not expected null", list);
        Assert.assertNotEquals("failure - list size not expected 0", 0, list.size());
    }

    @Test
    public void findAnswersByQuestion_WhenSearchByQuestionInexistent_ReturnEmptyList() {
    	List<Answer> list = service.findAnswersByQuestion(QUESTION_ID_INEXISTENT);
    	Assert.assertEquals("failure - expected empty list", 0, list.size());
    }
    /* findAnswersByQuestion - end */
}