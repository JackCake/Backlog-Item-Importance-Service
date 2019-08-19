package ntut.csie.backlogItemImportanceService.useCase.history.get;

import java.util.List;

import ntut.csie.backlogItemImportanceService.useCase.Output;
import ntut.csie.backlogItemImportanceService.useCase.history.HistoryModel;

public interface GetHistoriesByBacklogItemIdOutput extends Output{
	public List<HistoryModel> getHistoryList();
	
	public void setHistoryList(List<HistoryModel> historyList);
}
