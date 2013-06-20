package com.example.babyprogressmap;

public class NavigationRule {

	private ActivityEnum previous = ActivityEnum.Null;
	private ActivityEnum current = ActivityEnum.Null;
	private ActivityEnum next = ActivityEnum.Null;

	private boolean isForward = false;

	public NavigationRule(ActivityEnum previous, ActivityEnum current,
			ActivityEnum next, boolean isForward) {
		this.previous = previous;
		this.current = current;
		this.next = next;
		this.isForward = isForward;
	}

	public boolean equals(NavigationRule rule) {
		return this.previous.equals(rule.previous)
				& this.current.equals(rule.current)
				& this.next.equals(rule.next)
				& this.isForward == rule.isForward;

	}

	public boolean almostEquivalent(ActivityEnum previous,
			ActivityEnum current, boolean isForward) {
		return this.previous.equals(previous) & this.current.equals(current)
				& this.isForward == isForward;
	}

	public ActivityEnum getNext() {
		return this.next;
	}
}
