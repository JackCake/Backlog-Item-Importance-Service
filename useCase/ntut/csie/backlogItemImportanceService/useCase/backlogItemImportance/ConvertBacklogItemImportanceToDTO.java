package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;

public class ConvertBacklogItemImportanceToDTO {
	public static BacklogItemImportanceModel transform(BacklogItemImportance backlogItemImportance) {
		BacklogItemImportanceModel dto = new BacklogItemImportanceModel();
		dto.setBacklogItemId(backlogItemImportance.getBacklogItemId());
		dto.setImportance(backlogItemImportance.getImportance());
		return dto;
	}
}
