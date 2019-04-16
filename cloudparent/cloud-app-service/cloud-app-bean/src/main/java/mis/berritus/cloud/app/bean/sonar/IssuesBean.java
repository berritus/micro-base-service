package mis.berritus.cloud.app.bean.sonar;

import mis.berritus.cloud.bean.base.MisBean;

/**
 * @Program: cloud-parent
 * @Copyright
 * @Author: Qin Guihe
 * @Create: 2019-04-16 21:14
 * @Description:
 */
public class IssuesBean extends MisBean {
	private static final long serialVersionUID = 4522696953451920265L;
	private String key;
	private String rule;
	private String severity;
	private String component;
	private String project;
	private Integer line;
	private String hash;
	private String flows;
	private String status;
	private String message;
	private String effort;
	private String debt;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Integer getLine() {
		return line;
	}

	public void setLine(Integer line) {
		this.line = line;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getFlows() {
		return flows;
	}

	public void setFlows(String flows) {
		this.flows = flows;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEffort() {
		return effort;
	}

	public void setEffort(String effort) {
		this.effort = effort;
	}

	public String getDebt() {
		return debt;
	}

	public void setDebt(String debt) {
		this.debt = debt;
	}
}
