package ntut.csie.backlogItemImportanceService.controller.backlogItemImportance;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ntut.csie.backlogItemImportanceService.ApplicationContext;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceModel;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get.GetBacklogItemImportanceByBacklogItemIdInput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get.GetBacklogItemImportanceByBacklogItemIdOutput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get.GetBacklogItemImportanceByBacklogItemIdUseCase;

@Path("/backlog_item_importances")
@Singleton
public class GetBacklogItemImportanceByBacklogItemIdRestfulAPI implements GetBacklogItemImportanceByBacklogItemIdOutput{
	private ApplicationContext applicationContext = ApplicationContext.getInstance();
	private GetBacklogItemImportanceByBacklogItemIdUseCase getBacklogItemImportanceByBacklogItemIdUseCase = applicationContext.newGetBacklogItemImportanceByBacklogItemIdUseCase();
	
	private BacklogItemImportanceModel backlogItemImportance;
	
	@GET
	@Path("/{backlog_item_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized GetBacklogItemImportanceByBacklogItemIdOutput getBacklogItemImportanceByBacklogItemId(@PathParam("backlog_item_id") String backlogItemId) {
		GetBacklogItemImportanceByBacklogItemIdOutput output = this;
		
		GetBacklogItemImportanceByBacklogItemIdInput input = (GetBacklogItemImportanceByBacklogItemIdInput) getBacklogItemImportanceByBacklogItemIdUseCase;
		input.setBacklogItemId(backlogItemId);
		
		getBacklogItemImportanceByBacklogItemIdUseCase.execute(input, output);
		
		return output;
	}

	@Override
	public BacklogItemImportanceModel getBacklogItemImportance() {
		return backlogItemImportance;
	}

	@Override
	public void setBacklogItemImportance(BacklogItemImportanceModel backlogItemImportance) {
		this.backlogItemImportance = backlogItemImportance;
	}
}
