package com.example.babyprogressmap;

import java.util.ArrayList;

public class NavigationRuleCollection extends ArrayList<NavigationRule> {

	public NavigationRuleCollection() {

	}

	public NavigationRule getTheSame(ActivityEnum previous,
			ActivityEnum current, boolean isForward) {
		for (NavigationRule rule : this) {
			if (rule.almostEquivalent(previous, current, isForward))
				return rule;
		}
		return null;
	}

}
