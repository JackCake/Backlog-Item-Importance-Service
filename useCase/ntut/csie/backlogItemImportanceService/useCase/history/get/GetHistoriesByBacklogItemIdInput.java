package ntut.csie.backlogItemImportanceService.useCase.history.get;

import ntut.csie.backlogItemImportanceService.useCase.Input;

public interface GetHistoriesByBacklogItemIdInput extends Input{
	public String getBacklogItemId();
	
	public void setBacklogItemId(String backlogItemId);
}
