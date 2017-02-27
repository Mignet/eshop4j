package com.linkwee.xoss.trace;


public final class LocalSpanThreadBinder {

	private static final ThreadLocal<Span> currentSpan = new ThreadLocal<Span>();
	
    public static Span getCurrentSpan() {
        return currentSpan.get();
    }

    public static void setCurrentSpan(final Span span) {
        if (span == null) {
        	currentSpan.remove();
        } else {
        	currentSpan.set(span);
        }
    }
    
    public static Span getParentSpan(){
    	return getCurrentSpan();
    }
    
    public static void clean(){
    	currentSpan.remove();
    }
    
}
