import { Layout, makeScene2D } from "@motion-canvas/2d";
import {
	waitFor,
	waitUntil,
	createSignal,
	createRef,
	all,
	chain,
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
	let arrayIndexOpacity = createSignal(0);
	let dataEntryTypeOpacity = createSignal(0);
	let dataEntryValueOpacitys = [];
	for (let i = 0; i < 5; i++) {
		dataEntryValueOpacitys[i] = createSignal(0);
	}
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
			<Layout layout opacity={arrayOpacity} gap={20}>
				<Rect
					layout
					direction={"column"}
					alignItems={"center"}
					justifyContent={"center"}
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
					layout
					direction={"column"}
					alignItems={"center"}
					justifyContent={"center"}
					stroke={"#fff"}
					fill={"#444"}
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
					fill={"#444"}
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

	yield* opacity(100, 1);

	yield* waitUntil("1d-raster");
	yield* all(
		titleText().position.x(-1600, 1),
		titleText().position.y(-900, 1),
		titleText().fontSize(200, 1),
		arrayOpacity(1, 1)
	);

	yield* waitUntil("DataType");
	yield* dataEntryTypeOpacity(1, 1);

	yield* waitUntil("FillNumbers");
	yield* chain(
		dataEntryValueOpacitys[0](1, 0.5),
		dataEntryValueOpacitys[1](1, 0.5),
		dataEntryValueOpacitys[2](1, 0.5),
		dataEntryValueOpacitys[3](1, 0.5),
		dataEntryValueOpacitys[4](1, 0.5)
	);

	yield* waitUntil("ShowIndex");
	yield* arrayIndexOpacity(1, 1);

	yield* waitUntil("END SCENE");
});
