package dk.jarry.wildfly.camel.activemq;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

@Named("PROPAGATION_REQUIRED_FOO")
public class CdiRequiredPolicy extends SpringTransactionPolicy {
	
	@Inject
	public CdiRequiredPolicy(CdiTransactionManager cdiTransactionManager) {
		super(new TransactionTemplate(cdiTransactionManager,
				new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED)));
	}
	
}