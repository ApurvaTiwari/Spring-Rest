package org.spring.rest.exception;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.http.HttpStatus;

@JsonSerialize(include=Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown=true)
@XmlAccessorType(XmlAccessType.FIELD)
public class ExceptionDetails implements Serializable {
	
	private static final long serialVersionUID = -2911042116001467402L;
	
	@JsonProperty("statusCode")
	private HttpStatus statusCode;
	@JsonProperty("message")
	private String messsage;
	@JsonProperty("causedBy")
	private List<String> probableCauses;
	
	public ExceptionDetails() {
		// TODO Auto-generated constructor stub
	}

	public ExceptionDetails(HttpStatus statusCode, String messsage,
			List<String> probableCauses) {
		super();
		this.statusCode = statusCode;
		this.messsage = messsage;
		this.probableCauses = probableCauses;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getMesssage() {
		return messsage;
	}

	public void setMesssage(String messsage) {
		this.messsage = messsage;
	}

	public List<String> getProbableCauses() {
		return probableCauses;
	}

	public void setProbableCauses(List<String> probableCauses) {
		this.probableCauses = probableCauses;
	}

	@Override
	public String toString() {
		return "ExceptionDetails [statusCode=" + statusCode + ", messsage="
				+ messsage + ", probableCauses=" + probableCauses + "]";
	}
	
	

}
