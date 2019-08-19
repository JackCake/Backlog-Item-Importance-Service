package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit;

import ntut.csie.backlogItemImportanceService.useCase.Output;

public interface EditBacklogItemImportanceOutput extends Output {
	public boolean isEditSuccess();
	
	public void setEditSuccess(boolean editSuccess);
	
	public String getErrorMessage();
	
	public void setErrorMessage(String errorMessage);
}
