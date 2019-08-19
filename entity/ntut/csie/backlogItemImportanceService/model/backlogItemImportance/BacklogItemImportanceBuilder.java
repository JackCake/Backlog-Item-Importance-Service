package ntut.csie.backlogItemImportanceService.model.backlogItemImportance;

public class BacklogItemImportanceBuilder {
	private String backlogItemId;
	private int importance;
	
	public static BacklogItemImportanceBuilder newInstance() {
		return new BacklogItemImportanceBuilder();
	}
	
	public BacklogItemImportanceBuilder backlogItemId(String backlogItemId) {
		this.backlogItemId = backlogItemId;
		return this;
	}
	
	public BacklogItemImportanceBuilder importance(int importance) {
		this.importance = importance;
		return this;
	}
	
	public BacklogItemImportance build() {
		BacklogItemImportance backlogItemImportance = new BacklogItemImportance(backlogItemId, importance);
		return backlogItemImportance;
	}
}
