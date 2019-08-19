package ntut.csie.backlogItemImportanceService.gateways.repository.backlogItemImportance;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;

public class BacklogItemImportanceMapper {
	public BacklogItemImportance transformToBacklogItemImportance(BacklogItemImportanceData data) {
		BacklogItemImportance backlogItemImportance = new BacklogItemImportance();
		backlogItemImportance.setBacklogItemId(data.getBacklogItemId());
		backlogItemImportance.setImportance(data.getImportance());
		return backlogItemImportance;
	}
	
	public BacklogItemImportanceData transformToBacklogItemImportanceData(BacklogItemImportance backlogItemImportance) {
		BacklogItemImportanceData data = new BacklogItemImportanceData();
		data.setBacklogItemId(backlogItemImportance.getBacklogItemId());
		data.setImportance(backlogItemImportance.getImportance());
		return data;
	}
}
