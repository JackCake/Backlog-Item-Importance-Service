package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit;

import ntut.csie.backlogItemImportanceService.useCase.Input;

public interface EditBacklogItemImportanceInput extends Input {
	public String getBacklogItemId();
	
	public void setBacklogItemId(String backlogItemId);
	
	public int getImportance();
	
	public void setImportance(int importance);
}
