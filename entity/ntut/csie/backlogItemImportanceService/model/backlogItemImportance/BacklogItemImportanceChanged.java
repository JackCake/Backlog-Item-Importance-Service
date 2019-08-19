package ntut.csie.backlogItemImportanceService.model.backlogItemImportance;

import java.util.Date;

import ntut.csie.backlogItemImportanceService.model.DateProvider;
import ntut.csie.backlogItemImportanceService.model.DomainEvent;

public class BacklogItemImportanceChanged implements DomainEvent {
	private Date occurredOn;
	private String backlogItemId;
	private int originalImportance;
	private int newImportance;

	public BacklogItemImportanceChanged(String backlogItemId, int originalImportance, int newImportance) {
		this.occurredOn = DateProvider.now();
		this.backlogItemId = backlogItemId;
		this.originalImportance = originalImportance;
		this.newImportance = newImportance;
	}
	
	@Override
	public Date occurredOn() {
		return occurredOn;
	}
	
	public String backlogItemId() {
		return backlogItemId;
	}
	
	public int originalImportance() {
		return originalImportance;
	}

	public int newImportance() {
		return newImportance;
	}
}
