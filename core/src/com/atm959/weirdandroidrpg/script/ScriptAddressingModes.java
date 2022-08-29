package com.atm959.weirdandroidrpg.script;

/**
 * Created by atm959 on 4/11/2022.
 */

//Different addressing modes for script instructions
public class ScriptAddressingModes {
	public static final byte CONSTANT = 0x00; //Read a constant value
	public static final byte REGISTER = 0x01; //Read or write a register
	public static final byte MEMORY = 0x02; //Read or write a memory block address
}
