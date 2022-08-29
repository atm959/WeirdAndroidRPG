package com.atm959.weirdandroidrpg.script;

/**
 * Created by atm959 on 4/9/2022.
 */

//Different instructions that the script executor can execute
public class ScriptInstructions {
	public static final byte INSTRUCTIONGROUP_DEBUG = 0x00;
	public static final byte INSTRUCTION_NOP = 0x00;
	public static final byte INSTRUCTION_DUMPSTATE = 0x01;

	public static final byte INSTRUCTIONGROUP_BRANCHING = 0x01;
	public static final byte INSTRUCTION_JUMP = 0x00;
	public static final byte INSTRUCTION_GOSUB = 0x01;
	public static final byte INSTRUCTION_RETURN = 0x02;

	public static final byte INSTRUCTIONGROUP_ARITHMETIC = 0x02;
	public static final byte INSTRUCTION_INC = 0x00;
}