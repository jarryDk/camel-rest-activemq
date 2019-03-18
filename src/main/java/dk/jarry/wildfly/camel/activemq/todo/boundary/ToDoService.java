package dk.jarry.wildfly.camel.activemq.todo.boundary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSRuntimeException;
import javax.jms.Queue;

import dk.jarry.wildfly.camel.activemq.todo.entity.ToDo;

/**
 * https://github.com/javaee-samples/javaee7-samples/tree/master/jms/send-receive/src/main/java/org/javaee7/jms/send/receive
 * 
 * @author Michael Bornholdt Nielsen
 *
 */
@Stateless
public class ToDoService {

	static Map<Long, ToDo> db = new HashMap<Long, ToDo>();

	@Inject
	JMSContext context;

	@Resource(mappedName = "java:/jms/queue/ToDo")
	Queue toDoQueue;

	public ToDoService() {
	}

	@PostConstruct
	public void init() {
		ToDo todo = new ToDo();
		todo.setId(Long.parseLong("17"));
		todo.setSubject("Subject");
		todo.setBody("Body");
		db.put(todo.getId(), todo);

	}

	public ToDo create(ToDo todo) {
		db.put(todo.getId(), todo);
		return todo;
	}

	public ToDo read(Class<ToDo> class1, Long id) {
		ToDo todo = db.get(id);
		if (todo != null) {
			sendJMSMessage(todo);
		}

		return todo;
	}

	public void update(ToDo todo) {
		db.put(todo.getId(), todo);
	}

	public void delete(Class<ToDo> class1, Long id) {
		db.remove(id);

	}

	public List<ToDo> list(Long from, Long limit) {
		return null;
	}

	/**
	 * Send a message to the JMS queue.
	 *
	 * @param message the contents of the message.
	 * @throws JMSRuntimeException if an error occurs in accessing the queue.
	 */
	public void sendJMSMessage(ToDo todo) throws JMSRuntimeException {
		context.createProducer().send(toDoQueue, todo.toJson());
	}

}
