package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add;

import ntut.csie.backlogItemImportanceService.useCase.Output;

public interface AddBacklogItemImportanceOutput extends Output {
	public boolean isAddSuccess();
	
	public void setAddSuccess(boolean addSuccess);
	
	public String getErrorMessage();
	
	public void setErrorMessage(String errorMessage);
}
