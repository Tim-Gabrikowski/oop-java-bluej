import { makeProject } from "@motion-canvas/core";

import example from "./scenes/example?scene";
import arrays from "./scenes/arrays?scene";
import arraysCode from "./scenes/arraysCode?scene";

import "./global.css";
import audio from "./assets/voice.wav";

export default makeProject({
	scenes: [example, arrays, arraysCode],
	audio: audio,
});
