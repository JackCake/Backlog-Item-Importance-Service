package ntut.csie.backlogItemImportanceService.controller.backlogItemImportance;

import javax.inject.Singleton;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import ntut.csie.backlogItemImportanceService.ApplicationContext;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete.DeleteBacklogItemImportanceInput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete.DeleteBacklogItemImportanceOutput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete.DeleteBacklogItemImportanceUseCase;

@Path("/backlog_item_importances")
@Singleton
public class DeleteBacklogItemImportanceRestfulAPI implements DeleteBacklogItemImportanceOutput{
	private ApplicationContext applicationContext = ApplicationContext.getInstance();
	private DeleteBacklogItemImportanceUseCase deleteBacklogItemImportanceUseCase = applicationContext.newDeleteBacklogItemImportanceUseCase();

	private boolean deleteSuccess;
	private String errorMessage;
	
	@DELETE
	@Path("/{backlog_item_id}")
	public synchronized DeleteBacklogItemImportanceOutput deleteBacklogItemImportance(@PathParam("backlog_item_id") String backlogItemId) {
		DeleteBacklogItemImportanceOutput output = this;
		
		DeleteBacklogItemImportanceInput input = (DeleteBacklogItemImportanceInput) deleteBacklogItemImportanceUseCase;
		input.setBacklogItemId(backlogItemId);
		
		deleteBacklogItemImportanceUseCase.execute(input, output);
		
		return output;
	}

	@Override
	public boolean isDeleteSuccess() {
		return deleteSuccess;
	}

	@Override
	public void setDeleteSuccess(boolean deleteSuccess) {
		this.deleteSuccess = deleteSuccess;
	}

	@Override
	public String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
