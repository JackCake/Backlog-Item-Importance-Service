package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance;

public class BacklogItemImportanceModel {
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
