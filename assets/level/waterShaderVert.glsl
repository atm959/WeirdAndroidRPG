attribute vec4 %PosAttrib%;
attribute vec4 %ColAttrib%;
attribute vec2 %TxCAttrib%0;
uniform mat4 u_projTrans;

varying vec4 vColor;
varying vec2 vTexCoord;
void main() {
	vColor = %ColAttrib%;
	vTexCoord = %TxCAttrib%0;
	gl_Position =  u_projTrans * %PosAttrib%;
}