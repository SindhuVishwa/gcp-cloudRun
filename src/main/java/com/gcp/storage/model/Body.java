package com.gcp.storage.model;

public class Body {

	  private Message message;
	  

	  public Body() {}

	  public Message getMessage() {
	    return message;
	  }

	  public void setMessage(Message message) {
	    this.message = message;
	  }

	  public class Message {

	    private String messageId;
	    private String publishTime;
	    private String data;
	    private Attributes attribute;

	    public Message() {}

	    public Message(String messageId, String publishTime, String data, Attributes attribute) {
	      this.messageId = messageId;
	      this.publishTime = publishTime;
	      this.data = data;
	      this.attribute = attribute;
	    }

	    public String getMessageId() {
	      return messageId;
	    }

	    public void setMessageId(String messageId) {
	      this.messageId = messageId;
	    }

	    public String getPublishTime() {
	      return publishTime;
	    }

	    public void setPublishTime(String publishTime) {
	      this.publishTime = publishTime;
	    }

	    public String getData() {
	      return data;
	    }

	    public void setData(String data) {
	      this.data = data;
	    }

		public Attributes getAttribute() {
			return attribute;
		}

		public void setAttribute(Attributes attribute) {
			this.attribute = attribute;
		}
	  }
	  
	  public class Attributes {
		  
		  private String bucketId;
		  private String objectId;
		    
		  public Attributes()
			{				
			}
			public Attributes(String bucketId, String objectId) {
				super();
				this.bucketId = bucketId;
				this.objectId = objectId;
			}
			public String getBucketId() {
				return bucketId;
			}
			public void setBucketId(String bucketId) {
				this.bucketId = bucketId;
			}
			public String getObjectId() {
				return objectId;
			}
			public void setObjectId(String objectId) {
				this.objectId = objectId;
			}
		    
		    
		  
	  }
	  
	}