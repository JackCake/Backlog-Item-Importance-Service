package ntut.csie.backlogItemImportanceService.useCase;

public interface UseCase<I extends Input, O extends Output> {
	void execute(I input, O output);
}
