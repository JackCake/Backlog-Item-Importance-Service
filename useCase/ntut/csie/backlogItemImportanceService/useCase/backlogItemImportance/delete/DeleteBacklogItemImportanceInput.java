package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete;

import ntut.csie.backlogItemImportanceService.useCase.Input;

public interface DeleteBacklogItemImportanceInput extends Input {
	public String getBacklogItemId();
	
	public void setBacklogItemId(String backlogItemId);
}
