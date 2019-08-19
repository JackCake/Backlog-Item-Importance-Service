package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add;

import ntut.csie.backlogItemImportanceService.useCase.Input;

public interface AddBacklogItemImportanceInput extends Input {
	public String getBacklogItemId();
	
	public void setBacklogItemId(String backlogItemId);
	
	public int getImportance();
	
	public void setImportance(int importance);
}
