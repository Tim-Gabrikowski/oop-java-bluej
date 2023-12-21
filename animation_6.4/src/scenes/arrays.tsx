import { Layout, makeScene2D } from "@motion-canvas/2d";
import {
	waitFor,
	waitUntil,
	createSignal,
	createRef,
	all,
} from "@motion-canvas/core";
import {
	Circle,
	Txt,
	Line,
	Grid,
	Rect,
} from "@motion-canvas/2d/lib/components";

export default makeScene2D(function* (view) {
	// Create your animations here

	let opacity = createSignal(0);
	let titleText = createRef<Txt>();

	view.add(
		<>
			<Txt
				text={"Array"}
				ref={titleText}
				y={0}
				fontSize={300}
				fill={"d62828"}
				fontFamily={"Ubuntu"}
				opacity={opacity}
			></Txt>
			<Layout direction={"row"}>
				<Rect fill={"#444"} size={500}></Rect>
				<Rect fill={"#444"} size={500}></Rect>
				<Rect fill={"#444"} size={500}></Rect>
				<Rect fill={"#444"} size={500}></Rect>
				<Rect fill={"#444"} size={500}></Rect>
			</Layout>
		</>
	);

	yield* opacity(100, 1);

	yield* waitUntil("1d-raster");
	yield* all(
		titleText().position.x(-1600, 1),
		titleText().position.y(-900, 1),
		titleText().fontSize(200, 1)
	);
});
