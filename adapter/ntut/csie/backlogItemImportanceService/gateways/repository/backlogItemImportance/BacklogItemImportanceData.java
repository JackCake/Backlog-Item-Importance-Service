package ntut.csie.backlogItemImportanceService.gateways.repository.backlogItemImportance;

public class BacklogItemImportanceData {
	private String backlogItemId;
	private int importance;
	
	public String getBacklogItemId() {
		return backlogItemId;
	}
	
	public void setBacklogItemId(String backlogItemId) {
		this.backlogItemId = backlogItemId;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}
}
