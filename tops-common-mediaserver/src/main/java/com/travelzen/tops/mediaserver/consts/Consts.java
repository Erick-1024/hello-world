package com.travelzen.tops.mediaserver.consts;

public class Consts {

	public enum Header {

		/*** define ***/

		Media_Id("Media-Id"),

		Media_Type("Media-Type"),

		Media_Name("Media-Name"),
		
		Media_Cmd("Media-Cmd");


		private final String value;

		Header(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

		public static String[] headerNames() {
			java.util.List<String> names = new java.util.ArrayList<String>();
			for(Header h :values()) {
				names.add(h.getValue());
			}			
			return names.toArray(new String[values().length]);
		}

	}
	
	public enum COMMAND {
		
		none,
		add,
		get,
		crop,
		getmeta,
		delete;
	}

	public static final long UPDATE_FREQUENCY = 60 * 60 * 24 * 7;

	public enum FileType{

	      JPEG("image/jpeg"),
	      
	      JPG("image/jpeg"),

//	      JPG("image/pjpeg"),

	      PNG("image/png"),

//	      X_PNG("image/x-png"),

	      PDF("application/pdf"),

	      DOC("application/msword"),

	      DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),

		  TXT("text/plain");
	     

	      private String contentType;

	     

	      private FileType(String contentType){

	        this.contentType = contentType;

	      }
	      
	      public String contentType(){
	    	  return contentType;
	      }

	   }
}
