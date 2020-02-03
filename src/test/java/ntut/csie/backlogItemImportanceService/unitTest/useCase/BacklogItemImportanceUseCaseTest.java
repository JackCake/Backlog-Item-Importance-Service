package ntut.csie.backlogItemImportanceService.unitTest.useCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ntut.csie.backlogItemImportanceService.controller.backlogItemImportance.AddBacklogItemImportanceRestfulAPI;
import ntut.csie.backlogItemImportanceService.controller.backlogItemImportance.DeleteBacklogItemImportanceRestfulAPI;
import ntut.csie.backlogItemImportanceService.controller.backlogItemImportance.EditBacklogItemImportanceRestfulAPI;
import ntut.csie.backlogItemImportanceService.controller.backlogItemImportance.GetBacklogItemImportanceByBacklogItemIdRestfulAPI;
import ntut.csie.backlogItemImportanceService.controller.history.GetHistoriesByBacklogItemIdRestfulAPI;
import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;
import ntut.csie.backlogItemImportanceService.unitTest.factory.TestFactory;
import ntut.csie.backlogItemImportanceService.unitTest.repository.FakeBacklogItemImportanceRepository;
import ntut.csie.backlogItemImportanceService.unitTest.repository.FakeEventStore;
import ntut.csie.backlogItemImportanceService.useCase.DomainEventListener;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceModel;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add.AddBacklogItemImportanceInput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add.AddBacklogItemImportanceOutput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add.AddBacklogItemImportanceUseCase;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add.AddBacklogItemImportanceUseCaseImpl;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete.DeleteBacklogItemImportanceInput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete.DeleteBacklogItemImportanceOutput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete.DeleteBacklogItemImportanceUseCase;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete.DeleteBacklogItemImportanceUseCaseImpl;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit.EditBacklogItemImportanceInput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit.EditBacklogItemImportanceOutput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit.EditBacklogItemImportanceUseCase;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit.EditBacklogItemImportanceUseCaseImpl;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get.GetBacklogItemImportanceByBacklogItemIdInput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get.GetBacklogItemImportanceByBacklogItemIdOutput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get.GetBacklogItemImportanceByBacklogItemIdUseCase;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get.GetBacklogItemImportanceByBacklogItemIdUseCaseImpl;
import ntut.csie.backlogItemImportanceService.useCase.history.HistoryModel;
import ntut.csie.backlogItemImportanceService.useCase.history.get.GetHistoriesByBacklogItemIdInput;
import ntut.csie.backlogItemImportanceService.useCase.history.get.GetHistoriesByBacklogItemIdOutput;
import ntut.csie.backlogItemImportanceService.useCase.history.get.GetHistoriesByBacklogItemIdUseCase;
import ntut.csie.backlogItemImportanceService.useCase.history.get.GetHistoriesByBacklogItemIdUseCaseImpl;

public class BacklogItemImportanceUseCaseTest {
	private FakeBacklogItemImportanceRepository fakeBacklogItemImportanceRepository;
	private FakeEventStore fakeEventStore;
	
	private TestFactory testFactory;
	
	private String backlogItemId;
	
	@Before
	public void setUp() {
		fakeBacklogItemImportanceRepository = new FakeBacklogItemImportanceRepository();
		fakeEventStore = new FakeEventStore();
		DomainEventListener.getInstance().init(fakeEventStore);
		
		testFactory = new TestFactory(fakeBacklogItemImportanceRepository);
		
		backlogItemId = "1";
	}
	
	@After
	public void tearDown() {
		fakeBacklogItemImportanceRepository.clearAll();
		fakeEventStore.clearAll();
	}
	
	@Test
	public void Should_Success_When_AddBacklogItemImportance() {
		int importance = 90;
		
		AddBacklogItemImportanceOutput output = addBacklogItemImportance(importance, backlogItemId);
		
		boolean isAddSuccess = output.isAddSuccess();
		assertTrue(isAddSuccess);
	}
	
