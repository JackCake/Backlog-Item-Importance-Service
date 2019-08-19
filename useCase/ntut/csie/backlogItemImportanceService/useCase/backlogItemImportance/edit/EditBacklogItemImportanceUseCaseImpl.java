package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.edit;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceRepository;

public class EditBacklogItemImportanceUseCaseImpl implements EditBacklogItemImportanceUseCase, EditBacklogItemImportanceInput {
	private BacklogItemImportanceRepository backlogItemImportanceRepository;
	
	private String backlogItemId;
	private int importance;
	
	public EditBacklogItemImportanceUseCaseImpl(BacklogItemImportanceRepository backlogItemImportanceRepository) {
		this.backlogItemImportanceRepository = backlogItemImportanceRepository;
	}
	
	@Override
	public void execute(EditBacklogItemImportanceInput input, EditBacklogItemImportanceOutput output) {
		BacklogItemImportance backlogItemImportance = backlogItemImportanceRepository.getBacklogItemImportanceByBacklogItemId(input.getBacklogItemId());
		if(backlogItemImportance == null) {
			output.setEditSuccess(false);
			output.setErrorMessage("Sorry, the importance of the backlog item is not exist!");
			return;
		}
		backlogItemImportance.changeImportance(input.getImportance());
		try {
			backlogItemImportanceRepository.save(backlogItemImportance);
		} catch (Exception e) {
			output.setEditSuccess(false);
			output.setErrorMessage(e.getMessage());
			return;
		}
		output.setEditSuccess(true);
	}

	@Override
	public String getBacklogItemId() {
		return backlogItemId;
	}

	@Override
	public void setBacklogItemId(String backlogItemId) {
		this.backlogItemId = backlogItemId;
	}

	@Override
	public int getImportance() {
		return importance;
	}

	@Override
	public void setImportance(int importance) {
		this.importance = importance;
	}
}
