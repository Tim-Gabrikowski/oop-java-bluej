import { makeProject } from "@motion-canvas/core";

import example from "./scenes/example?scene";
import arrays from "./scenes/arrays?scene";
import arraysCode from "./scenes/arraysCode?scene";
import arrayDelete from "./scenes/array_delete?scene";
import arrayLists from "./scenes/arrayLists?scene";
import arrayListCode from "./scenes/arrayListCode?scene";
import forLoops from "./scenes/forLoops?scene";

import "./global.css";
import audio from "./assets/voice.wav";

export default makeProject({
	scenes: [
		example,
		arrays,
		arraysCode,
		arrayDelete,
		arrayLists,
		arrayListCode,
		forLoops,
	],
	audio: audio,
});
