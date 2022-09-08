//GL ES specific stuff
#ifdef GL_ES
#define LOWP lowp
precision mediump float;
#else
#define LOWP
#endif
varying LOWP vec4 vColor;
varying vec2 vTexCoord;
uniform sampler2D u_texture;
uniform sampler2D u_dudvMap;
uniform lowp float u_time;
void main(void) {
   vec4 dudvColor = texture2D(u_dudvMap, vec2(vTexCoord.x + mod(u_time, 1.0), vTexCoord.y + mod(u_time, 1.0)));
   vec4 dudvColor2 = texture2D(u_dudvMap, vec2(vTexCoord.x + mod(u_time, 1.0), vTexCoord.y));
   vec4 finalDudv = mix(dudvColor, dudvColor2, 0.5);
   vec2 distortion = vec2((finalDudv.r * 2.0) - 1.0, (finalDudv.g * 2.0) - 1.0) * 0.01;
   vec2 newTexCoord = vec2(vTexCoord.x + distortion.x, vTexCoord.y + distortion.y);
   vec4 newColor = texture2D(u_texture, newTexCoord);
   newColor *= vec4(1.0, 1.0, 2.0 + ((distortion.x + distortion.y) * 100.0), 1.0);
   gl_FragColor = vColor * newColor;
}