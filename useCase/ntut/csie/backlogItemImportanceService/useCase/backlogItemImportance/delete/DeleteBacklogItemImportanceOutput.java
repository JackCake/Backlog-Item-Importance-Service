package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete;

import ntut.csie.backlogItemImportanceService.useCase.Output;

public interface DeleteBacklogItemImportanceOutput extends Output {
	public boolean isDeleteSuccess();
	
	public void setDeleteSuccess(boolean deleteSuccess);
	
	public String getErrorMessage();
	
	public void setErrorMessage(String errorMessage);
}
