package ntut.csie.backlogItemImportanceService.useCase;

import ntut.csie.backlogItemImportanceService.model.DomainEventPublisher;
import ntut.csie.backlogItemImportanceService.model.DomainEventSubscriber;
import ntut.csie.backlogItemImportanceService.model.backlogItemImportance.BacklogItemImportanceChanged;

public class DomainEventListener {
	private static DomainEventListener instance = null;
	
	private EventStore eventStore;
	
	private DomainEventListener() {}
	
	public static synchronized DomainEventListener getInstance() {
		if(instance == null) {
			instance = new DomainEventListener();
		}
		return instance;
	}
	
	public void init(EventStore eventStore) {
		this.eventStore = eventStore;
		DomainEventPublisher.getInstance().reset();
		eventSubscribe();
	}
	
	private void eventSubscribe() {
		DomainEventPublisher.getInstance().subscribe(new DomainEventSubscriber<BacklogItemImportanceChanged>() {

			@Override
			public void handleEvent(BacklogItemImportanceChanged domainEvent) {
				eventStore.save(domainEvent);
			}

			@Override
			public Class<BacklogItemImportanceChanged> subscribedToEventType() {
				return BacklogItemImportanceChanged.class;
			}
           
        });
	}
}
