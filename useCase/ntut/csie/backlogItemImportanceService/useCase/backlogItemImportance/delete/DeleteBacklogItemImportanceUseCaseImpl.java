package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.delete;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceRepository;

public class DeleteBacklogItemImportanceUseCaseImpl implements DeleteBacklogItemImportanceUseCase, DeleteBacklogItemImportanceInput {
	private BacklogItemImportanceRepository backlogItemImportanceRepository;
	
	private String backlogItemId;
	
	public DeleteBacklogItemImportanceUseCaseImpl(BacklogItemImportanceRepository backlogItemImportanceRepository) {
		this.backlogItemImportanceRepository = backlogItemImportanceRepository;
	}
	
	@Override
	public void execute(DeleteBacklogItemImportanceInput input, DeleteBacklogItemImportanceOutput output) {
		BacklogItemImportance backlogItemImportance = backlogItemImportanceRepository.getBacklogItemImportanceByBacklogItemId(input.getBacklogItemId());
		if(backlogItemImportance == null) {
			output.setDeleteSuccess(false);
			output.setErrorMessage("Sorry, the importance of the backlog item is not exist!");
			return;
		}
		try {
			backlogItemImportanceRepository.remove(backlogItemImportance);
		} catch (Exception e) {
			output.setDeleteSuccess(false);
			output.setErrorMessage(e.getMessage());
			return;
		}
		output.setDeleteSuccess(true);
	}

	@Override
	public String getBacklogItemId() {
		return backlogItemId;
	}

	@Override
	public void setBacklogItemId(String backlogItemId) {
		this.backlogItemId = backlogItemId;
	}
}
