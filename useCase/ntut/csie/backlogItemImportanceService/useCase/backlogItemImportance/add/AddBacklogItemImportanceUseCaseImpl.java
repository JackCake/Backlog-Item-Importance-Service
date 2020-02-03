package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.add;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;
import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportanceBuilder;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceRepository;

public class AddBacklogItemImportanceUseCaseImpl implements AddBacklogItemImportanceUseCase, AddBacklogItemImportanceInput {
	private BacklogItemImportanceRepository backlogItemImportanceRepository;
	
	private String backlogItemId;
	private int importance;
	
	public AddBacklogItemImportanceUseCaseImpl(BacklogItemImportanceRepository backlogItemImportanceRepository) {
		this.backlogItemImportanceRepository = backlogItemImportanceRepository;
	}
	
	@Override
	public void execute(AddBacklogItemImportanceInput input, AddBacklogItemImportanceOutput output) {
		try {
			BacklogItemImportance backlogItemImportance = BacklogItemImportanceBuilder.newInstance()
					.backlogItemId(input.getBacklogItemId())
					.importance(input.getImportance())
					.build();
			backlogItemImportanceRepository.save(backlogItemImportance);
		} catch (Exception e) {
			output.setAddSuccess(false);
			output.setErrorMessage(e.getMessage());
			return;
		}
		output.setAddSuccess(true);
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
