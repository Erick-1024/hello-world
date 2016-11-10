package com.cana.message.mongo.entity;

import org.bson.types.ObjectId;

import com.github.jmkgreen.morphia.annotations.Entity;
import com.github.jmkgreen.morphia.annotations.Id;
import com.github.jmkgreen.morphia.annotations.Index;
import com.github.jmkgreen.morphia.annotations.Indexes;
import com.travelzen.mongo.entity.BasicEntity;

@Entity("mailMessageHistory")
@Indexes({@Index("title"), @Index("sendDateTime")})
public class MMessageHistory extends MailMessageHistory implements BasicEntity {

	private static final long serialVersionUID = -1660822690366282111L;
	
	@Id
	private ObjectId id;

	public MMessageHistory() {
		super();
	}

	public MMessageHistory(MailMessageHistory history) {
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
