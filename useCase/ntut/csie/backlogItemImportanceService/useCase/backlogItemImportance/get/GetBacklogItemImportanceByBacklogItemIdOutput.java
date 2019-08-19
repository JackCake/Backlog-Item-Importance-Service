package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get;

import ntut.csie.backlogItemImportanceService.useCase.Output;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceModel;

public interface GetBacklogItemImportanceByBacklogItemIdOutput extends Output {
	public BacklogItemImportanceModel getBacklogItemImportance();
	
	public void setBacklogItemImportance(BacklogItemImportanceModel backlogItemImportance);
}
