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
	
	public BacklogItemImportance build() throws Exception {
		String exceptionMessage = "";
		if(backlogItemId == null || backlogItemId.isEmpty()) {
			exceptionMessage += "The backlog item id of the backlog item importance should be required!\n";
		}
		if(!exceptionMessage.isEmpty()) {
			throw new Exception(exceptionMessage);
		}
		
		BacklogItemImportance backlogItemImportance = new BacklogItemImportance(backlogItemId, importance);
		return backlogItemImportance;
	}
}
