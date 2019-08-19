package ntut.csie.backlogItemImportanceService.controller.history;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ntut.csie.backlogItemImportanceService.ApplicationContext;
import ntut.csie.backlogItemImportanceService.useCase.history.HistoryModel;
import ntut.csie.backlogItemImportanceService.useCase.history.get.GetHistoriesByBacklogItemIdInput;
import ntut.csie.backlogItemImportanceService.useCase.history.get.GetHistoriesByBacklogItemIdOutput;
import ntut.csie.backlogItemImportanceService.useCase.history.get.GetHistoriesByBacklogItemIdUseCase;

@Path("/backlog_item_importances/{backlog_item_id}/histories")
@Singleton
public class GetHistoriesByBacklogItemIdRestfulAPI implements GetHistoriesByBacklogItemIdOutput {
	private ApplicationContext applicationContext = ApplicationContext.getInstance();
	private GetHistoriesByBacklogItemIdUseCase getHistoriesByBacklogItemIdUseCase = applicationContext.newGetHistoriesByBacklogItemIdUseCase();
	
	private List<HistoryModel> historyList;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized GetHistoriesByBacklogItemIdOutput getHistoriesByBacklogItemId(@PathParam("backlog_item_id") String backlogItemId) {
		GetHistoriesByBacklogItemIdOutput output = this;
		
		GetHistoriesByBacklogItemIdInput input = (GetHistoriesByBacklogItemIdInput) getHistoriesByBacklogItemIdUseCase;
		input.setBacklogItemId(backlogItemId);
		
		getHistoriesByBacklogItemIdUseCase.execute(input, output);
		
		return output;
	}
	
	@Override
	public List<HistoryModel> getHistoryList() {
		return historyList;
	}

	@Override
	public void setHistoryList(List<HistoryModel> historyList) {
		this.historyList = historyList;
	}
}
