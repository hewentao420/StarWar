package edu.toronto.ece1779.ec2.entity;

public class ManagerConfig {

	private double thresholdGrow;
	private double thresholdShrink;
	private int ratioExpand;
	private int ratioShrink;
	
	public ManagerConfig() {
		
	}
	
	public ManagerConfig(double thresholdGrow, double thresholdShrink, int ratioExpand, int ratioShrink) {
		this.thresholdGrow = thresholdGrow;
		this.thresholdShrink = thresholdShrink;
		this.ratioExpand = ratioExpand;
		this.ratioShrink = ratioShrink;
	}
	
	public double getThresholdGrow() {
		return thresholdGrow;
	}
	public void setThresholdGrow(double thresholdGrow) {
		this.thresholdGrow = thresholdGrow;
	}
	public double getThresholdShrink() {
		return thresholdShrink;
	}
	public void setThresholdShrink(double thresholdShrink) {
		this.thresholdShrink = thresholdShrink;
	}
	public int getRatioExpand() {
		return ratioExpand;
	}
	public void setRatioExpand(int ratioExpand) {
		this.ratioExpand = ratioExpand;
	}
	public int getRatioShrink() {
		return ratioShrink;
	}
	public void setRatioShrink(int ratioShrink) {
		this.ratioShrink = ratioShrink;
	}
	
	
}
