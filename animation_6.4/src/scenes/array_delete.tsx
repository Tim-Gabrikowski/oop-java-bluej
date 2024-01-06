import { Layout, makeScene2D } from "@motion-canvas/2d";
import {
	waitFor,
	waitUntil,
	createSignal,
	createRef,
	all,
	chain,
	Vector2,
	sequence,
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
	let arrayOpacity = createSignal(0);
	let arrayIndexOpacity = createSignal(1);
	let dataEntryTypeOpacity = createSignal(1);
	let dataEntryValueOpacitys = [];
	for (let i = 0; i < 5; i++) {
		dataEntryValueOpacitys[i] = createSignal(1);
	}

	let entry1 = createRef<Rect>();
	let entry2 = createRef<Rect>();
	let entryGap = createRef<Rect>();
	let entry3 = createRef<Rect>();
	let entry4 = createRef<Rect>();
	let entry5 = createRef<Rect>();
	let entry6 = createRef<Rect>();

	view.add(
		<>
			<Layout opacity={arrayOpacity}>
				<Rect
					layout
					direction={"column"}
					alignItems={"center"}
					justifyContent={"center"}
					x={-1040}
					ref={entry1}
					stroke={"#fff"}
					fill={"#444"}
					size={500}
				>
					<Txt
						text={"1"}
						fill={"#fff"}
						fontSize={200}
						fontFamily={"Ubuntu"}
						opacity={dataEntryValueOpacitys[0]}
					></Txt>
					<Txt
						text={"(int)"}
						fill={"FCBF49"}
						fontSize={75}
						fontFamily={"Ubuntu"}
						opacity={dataEntryTypeOpacity}
					></Txt>
				</Rect>
				<Rect
					x={-520}
					ref={entryGap}
					stroke={"#aaa"}
					lineWidth={5}
					opacity={0}
					size={500}
				></Rect>
				<Rect
					layout
					direction={"column"}
					alignItems={"center"}
					justifyContent={"center"}
					stroke={"#fff"}
					ref={entry2}
					fill={"#444"}
					x={-520}
					size={500}
				>
					<Txt
						text={"5"}
						fill={"#fff"}
						fontSize={200}
						fontFamily={"Ubuntu"}
						opacity={dataEntryValueOpacitys[1]}
					></Txt>
					<Txt
						text={"(int)"}
						fill={"FCBF49"}
						fontSize={75}
						fontFamily={"Ubuntu"}
						opacity={dataEntryTypeOpacity}
					></Txt>
				</Rect>
				<Rect
					layout
					direction={"column"}
					alignItems={"center"}
					justifyContent={"center"}
					stroke={"#fff"}
					fill={"#444"}
					ref={entry3}
					x={0}
					size={500}
				>
					<Txt
						text={"7"}
						fill={"#fff"}
						fontSize={200}
						fontFamily={"Ubuntu"}
						opacity={dataEntryValueOpacitys[2]}
					></Txt>
					<Txt
						text={"(int)"}
						fill={"FCBF49"}
						fontSize={75}
						fontFamily={"Ubuntu"}
						opacity={dataEntryTypeOpacity}
					></Txt>
				</Rect>
				<Rect
					layout
					direction={"column"}
					alignItems={"center"}
					justifyContent={"center"}
					stroke={"#fff"}
					fill={"#444"}
					ref={entry4}
					x={520}
					size={500}
				>
					<Txt
						text={"4"}
						fill={"#fff"}
						fontSize={200}
						fontFamily={"Ubuntu"}
						opacity={dataEntryValueOpacitys[3]}
					></Txt>
					<Txt
						text={"(int)"}
						fill={"FCBF49"}
						fontSize={75}
						fontFamily={"Ubuntu"}
						opacity={dataEntryTypeOpacity}
					></Txt>
				</Rect>
				<Rect
					layout
					direction={"column"}
					alignItems={"center"}
					justifyContent={"center"}
					stroke={"#fff"}
					x={1040}
					fill={"#444"}
					ref={entry5}
					size={500}
				>
					<Txt
						text={"9"}
						fill={"#fff"}
						fontSize={200}
						fontFamily={"Ubuntu"}
						opacity={dataEntryValueOpacitys[4]}
					></Txt>
					<Txt
						text={"(int)"}
						fill={"FCBF49"}
						fontSize={75}
						fontFamily={"Ubuntu"}
						opacity={dataEntryTypeOpacity}
					></Txt>
				</Rect>
				<Rect
					layout
					direction={"column"}
					alignItems={"center"}
					justifyContent={"center"}
					stroke={"#fff"}
					x={1040}
					fill={"#444"}
					opacity={0}
					ref={entry6}
					size={500}
				></Rect>
			</Layout>
			<Layout layout opacity={arrayIndexOpacity} y={350} gap={460}>
				<Txt
					text={"0"}
					fontSize={100}
					fontFamily={"Ubuntu"}
					fill={"#fff"}
				></Txt>
				<Txt
					text={"1"}
					fontSize={100}
					fontFamily={"Ubuntu"}
					fill={"#fff"}
				></Txt>
				<Txt
					text={"2"}
					fontSize={100}
					fontFamily={"Ubuntu"}
					fill={"#fff"}
				></Txt>
				<Txt
					text={"3"}
					fontSize={100}
					fontFamily={"Ubuntu"}
					fill={"#fff"}
				></Txt>
				<Txt
					text={"4"}
					fontSize={100}
					fontFamily={"Ubuntu"}
					fill={"#fff"}
				></Txt>
			</Layout>
		</>
	);

	yield* arrayOpacity(100, 1);

	yield* waitUntil("Delete entry");

	yield* all(
		entry2().position(new Vector2(-520, -620), 1),
		entry2().opacity(0.5, 1)
	);
	yield* waitUntil("Gap");
	yield* entryGap().opacity(100, 1);

	yield* waitUntil("MoveRest");
	yield* entryGap().opacity(0, 0.5);
	yield* sequence(
		0.2,
		entry3().position(new Vector2(-520, 0), 1),
		entry4().position(new Vector2(0, 0), 1),
		entry5().position(new Vector2(520, 0), 1),
		entryGap().position(new Vector2(1040, 0), 1)
	);
	yield* entryGap().opacity(1, 0.5);

	yield* waitUntil("END SCENE");
});
