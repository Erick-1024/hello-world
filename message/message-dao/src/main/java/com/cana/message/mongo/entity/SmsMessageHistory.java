package com.cana.message.mongo.entity;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Index;
import com.github.jmkgreen.morphia.annotations.Indexes;
import com.travelzen.mongo.entity.BasicEntity;

@Entity("smsMessageHistory")
@Indexes({@Index("receivePhoneNum"), @Index("sendDateTime"), @Index("sendState")})
public class SmsMessageHistory extends SmsMessage implements BasicEntity{

	private static final long serialVersionUID = 4743632491179434604L;

	@Id
	private ObjectId id;
	
	public SmsMessageHistory() {
		super();
	}

	public SmsMessageHistory(SmsMessage history) {
		super(history);
	}
	
	@Override
	public ObjectId getId() {
		return id;
	}

	@Override
	public void setId(ObjectId id) {
		this.id = id;
	}

	@Override
	public void setId(String id) {
		this.id = new ObjectId(id);
	}
}
