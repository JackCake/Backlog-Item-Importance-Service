package ntut.csie.backlogItemImportanceService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import ntut.csie.backlogItemImportanceService.gateways.database.SqlDatabaseHelper;
import ntut.csie.backlogItemImportanceService.useCase.DomainEventListener;

@SuppressWarnings("serial")
public class BacklogItemImportanceServiceStart extends HttpServlet implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Backlog Item Importance Service Start!");
		SqlDatabaseHelper sqlDatabaseHelper = new SqlDatabaseHelper();
		sqlDatabaseHelper.initialize();
		ApplicationContext context = ApplicationContext.getInstance();
		DomainEventListener.getInstance().init(context.newEventStore());
	}
}