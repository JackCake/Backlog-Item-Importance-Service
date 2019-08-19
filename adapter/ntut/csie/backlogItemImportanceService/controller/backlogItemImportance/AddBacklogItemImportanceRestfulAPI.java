package ntut.csie.backlogItemImportanceService.controller.backlogItemImportance;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import ntut.csie.backlogItemImportanceService.ApplicationContext;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add.AddBacklogItemImportanceInput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add.AddBacklogItemImportanceOutput;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add.AddBacklogItemImportanceUseCase;

@Path("/backlog_item_importances")
@Singleton
public class AddBacklogItemImportanceRestfulAPI implements AddBacklogItemImportanceOutput {
	private ApplicationContext applicationContext = ApplicationContext.getInstance();
	private AddBacklogItemImportanceUseCase addBacklogItemImportanceUseCase = applicationContext.newAddBacklogItemImportanceUseCase();
	
	private boolean addSuccess;
	private String errorMessage;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public synchronized AddBacklogItemImportanceOutput addBacklogItemImportance(String backlogItemImportanceInfo) {
		String backlogItemId = "";
		int importance = 0;
		
		AddBacklogItemImportanceOutput output = this;
		
		try {
			JSONObject backlogItemImportanceJSON = new JSONObject(backlogItemImportanceInfo);
			backlogItemId = backlogItemImportanceJSON.getString("backlogItemId");
			importance = backlogItemImportanceJSON.getInt("importance");
		} catch (JSONException e) {
			e.printStackTrace();
			output.setAddSuccess(false);
			output.setErrorMessage("Sorry, please try again!");
			return output;
		}
		
		AddBacklogItemImportanceInput input = (AddBacklogItemImportanceInput) addBacklogItemImportanceUseCase;
		input.setBacklogItemId(backlogItemId);
		input.setImportance(importance);
		
		addBacklogItemImportanceUseCase.execute(input, output);
		
		return output;
	}

	@Override
	public boolean isAddSuccess() {
		return addSuccess;
	}

	@Override
	public void setAddSuccess(boolean addSuccess) {
		this.addSuccess = addSuccess;
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
