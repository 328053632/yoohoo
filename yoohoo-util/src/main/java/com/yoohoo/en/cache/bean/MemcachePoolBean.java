package com.yoohoo.en.cache.bean;

public class MemcachePoolBean {
	private String poolId;
	private String addressList;
	private int connectSize;
	private int mergeFactor;
	private int initBufSize;
	private int maxBufSize;
	private int compressionThreshold;
	private long connectTimeout;
	private boolean isDefaultTranscode;
	private boolean failureMode;

	public String getPoolId() {
		return poolId;
	}

	public void setPoolId(String poolId) {
		this.poolId = poolId;
	}

	public String getAddressList() {
		return addressList;
	}

	public void setAddressList(String addressList) {
		this.addressList = addressList;
	}

	public int getConnectSize() {
		return connectSize;
	}

	public void setConnectSize(int connectSize) {
		this.connectSize = connectSize;
	}

	public int getMergeFactor() {
		return mergeFactor;
	}

	public void setMergeFactor(int mergeFactor) {
		this.mergeFactor = mergeFactor;
	}

	public int getInitBufSize() {
		return initBufSize;
	}

	public void setInitBufSize(int initBufSize) {
		this.initBufSize = initBufSize;
	}

	public int getMaxBufSize() {
		return maxBufSize;
	}

	public void setMaxBufSize(int maxBufSize) {
		this.maxBufSize = maxBufSize;
	}

	public int getCompressionThreshold() {
		return compressionThreshold;
	}

	public void setCompressionThreshold(int compressionThreshold) {
		this.compressionThreshold = compressionThreshold;
	}

	public long getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(long connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public boolean isDefaultTranscode() {
		return isDefaultTranscode;
	}

	public void setDefaultTranscode(boolean isDefaultTranscode) {
		this.isDefaultTranscode = isDefaultTranscode;
	}

	public boolean isFailureMode() {
		return failureMode;
	}

	public void setFailureMode(boolean failureMode) {
		this.failureMode = failureMode;
	}

}
