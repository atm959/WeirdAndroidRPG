package com.atm959.weirdandroidrpg.script;

import com.badlogic.gdx.Gdx;

/**
 * Created by atm959 on 4/9/2022.
 */

//A script executor, built like a virtual machine
public class ScriptExecutor {
	public int[] registers; //The different registers of the virtual machine
	public int[] stack; //The stack of the virtual machine
	public int[] memoryBlock; //General-purpose memory

	public byte[] program; //The program's bytecode, like a "ROM"

	//Initialize the script executor
	public ScriptExecutor(byte[] script) {
		registers = new int[0x25];
		stack = new int[0x80];
		memoryBlock = new int[256];
		this.program = script;
	}

	//Functions to manipulate the program counter
	public int getPC() {
		return registers[ScriptRegisterOffsets.PC];
	}

	public void setPC(int newPC) {
		registers[ScriptRegisterOffsets.PC] = newPC;
	}

	public void incrementPC() {
		registers[ScriptRegisterOffsets.PC]++;
	}

	//Get the next byte from the program
	public byte getNextByte() {
		byte val = program[getPC()];
		incrementPC();
		return val;
	}

	//Get the next integer from the program
	public int getNextInt() {
		byte val1 = program[getPC()];
		incrementPC();
		byte val2 = program[getPC()];
		incrementPC();
		byte val3 = program[getPC()];
		incrementPC();
		byte val4 = program[getPC()];
		incrementPC();
		return (int) ((val1 << 24) | (val2 << 16) | (val3 << 8) | val4);
	}

	//Functions to manipulate registers
	public int getRegisterValue(byte registerNum) {
		return registers[registerNum];
	}

	public void setRegisterValue(byte registerNum, int value) {
		registers[registerNum] = value;
	}

	public void incrementRegisterValue(byte registerNum) {
		registers[registerNum]++;
	}

	//Functions to manipulate memory
	public int getMemoryValue(byte offset) {
		return memoryBlock[offset];
	}

	public void setMemoryValue(byte offset, int value) {
		memoryBlock[offset] = value;
	}

	public void incrementMemoryValue(byte offset) {
		memoryBlock[offset]++;
	}

	//Dump a small bit of the virtual machine's state
	public void dumpState() {
		Gdx.app.log("SCRIPT_EXECUTOR", "PC: " + Integer.toHexString(getPC()));
		Gdx.app.log("SCRIPT_EXECUTOR", "R00: " + Integer.toHexString(getRegisterValue((byte) 0x00)));
		Gdx.app.log("SCRIPT_EXECUTOR", "M000: " + Integer.toHexString(getMemoryValue((byte) 0x00)));
	}

	//Execute the next instruction
	public void executeNext() {
		byte instructionGroup = getNextByte();
		//Gdx.app.log("SCRIPT_EXECUTOR", "Instruction Group: " + instructionGroup);
		byte instructionOffset = getNextByte();
		//Gdx.app.log("SCRIPT_EXECUTOR", "Instruction Offset: " + instructionOffset);

		switch (instructionGroup) {
			case ScriptInstructions.INSTRUCTIONGROUP_DEBUG:
				switch (instructionOffset) {
					case ScriptInstructions.INSTRUCTION_NOP:
						//Do nothing
						break;
					case ScriptInstructions.INSTRUCTION_DUMPSTATE:
						dumpState();
						break;
				}
				break;
			case ScriptInstructions.INSTRUCTIONGROUP_BRANCHING:
				switch (instructionOffset) {
					case ScriptInstructions.INSTRUCTION_JUMP:
						int newPC = getNextInt();
						Gdx.app.log("SCRIPT_EXECUTOR", "Jump to " + Integer.toHexString(newPC));
						setPC(newPC);
						break;
					case ScriptInstructions.INSTRUCTION_GOSUB:
						break;
					case ScriptInstructions.INSTRUCTION_RETURN:
						break;
				}
				break;
			case ScriptInstructions.INSTRUCTIONGROUP_ARITHMETIC:
				switch (instructionOffset) {
					case ScriptInstructions.INSTRUCTION_INC:
						byte addressingMode = getNextByte();
						switch (addressingMode) {
							case ScriptAddressingModes.REGISTER:
								byte registerNum = getNextByte();
								Gdx.app.log("SCRIPT_EXECUTOR", "Increment register " + registerNum);
								incrementRegisterValue(registerNum);
								break;
							case ScriptAddressingModes.MEMORY:
								byte memOffset = getNextByte();
								Gdx.app.log("SCRIPT_EXECUTOR", "Increment memory offset " + memOffset);
								incrementMemoryValue(memOffset);
								break;
						}
						break;
				}
				break;
		}
	}
}
