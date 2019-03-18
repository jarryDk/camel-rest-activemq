package dk.jarry.wildfly.camel.activemq.todo.entity;

import static javax.persistence.TemporalType.DATE;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.config.PropertyVisibilityStrategy;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 * 
 * https://github.com/wildfly-extras/wildfly-camel-examples/blob/master/camel-jpa/src/main/java/org/wildfly/camel/examples/jpa/model/Order.java
 * 
 * @author Michael Bornholdt Nielsen
 *
 */
@Entity
public class ToDo implements Serializable {

	private static final long serialVersionUID = -8388229003597893949L;

	@Id
	@GeneratedValue
	private Long id;

	private String subject;
	private String body;

	@Temporal(DATE)
	private Date created;
	@Temporal(DATE)
	private Date updated;

	@Temporal(DATE)
	private Date start;
	@Temporal(DATE)
	private Date end;

	private Integer priority;
	private Integer importens;

	private String owner;
	private String createBy;
	private String updatedBy;

	public ToDo() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getImportens() {
		return importens;
	}

	public void setImportens(Integer importens) {
		this.importens = importens;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "ToDo{" + "id=" + id + ", subject=" + subject + ", body=" + body + ", created=" + created + ", updated="
				+ updated + ", start=" + start + ", end=" + end + ", priority=" + priority + ", importens=" + importens
				+ ", owner=" + owner + ", createBy=" + createBy + ", updatedBy=" + updatedBy + '}';
	}
	
	public String toJson() {
		// https://developer.ibm.com/articles/j-javaee8-json-binding-1/
		JsonbConfig config = new JsonbConfig().withPropertyVisibilityStrategy(new PropertyVisibilityStrategy() {
			@Override
			public boolean isVisible(Method method) {
				return true;
			}
			
			@Override
			public boolean isVisible(Field field) {
				return false;
			}
		});
		
		config.withPropertyOrderStrategy(PropertyOrderStrategy.LEXICOGRAPHICAL);
		
		return JsonbBuilder.newBuilder().withConfig(config).build().toJson(this);
	}
	
	static public ToDo fromJson(String json) {		
		return JsonbBuilder.create().fromJson(json, ToDo.class);		
	}

}
