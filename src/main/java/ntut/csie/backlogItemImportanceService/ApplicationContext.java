package ntut.csie.backlogItemImportanceService;

import ntut.csie.backlogItemImportanceService.gateways.repository.backlogItemImportance.MySqlBacklogItemImportanceRepositoryImpl;
import ntut.csie.backlogItemImportanceService.gateways.repository.event.MySqlEventStoreImpl;
import ntut.csie.backlogItemImportanceService.useCase.EventStore;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceRepository;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add.AddBacklogItemImportanceUseCase;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add.AddBacklogItemImportanceUseCaseImpl;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete.DeleteBacklogItemImportanceUseCase;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete.DeleteBacklogItemImportanceUseCaseImpl;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit.EditBacklogItemImportanceUseCase;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit.EditBacklogItemImportanceUseCaseImpl;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get.GetBacklogItemImportanceByBacklogItemIdUseCase;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get.GetBacklogItemImportanceByBacklogItemIdUseCaseImpl;
import ntut.csie.backlogItemImportanceService.useCase.history.get.GetHistoriesByBacklogItemIdUseCase;
import ntut.csie.backlogItemImportanceService.useCase.history.get.GetHistoriesByBacklogItemIdUseCaseImpl;

public class ApplicationContext {
	private static ApplicationContext instance = null;
	
	private static BacklogItemImportanceRepository backlogItemImportanceRepository = null;
	private static EventStore eventStore = null;
	
	private AddBacklogItemImportanceUseCase addBacklogItemImportanceUseCase;
	private GetBacklogItemImportanceByBacklogItemIdUseCase getBacklogItemImportanceByBacklogItemIdUseCase;
	private EditBacklogItemImportanceUseCase editBacklogItemImportanceUseCase;
	private DeleteBacklogItemImportanceUseCase deleteBacklogItemImportanceUseCase;
	private GetHistoriesByBacklogItemIdUseCase getHistoriesByBacklogItemIdUseCase;
	
	private ApplicationContext() {}
	
	public static synchronized ApplicationContext getInstance() {
		if(instance == null){
			return new ApplicationContext();
		}
		return instance;
	}
	
	public BacklogItemImportanceRepository newBacklogItemImportanceRepository() {
		if(backlogItemImportanceRepository == null) {
			backlogItemImportanceRepository = new MySqlBacklogItemImportanceRepositoryImpl();
		}
		return backlogItemImportanceRepository;
	}
	
	public EventStore newEventStore() {
		if(eventStore == null) {
			eventStore = new MySqlEventStoreImpl();
		}
		return eventStore;
	}
	
	public AddBacklogItemImportanceUseCase newAddBacklogItemImportanceUseCase() {
		addBacklogItemImportanceUseCase = new AddBacklogItemImportanceUseCaseImpl(newBacklogItemImportanceRepository());
		return addBacklogItemImportanceUseCase;
	}
	
	public GetBacklogItemImportanceByBacklogItemIdUseCase newGetBacklogItemImportanceByBacklogItemIdUseCase() {
		getBacklogItemImportanceByBacklogItemIdUseCase = new GetBacklogItemImportanceByBacklogItemIdUseCaseImpl(newBacklogItemImportanceRepository());
		return getBacklogItemImportanceByBacklogItemIdUseCase;
	}
	
	public EditBacklogItemImportanceUseCase newEditBacklogItemImportanceUseCase() {
		editBacklogItemImportanceUseCase = new EditBacklogItemImportanceUseCaseImpl(newBacklogItemImportanceRepository());
		return editBacklogItemImportanceUseCase;
	}
	
	public DeleteBacklogItemImportanceUseCase newDeleteBacklogItemImportanceUseCase() {
		deleteBacklogItemImportanceUseCase = new DeleteBacklogItemImportanceUseCaseImpl(newBacklogItemImportanceRepository());
		return deleteBacklogItemImportanceUseCase;
	}
	
	public GetHistoriesByBacklogItemIdUseCase newGetHistoriesByBacklogItemIdUseCase() {
		getHistoriesByBacklogItemIdUseCase = new GetHistoriesByBacklogItemIdUseCaseImpl(newEventStore());
		return getHistoriesByBacklogItemIdUseCase;
	}
}
