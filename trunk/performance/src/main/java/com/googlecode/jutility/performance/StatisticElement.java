package com.googlecode.jutility.performance;

class StatisticElement {
	private String name;
	private long count;
	private long time;
	private long start;
	private long end;
	private boolean available;

	StatisticElement(String name) {
		this.name = name;
		this.available = false;
	}

	protected void setCount() {
		this.count++;
	}

	protected void setStart() {
		this.start = System.currentTimeMillis();
		available = true;
	}

	protected void setEnd() {
		if (available == true) {
			this.end = System.currentTimeMillis();
		}
	}

	protected void setTime() {
		if (available == true) {
			this.time += this.end - this.start;
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("----------------------------------------").append("\n");
		sb.append("Name  :").append(name).append("\n");
		sb.append("Count :").append(count).append("\n");
		sb.append("Time  :").append(time).append("\n");
		sb.append("----------------------------------------").append("\n");
		return sb.toString();
	}
}
