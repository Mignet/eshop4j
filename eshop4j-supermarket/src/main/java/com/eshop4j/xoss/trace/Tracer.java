package com.eshop4j.xoss.trace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Tracer {
	

		private static final Logger LOGGER = LoggerFactory.getLogger(Tracer.class);
	
	 	public static Span getCurrentSpan() {
	        return LocalSpanThreadBinder.getCurrentSpan();
	    }

	    public static void setCurrentSpan(final Span span) {
	    	LocalSpanThreadBinder.setCurrentSpan(span);
	    }
	    
	    public static Span getParentSpan(){
	    	return LocalSpanThreadBinder.getParentSpan();
	    }
	    
	    public static void clean(){
	    	LocalSpanThreadBinder.clean();
	    }
	    
	    public static void currentSpanEnd(String status){
	    	if(getCurrentSpan()==null)return;
	    	getCurrentSpan().setStatus(status);
	    	getCurrentSpan().setEndTime(System.currentTimeMillis());
	    	getCurrentSpan().setTimestamp(getCurrentSpan().getEndTime()-getCurrentSpan().getStartTime());
	    	LOGGER.info(getCurrentSpan().toString());
	    }
	      
}
