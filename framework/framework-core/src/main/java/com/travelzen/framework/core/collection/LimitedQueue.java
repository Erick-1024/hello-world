package com.travelzen.framework.core.collection;

import java.util.concurrent.LinkedBlockingQueue;

public class LimitedQueue <E> extends LinkedBlockingQueue<E>{

	private static final long serialVersionUID = 1408352736889928792L;

	public LimitedQueue(int maxSize){
        super(maxSize);
    }

    @Override
    public boolean offer(E e){
        // turn offer() and add() into a blocking calls (unless interrupted)
        try {
            put(e);
            return true;
        } catch(InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        return false;
    }

}
