package ntut.csie.backlogItemImportanceService.model.backlogItemImportance;

import ntut.csie.backlogItemImportanceService.model.DomainEventPublisher;

public class BacklogItemImportance {
	private String backlogItemId;
	private int importance;
	
	public BacklogItemImportance() {}
	
	public BacklogItemImportance(String backlogItemId, int importance) {
		this.backlogItemId = backlogItemId;
		this.importance = importance;
	}
	
	public void changeImportance(int importance) {
		if(this.importance == importance) {
			return;
		}
		DomainEventPublisher.getInstance().publish(new BacklogItemImportanceChanged(
				backlogItemId, this.importance, importance));
		this.setImportance(importance);
	}
	
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
