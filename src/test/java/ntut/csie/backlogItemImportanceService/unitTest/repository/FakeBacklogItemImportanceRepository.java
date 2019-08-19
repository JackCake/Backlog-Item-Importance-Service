package ntut.csie.backlogItemImportanceService.unitTest.repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportance;
import ntut.csie.backlogItemImportanceService.useCase.backlogItemImportance.BacklogItemImportanceRepository;

public class FakeBacklogItemImportanceRepository implements BacklogItemImportanceRepository {
	private Map<String, BacklogItemImportance> backlogItemImportances;

	public FakeBacklogItemImportanceRepository() {
		backlogItemImportances = Collections.synchronizedMap(new LinkedHashMap<String, BacklogItemImportance>());
	}
	
	@Override
	public void save(BacklogItemImportance backlogItemImportance) {
		backlogItemImportances.put(backlogItemImportance.getBacklogItemId(), backlogItemImportance);
	}

	@Override
	public void remove(BacklogItemImportance backlogItemImportance) {
		backlogItemImportances.remove(backlogItemImportance.getBacklogItemId());
	}

	@Override
	public BacklogItemImportance getBacklogItemImportanceByBacklogItemId(String backlogItemId) {
		return backlogItemImportances.get(backlogItemId);
	}
	
	public void clearAll() {
		backlogItemImportances.clear();
	}
}
