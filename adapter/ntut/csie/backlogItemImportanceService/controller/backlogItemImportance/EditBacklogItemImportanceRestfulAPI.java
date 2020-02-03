package ntut.csie.backlogItemImportanceService.controller.backlogItemImportance;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import ntut.csie.backlogItemImportanceService.ApplicationContext;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit.EditBacklogItemImportanceInput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit.EditBacklogItemImportanceOutput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit.EditBacklogItemImportanceUseCase;

@Path("/backlog_item_importances")
@Singleton
public class EditBacklogItemImportanceRestfulAPI implements EditBacklogItemImportanceOutput{
	private ApplicationContext applicationContext = ApplicationContext.getInstance();
	private EditBacklogItemImportanceUseCase editBacklogItemImportanceUseCase = applicationContext.newEditBacklogItemImportanceUseCase();
	
	private boolean editSuccess;
	private String errorMessage;
	
	@PUT
	@Path("/{backlog_item_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized EditBacklogItemImportanceOutput editBacklogItemImportance(
			@PathParam("backlog_item_id") String backlogItemId, 
			String backlogItemImportanceInfo) {
		int importance = 0;
		
		EditBacklogItemImportanceOutput output = this;
		
		try {
			JSONObject backlogItemImportanceJSON = new JSONObject(backlogItemImportanceInfo);
			importance = backlogItemImportanceJSON.getInt("importance");
		} catch (JSONException e) {
			e.printStackTrace();
			output.setEditSuccess(false);
			output.setErrorMessage("Sorry, there is the service problem when edit the importance of the backlog item. Please contact to the system administrator!");
			return output;
		}
		
		EditBacklogItemImportanceInput input = (EditBacklogItemImportanceInput) editBacklogItemImportanceUseCase;
		input.setBacklogItemId(backlogItemId);
		input.setImportance(importance);
		
		editBacklogItemImportanceUseCase.execute(input, output);
		
		return output;
	}

	@Override
	public boolean isEditSuccess() {
		return editSuccess;
	}

	@Override
	public void setEditSuccess(boolean editSuccess) {
		this.editSuccess = editSuccess;
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
