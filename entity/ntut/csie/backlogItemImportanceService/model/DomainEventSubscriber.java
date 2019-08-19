package ntut.csie.backlogItemImportanceService.model;

public interface DomainEventSubscriber<T> {
	public void handleEvent(final T domainEvent);
	public Class<T> subscribedToEventType();
}
