import { makeScene2D } from "@motion-canvas/2d";
import {
	waitFor,
	waitUntil,
	createSignal,
	slideTransition,
	Direction,
} from "@motion-canvas/core";
import { Circle, Txt, Line } from "@motion-canvas/2d/lib/components";

export default makeScene2D(function* (view) {
	// Create your animations here

	let opacity = createSignal(0);

	view.add(
		<>
			<Txt
				text={"Cheers!"}
				y={0}
				fontSize={300}
				fill={"fff"}
				fontFamily={"Ubuntu"}
				opacity={opacity}
			></Txt>
		</>
	);

	yield* slideTransition(Direction.Right);

	yield* opacity(0, 0);
	yield* waitUntil("showEnd");
	yield* opacity(100, 1);

	yield* waitUntil("END SCENE");
	yield* opacity(0, 0.5);
});
