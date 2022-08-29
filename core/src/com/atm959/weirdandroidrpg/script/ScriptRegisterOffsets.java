package com.atm959.weirdandroidrpg.script;

/**
 * Created by atm959 on 4/9/2022.
 */

//Script executor register names and offsets
public class ScriptRegisterOffsets {
	//General-purpose registers
	public static final byte R0 = 0x00;
	public static final byte R1 = 0x01;
	public static final byte R2 = 0x02;
	public static final byte R3 = 0x03;
	public static final byte R4 = 0x04;
	public static final byte R5 = 0x05;
	public static final byte R6 = 0x06;
	public static final byte R7 = 0x07;
	public static final byte R8 = 0x08;
	public static final byte R9 = 0x09;
	public static final byte R10 = 0x0A;
	public static final byte R11 = 0x0B;
	public static final byte R12 = 0x0C;
	public static final byte R13 = 0x0D;
	public static final byte R14 = 0x0E;
	public static final byte R15 = 0x0F;
	public static final byte R16 = 0x10;
	public static final byte R17 = 0x11;
	public static final byte R18 = 0x12;
	public static final byte R19 = 0x13;
	public static final byte R20 = 0x14;
	public static final byte R21 = 0x15;
	public static final byte R22 = 0x16;
	public static final byte R23 = 0x17;
	public static final byte R24 = 0x18;
	public static final byte R25 = 0x19;
	public static final byte R26 = 0x1A;
	public static final byte R27 = 0x1B;
	public static final byte R28 = 0x1C;
	public static final byte R29 = 0x1D;
	public static final byte R30 = 0x1E;
	public static final byte R31 = 0x1F;

	//Program counter
	public static final byte PC = 0x20;

	//Stack pointer
	public static final byte SP = 0x21;

	//Error register
	public static final byte ER = 0x22;

	//Status register
	public static final byte ST = 0x24;
}
