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
} from "@motion-canvas/core";
import {
	Circle,
	Txt,
	Line,
	Grid,
	Rect,
} from "@motion-canvas/2d/lib/components";

import { ArrListEntry } from "../components/arrListEntry";

export default makeScene2D(function* (view) {
	let titleText = createRef<Txt>();
	let listLayout = createRef<Layout>();
	let newElemRef = createRef<ArrListEntry>();
	const random = useRandom();

	let arrLists = [];

	for (let i = 0; i < 5; i++) {
		let ref = createRef<ArrListEntry>();
		arrLists[i] = {
			type: "int",
			value: random.nextInt(0, 100),
			ref: ref,
		};
	}

	view.add(
		<>
			<Txt
				text={"ArrayLists"}
				ref={titleText}
				y={0}
				fontSize={300}
				fill={"d62828"}
				fontFamily={"Ubuntu"}
				opacity={0}
			></Txt>
			<Layout
				layout
				ref={listLayout}
				position={[0, 0]}
				opacity={0}
				gap={100}
				justifyContent={"start"}
			>
				{arrLists.map((e: any) => (
					<ArrListEntry
						type={e.type}
						value={e.value}
						ref={e.ref}
					></ArrListEntry>
				))}
				<ArrListEntry
					type={"int"}
					value={"19"}
					ref={newElemRef}
					opacity={0}
				></ArrListEntry>
			</Layout>
		</>
	);

	yield* waitUntil("Show Title");
	yield* titleText().opacity(1, 1);

	yield* waitUntil("Show List");
	yield* sequence(
		0.5,
		titleText().position(new Vector2(0, -520), 1),
		listLayout().opacity(1, 1)
	);

	yield* waitUntil("Show Detail");

	let arrFuncs: ThreadGenerator[] = [];
	for (let i = 0; i < arrLists.length - 1; i++) {
		const element = arrLists[i];
		arrFuncs.push(element.ref().showArrow(1));
	}

	yield* sequence(
		0.25,
		...arrLists.map((e: any) => e.ref().textOpacity(1, 1)),
		...arrFuncs
	);

	yield* waitUntil("Add Entry");
	yield* newElemRef().opacity(1, 1);

	yield* waitUntil("Init Value");
	yield* newElemRef().textOpacity(1, 1);

	yield* waitUntil("Show arrow");
	yield* arrLists[arrLists.length - 1].ref().showArrow(1);

	yield* waitUntil("END SCENE");
});
