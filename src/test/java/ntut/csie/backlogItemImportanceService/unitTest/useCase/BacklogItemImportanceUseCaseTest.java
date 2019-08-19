package ntut.csie.backlogItemImportanceService.unitTest.useCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
	
	@Before
	public void setUp() {
		fakeBacklogItemImportanceRepository = new FakeBacklogItemImportanceRepository();
		fakeEventStore = new FakeEventStore();
		DomainEventListener.getInstance().init(fakeEventStore);
	}
	
	@After
	public void tearDown() {
		fakeBacklogItemImportanceRepository.clearAll();
		fakeEventStore.clearAll();
	}
	
	@Test
	public void Should_Success_When_AddBacklogItemImportance() {
		String backlogItemId = "1";
		int importance = 90;
		
		assertNull(null, getBacklogItemImportanceByBacklogItemId(backlogItemId));
		
		AddBacklogItemImportanceOutput output = addBacklogItemImportance(backlogItemId, importance);
		
		assertTrue(output.isAddSuccess());
		assertNotNull(null, getBacklogItemImportanceByBacklogItemId(backlogItemId));
	}
	
	@Test
	public void Should_ReturnBacklogItemImportance_When_GetBacklogItemImportanceByBacklogItemId() {
		String backlogItemId = "1";
		int importance = 100;
		
		addBacklogItemImportance(backlogItemId, importance);
		
		BacklogItemImportanceModel backlogItemImportance = getBacklogItemImportanceByBacklogItemId(backlogItemId);
		
		assertEquals(importance, backlogItemImportance.getImportance());
	}
	
	@Test
	public void Should_Success_When_EditBacklogItemImportance() {
		String backlogItemId = "1";
		int importance = 100;
		
		addBacklogItemImportance(backlogItemId, importance);
		
		int editedImportance = 80;
		
		EditBacklogItemImportanceOutput output = editBacklogItemImportance(backlogItemId, editedImportance);
		
		BacklogItemImportanceModel backlogItemImportance = getBacklogItemImportanceByBacklogItemId(backlogItemId);
		
		assertTrue(output.isEditSuccess());
		assertEquals(editedImportance, backlogItemImportance.getImportance());
	}
	
	@Test
	public void Should_Success_When_DeleteBacklogItemImportance() {
		String backlogItemId = "1";
		int importance = 65;
		
		addBacklogItemImportance(backlogItemId, importance);
		
		assertNotNull(getBacklogItemImportanceByBacklogItemId(backlogItemId));
		
		DeleteBacklogItemImportanceOutput output = deleteBacklogItemImportance(backlogItemId);
		
		assertTrue(output.isDeleteSuccess());
		assertNull(getBacklogItemImportanceByBacklogItemId(backlogItemId));
	}
	
	@Test
	public void Should_ReturnHistoryList_When_GetHistoriesByBacklogItemId() {
		String backlogItemId = "1";
		int importance = 100;
		
		addBacklogItemImportance(backlogItemId, importance);
		
		int editedImportance = 80;
		
		editBacklogItemImportance(backlogItemId, editedImportance);
		
		assertEquals(1, getHistoriesByBacklogItemId(backlogItemId).size());
	}
	
	private AddBacklogItemImportanceOutput addBacklogItemImportance(String backlogItemId, int importance) {
		AddBacklogItemImportanceUseCase addBacklogItemImportanceUseCase = new AddBacklogItemImportanceUseCaseImpl(fakeBacklogItemImportanceRepository);
		AddBacklogItemImportanceInput input = (AddBacklogItemImportanceInput) addBacklogItemImportanceUseCase;
		input.setBacklogItemId(backlogItemId);
		input.setImportance(importance);
		AddBacklogItemImportanceOutput output = new AddBacklogItemImportanceRestfulAPI();
		addBacklogItemImportanceUseCase.execute(input, output);
		return output;
	}
	
	private BacklogItemImportanceModel getBacklogItemImportanceByBacklogItemId(String backlogItemId) {
		GetBacklogItemImportanceByBacklogItemIdUseCase getBacklogItemImportanceByBacklogItemIdUseCase = new GetBacklogItemImportanceByBacklogItemIdUseCaseImpl(fakeBacklogItemImportanceRepository);
		GetBacklogItemImportanceByBacklogItemIdInput input = (GetBacklogItemImportanceByBacklogItemIdInput) getBacklogItemImportanceByBacklogItemIdUseCase;
		input.setBacklogItemId(backlogItemId);
		GetBacklogItemImportanceByBacklogItemIdOutput output = new GetBacklogItemImportanceByBacklogItemIdRestfulAPI();
		getBacklogItemImportanceByBacklogItemIdUseCase.execute(input, output);
		return output.getBacklogItemImportance();
	}
	
	private EditBacklogItemImportanceOutput editBacklogItemImportance(String backlogItemId, int importance) {
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
	
	private List<HistoryModel> getHistoriesByBacklogItemId(String backlogItemId) {
		GetHistoriesByBacklogItemIdUseCase getHistoriesByBacklogItemIdUseCase = new GetHistoriesByBacklogItemIdUseCaseImpl(fakeEventStore);
		GetHistoriesByBacklogItemIdInput input = (GetHistoriesByBacklogItemIdInput) getHistoriesByBacklogItemIdUseCase;
		input.setBacklogItemId(backlogItemId);
		GetHistoriesByBacklogItemIdOutput output = new GetHistoriesByBacklogItemIdRestfulAPI();
		getHistoriesByBacklogItemIdUseCase.execute(input, output);
		return output.getHistoryList();
	}
}
