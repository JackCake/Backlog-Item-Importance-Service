package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get;

import ntut.csie.backlogItemImportanceService.useCase.Input;

public interface GetBacklogItemImportanceByBacklogItemIdInput extends Input {
	public String getBacklogItemId();
	
	public void setBacklogItemId(String backlogItemId);
}
