package dk.jarry.wildfly.camel.activemq;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.springframework.transaction.jta.JtaTransactionManager;

@Named("transactionManager")
public class CdiTransactionManager extends JtaTransactionManager {

	private static final long serialVersionUID = -1806738474702612290L;

	@Resource(mappedName = "java:/TransactionManager")
	private TransactionManager transactionManager;

	@Resource
	private UserTransaction userTransaction;

	@PostConstruct
	public void initTransactionManager() {
		setTransactionManager(transactionManager);
		setUserTransaction(userTransaction);
	}
	
}