	@Test
	public void Should_ReturnErrorMessage_When_AddBacklogItemImportanceWithNullBacklogItemId() {
		int importance = 90;
		
		AddBacklogItemImportanceOutput output = addBacklogItemImportance(importance, null);
		
		boolean isAddSuccess = output.isAddSuccess();
		String errorMessage = output.getErrorMessage();
		String expectedErrorMessage = "The backlog item id of the backlog item importance should be required!\n";
		assertFalse(isAddSuccess);
		assertEquals(expectedErrorMessage, errorMessage);
	}
	
	@Test
	public void Should_ReturnErrorMessage_When_AddBacklogItemImportanceWithEmptyBacklogItemId() {
		int importance = 90;
		
		AddBacklogItemImportanceOutput output = addBacklogItemImportance(importance, "");
		
		boolean isAddSuccess = output.isAddSuccess();
		String errorMessage = output.getErrorMessage();
		String expectedErrorMessage = "The backlog item id of the backlog item importance should be required!\n";
		assertFalse(isAddSuccess);
		assertEquals(expectedErrorMessage, errorMessage);
	}
	
	@Test
	public void Should_ReturnBacklogItemImportance_When_GetBacklogItemImportance() {
		int importance = 100;
		
		testFactory.newBacklogItemImportance(importance, backlogItemId);
		
		GetBacklogItemImportanceByBacklogItemIdOutput output = getBacklogItemImportanceByBacklogItemId(backlogItemId);
		BacklogItemImportanceModel backlogItemImportance = output.getBacklogItemImportance();
		
		assertEquals(importance, backlogItemImportance.getImportance());
	}
	
	@Test
	public void Should_Success_When_EditBacklogItemImportance() {
		int importance = 100;
		
		BacklogItemImportance backlogItemImportance = testFactory.newBacklogItemImportance(importance, backlogItemId);
		
		int editedImportance = 80;
		
		EditBacklogItemImportanceOutput output = editBacklogItemImportance(editedImportance, backlogItemId);
		
		boolean isEditSuccess = output.isEditSuccess();
		assertTrue(isEditSuccess);
		assertEquals(editedImportance, backlogItemImportance.getImportance());
	}
	
	@Test
	public void Should_ReturnErrorMessage_When_EditNotExistBacklogItemImportance() {
		int editedImportance = 80;
		
		EditBacklogItemImportanceOutput output = editBacklogItemImportance(editedImportance, null);
		
		boolean isEditSuccess = output.isEditSuccess();
		String errorMessage = output.getErrorMessage();
		String expectedErrorMessage = "Sorry, the importance of the backlog item is not exist!";
		assertFalse(isEditSuccess);
		assertEquals(expectedErrorMessage, errorMessage);
	}
	
	@Test
	public void Should_Success_When_DeleteBacklogItemImportance() {
		int importance = 65;
		
		testFactory.newBacklogItemImportance(importance, backlogItemId);
		
		DeleteBacklogItemImportanceOutput output = deleteBacklogItemImportance(backlogItemId);
		
		boolean isDeleteSuccess = output.isDeleteSuccess();
		assertTrue(isDeleteSuccess);
	}
	
	@Test
	public void Should_ReturnErrorMessage_When_DeleteNotExistBacklogItemImportance() {
		DeleteBacklogItemImportanceOutput output = deleteBacklogItemImportance(backlogItemId);
		
		boolean isDeleteSuccess = output.isDeleteSuccess();
		String errorMessage = output.getErrorMessage();
		String expectedErrorMessage = "Sorry, the importance of the backlog item is not exist!";
		assertFalse(isDeleteSuccess);
		assertEquals(expectedErrorMessage, errorMessage);
	}
	
	@Test
	public void Should_ReturnHistoryList_When_GetHistoriesOfBacklogItem() {
		int importance = 100;
		
		addBacklogItemImportance(importance, backlogItemId);
		
		int editedImportance = 80;
		
		editBacklogItemImportance(editedImportance, backlogItemId);
		
		GetHistoriesByBacklogItemIdOutput output = getHistoriesByBacklogItemId(backlogItemId);
		List<HistoryModel> historyList = output.getHistoryList();
		
		int numberOfHistories = 1;
		assertEquals(numberOfHistories, historyList.size());
	}
	
