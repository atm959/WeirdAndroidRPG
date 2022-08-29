package com.atm959.weirdandroidrpg.items.items;

/**
 * Created by atm959 on 3/29/2022.
 */
public class NothingItem extends Item {
	public NothingItem() {
		this.isRendered = false;
	}

	public NothingItem(NothingItem item) {
		super(item);
	}

	@Override
	public NothingItem copy() {
		return new NothingItem(this);
	}
}
