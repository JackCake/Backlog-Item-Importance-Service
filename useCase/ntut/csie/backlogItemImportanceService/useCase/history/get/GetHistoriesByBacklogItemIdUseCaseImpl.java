package ntut.csie.backlogItemImportanceService.useCase.history.get;

import java.util.ArrayList;
import java.util.List;

import ntut.csie.backlogItemImportanceService.model.DomainEvent;
import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportanceChanged;
import ntut.csie.backlogItemImportanceService.useCase.EventStore;
import ntut.csie.backlogItemImportanceService.useCase.history.ConvertBacklogItemImportanceChangedEventToDTO;
import ntut.csie.backlogItemImportanceService.useCase.history.HistoryModel;

public class GetHistoriesByBacklogItemIdUseCaseImpl implements GetHistoriesByBacklogItemIdUseCase, GetHistoriesByBacklogItemIdInput {
	private EventStore eventStore;
	
	private String backlogItemId;
	
	public GetHistoriesByBacklogItemIdUseCaseImpl(EventStore eventStore) {
		this.eventStore = eventStore;
	}
	
	@Override
	public void execute(GetHistoriesByBacklogItemIdInput input, GetHistoriesByBacklogItemIdOutput output) {
		String backlogItemId = input.getBacklogItemId();
		List<HistoryModel> historyList = new ArrayList<>();
		for(DomainEvent domainEvent : eventStore.getAllEvent()) {
			if(domainEvent instanceof BacklogItemImportanceChanged) {
				BacklogItemImportanceChanged backlogItemImportanceChanged = (BacklogItemImportanceChanged) domainEvent;
				if(backlogItemImportanceChanged.backlogItemId().equals(backlogItemId)) {
					historyList.add(ConvertBacklogItemImportanceChangedEventToDTO.transform(backlogItemImportanceChanged));
				}
			}
		}
		output.setHistoryList(historyList);
	}

	@Override
	public String getBacklogItemId() {
		return backlogItemId;
	}

	@Override
	public void setBacklogItemId(String backlogItemId) {
		this.backlogItemId = backlogItemId;
	}
}
