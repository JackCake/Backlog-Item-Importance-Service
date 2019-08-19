package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;

public interface BacklogItemImportanceRepository {
	public void save(BacklogItemImportance backlogItemImportance) throws Exception;
	
	public void remove(BacklogItemImportance backlogItemImportance) throws Exception;
	
	public BacklogItemImportance getBacklogItemImportanceByBacklogItemId(String backlogItemId);
}