	private AddBacklogItemImportanceOutput addBacklogItemImportance(int importance, String backlogItemId) {
		AddBacklogItemImportanceUseCase addBacklogItemImportanceUseCase = new AddBacklogItemImportanceUseCaseImpl(fakeBacklogItemImportanceRepository);
		AddBacklogItemImportanceInput input = (AddBacklogItemImportanceInput) addBacklogItemImportanceUseCase;
		input.setBacklogItemId(backlogItemId);
		input.setImportance(importance);
		AddBacklogItemImportanceOutput output = new AddBacklogItemImportanceRestfulAPI();
		addBacklogItemImportanceUseCase.execute(input, output);
		return output;
	}
	
	private GetBacklogItemImportanceByBacklogItemIdOutput getBacklogItemImportanceByBacklogItemId(String backlogItemId) {
		GetBacklogItemImportanceByBacklogItemIdUseCase getBacklogItemImportanceByBacklogItemIdUseCase = new GetBacklogItemImportanceByBacklogItemIdUseCaseImpl(fakeBacklogItemImportanceRepository);
		GetBacklogItemImportanceByBacklogItemIdInput input = (GetBacklogItemImportanceByBacklogItemIdInput) getBacklogItemImportanceByBacklogItemIdUseCase;
		input.setBacklogItemId(backlogItemId);
		GetBacklogItemImportanceByBacklogItemIdOutput output = new GetBacklogItemImportanceByBacklogItemIdRestfulAPI();
		getBacklogItemImportanceByBacklogItemIdUseCase.execute(input, output);
		return output;
	}
	
	private EditBacklogItemImportanceOutput editBacklogItemImportance(int importance, String backlogItemId) {
		EditBacklogItemImportanceUseCase editBacklogItemImportanceUseCase = new EditBacklogItemImportanceUseCaseImpl(fakeBacklogItemImportanceRepository);
		EditBacklogItemImportanceInput input = (EditBacklogItemImportanceInput) editBacklogItemImportanceUseCase;
		input.setBacklogItemId(backlogItemId);
		input.setImportance(importance);
		EditBacklogItemImportanceOutput output = new EditBacklogItemImportanceRestfulAPI();
		editBacklogItemImportanceUseCase.execute(input, output);
		return output;
	}
	
	private DeleteBacklogItemImportanceOutput deleteBacklogItemImportance(String backlogItemId) {
		DeleteBacklogItemImportanceUseCase deleteBacklogItemImportanceUseCase = new DeleteBacklogItemImportanceUseCaseImpl(fakeBacklogItemImportanceRepository);
		DeleteBacklogItemImportanceInput input = (DeleteBacklogItemImportanceInput) deleteBacklogItemImportanceUseCase;
		input.setBacklogItemId(backlogItemId);
		DeleteBacklogItemImportanceOutput output = new DeleteBacklogItemImportanceRestfulAPI();
		deleteBacklogItemImportanceUseCase.execute(input, output);
		return output;
	}
	
	private GetHistoriesByBacklogItemIdOutput getHistoriesByBacklogItemId(String backlogItemId) {
		GetHistoriesByBacklogItemIdUseCase getHistoriesByBacklogItemIdUseCase = new GetHistoriesByBacklogItemIdUseCaseImpl(fakeEventStore);
		GetHistoriesByBacklogItemIdInput input = (GetHistoriesByBacklogItemIdInput) getHistoriesByBacklogItemIdUseCase;
		input.setBacklogItemId(backlogItemId);
		GetHistoriesByBacklogItemIdOutput output = new GetHistoriesByBacklogItemIdRestfulAPI();
		getHistoriesByBacklogItemIdUseCase.execute(input, output);
		return output;
	}
}
