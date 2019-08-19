package ntut.csie.backlogItemImportanceService.useCase.history;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportanceBehavior;
import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportanceChanged;

public class ConvertBacklogItemImportanceChangedEventToDTO {
	public static HistoryModel transform(BacklogItemImportanceChanged backlogItemImportanceChanged) {
		HistoryModel dto = new HistoryModel();
		DateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String occurredOn = simpleDateFormat.format(backlogItemImportanceChanged.occurredOn());
		dto.setOccurredOn(occurredOn);
		dto.setBehavior(BacklogItemImportanceBehavior.changeImportance);
		dto.setDescription("\"" + backlogItemImportanceChanged.originalImportance() + "\" → \"" + backlogItemImportanceChanged.newImportance() + "\"");
		return dto;
	}
}
