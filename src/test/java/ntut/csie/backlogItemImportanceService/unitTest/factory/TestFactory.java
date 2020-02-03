package ntut.csie.backlogItemImportanceService.unitTest.factory;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;
import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportanceBuilder;
import ntut.csie.backlogItemImportanceService.unitTest.repository.FakeBacklogItemImportanceRepository;

public class TestFactory {
	private FakeBacklogItemImportanceRepository fakeBacklogItemImportanceRepository;
	
	public TestFactory(FakeBacklogItemImportanceRepository fakeBacklogItemImportanceRepository) {
		this.fakeBacklogItemImportanceRepository = fakeBacklogItemImportanceRepository;
	}
	
	public BacklogItemImportance newBacklogItemImportance(int importance, String backlogItemId) {
		BacklogItemImportance backlogItemImportance = null;
		try {
			backlogItemImportance = BacklogItemImportanceBuilder.newInstance()
					.backlogItemId(backlogItemId)
					.importance(importance)
					.build();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		fakeBacklogItemImportanceRepository.save(backlogItemImportance);
		return backlogItemImportance;
	}
}
