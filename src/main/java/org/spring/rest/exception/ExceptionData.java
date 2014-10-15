package org.spring.rest.exception;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.ext.JodaSerializers.DateTimeSerializer;

@JsonSerialize(include = Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@XmlAccessorType(XmlAccessType.FIELD)
public class ExceptionData implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("details")
	private ExceptionDetails exceptionDetails;
	@JsonProperty("timeOfRequest")
	private String date;
	
	public ExceptionData() {
	}

	public ExceptionData(ExceptionDetails exceptionDetails, String date) {
		super();
		this.exceptionDetails = exceptionDetails;
		this.date = date;
	}

	public ExceptionDetails getExceptionDetails() {
		return exceptionDetails;
	}

	public void setExceptionDetails(ExceptionDetails exceptionDetails) {
		this.exceptionDetails = exceptionDetails;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
