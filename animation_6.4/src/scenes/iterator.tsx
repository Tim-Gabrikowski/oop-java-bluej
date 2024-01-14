import { Layout, makeScene2D } from "@motion-canvas/2d";
import {
	waitFor,
	waitUntil,
	createSignal,
	createRef,
	all,
	chain,
	sequence,
	Vector2,
	ThreadGenerator,
	useRandom,
	slideTransition,
	Direction,
} from "@motion-canvas/core";
import { Txt } from "@motion-canvas/2d/lib/components";

export default makeScene2D(function* (view) {
	view.add(
		<>
			<Txt
				text={"Iterator"}
				y={0}
				fontSize={300}
				fill={"d62828"}
				fontFamily={"Ubuntu"}
				opacity={1}
			></Txt>
		</>
	);
	yield* slideTransition(Direction.Right);

	yield* all();

	yield* waitUntil("END SCENE");
});
