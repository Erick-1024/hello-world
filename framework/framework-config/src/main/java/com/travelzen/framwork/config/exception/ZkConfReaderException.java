package com.travelzen.framwork.config.exception;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;

public class ZkConfReaderException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private KeeperException keeperException;
	private IOException ioException;

	public ZkConfReaderException(Exception exception) {
		super(exception);
	}

	public ZkConfReaderException(String message) {
		super(message);
	}

	public ZkConfReaderException(IOException ioException) {
		super(String.format("[retMsg=%s]", ioException.getLocalizedMessage()));
		this.setIoException(ioException);

	}

	public ZkConfReaderException(KeeperException keeperException) {
		super(String.format("[retCode=%s,retMsg=%s]", keeperException.code(), keeperException.getLocalizedMessage()));
		this.setKeeperException(keeperException);

	}

	public ZkConfReaderException(InterruptedException e) {
		super(e);
		Thread.currentThread().interrupt();
	}

	public KeeperException getKeeperException() {
		return keeperException;
	}

	public void setKeeperException(KeeperException keeperException) {
		this.keeperException = keeperException;
	}

	public IOException getIoException() {
		return ioException;
	}

	public void setIoException(IOException ioException) {
		this.ioException = ioException;
	}
}
