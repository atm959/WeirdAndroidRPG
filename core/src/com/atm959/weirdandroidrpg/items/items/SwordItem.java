package com.atm959.weirdandroidrpg.items.items;

/**
 * Created by atm959 on 3/24/2022.
 */
public class SwordItem extends Item {
	public SwordItem() {
		this.atlasID = 0;
		this.isWeapon = true;
	}

	public SwordItem(SwordItem item) {
		super(item);
	}

	@Override
	public void onPickup() {

	}

	@Override
	public void onUse() {

	}

	@Override
	public SwordItem copy() {
		return new SwordItem(this);
	}
}
