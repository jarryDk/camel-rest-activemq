package dk.jarry.wildfly.camel.activemq.todo.control;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.camel.component.jpa.JpaComponent;
import org.springframework.transaction.PlatformTransactionManager;

import dk.jarry.wildfly.camel.activemq.CdiTransactionManager;

/**
 * https://github.com/wildfly-extras/wildfly-camel-examples/blob/master/camel-jpa/src/main/java/org/wildfly/camel/examples/jpa/producer/JPAComponentProducer.java
 * 
 * @author Michael Bornholdt Nielsen
 *
 */
public class JPAComponentProducer {

	@Inject
	CdiTransactionManager transactionManager;

	@PersistenceContext(unitName = "camel")
	private EntityManager em;

	@Produces
	@ApplicationScoped
	@Named("jpa")
	public JpaComponent jpaComponent() {
		JpaComponent component = new JpaComponent();
		component.setTransactionManager((PlatformTransactionManager) transactionManager);
		component.setEntityManagerFactory(em.getEntityManagerFactory());
		return component;
	}

}
