package ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.get;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceRepository;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.ConvertBacklogItemImportanceToDTO;

public class GetBacklogItemImportanceByBacklogItemIdUseCaseImpl implements GetBacklogItemImportanceByBacklogItemIdUseCase, GetBacklogItemImportanceByBacklogItemIdInput {
	private BacklogItemImportanceRepository backlogItemImportanceRepository;
	
	private String backlogItemId;
	
	public GetBacklogItemImportanceByBacklogItemIdUseCaseImpl(BacklogItemImportanceRepository backlogItemImportanceRepository) {
		this.backlogItemImportanceRepository = backlogItemImportanceRepository;
	}
	
	@Override
	public void execute(GetBacklogItemImportanceByBacklogItemIdInput input,
			GetBacklogItemImportanceByBacklogItemIdOutput output) {
		BacklogItemImportance backlogItemImportance = backlogItemImportanceRepository.getBacklogItemImportanceByBacklogItemId(input.getBacklogItemId());
		if(backlogItemImportance != null) {
			output.setBacklogItemImportance(ConvertBacklogItemImportanceToDTO.transform(backlogItemImportance));
		}
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
