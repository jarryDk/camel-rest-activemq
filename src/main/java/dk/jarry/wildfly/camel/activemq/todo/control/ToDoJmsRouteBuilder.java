package dk.jarry.wildfly.camel.activemq.todo.control;


import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.ConnectionFactory;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
// import org.apache.camel.component.jms.JmsComponent;

import dk.jarry.wildfly.camel.activemq.todo.entity.ToDo;

@ApplicationScoped
@ContextName("camel-jms-mdb-context")
public class ToDoJmsRouteBuilder extends RouteBuilder {

    @Resource(mappedName = "java:jboss/DefaultJMSConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Override
    public void configure() throws Exception {
  
        /**
         * This route is invoked by the {@link MessageDrivenBean} message consumer and outputs
         * the message payload to the console log
         */
        from("direct:jmsIn")
        	.toF("jpa:%s", ToDo.class.getName())
        	.log("Received message: ${body}");
}

}
