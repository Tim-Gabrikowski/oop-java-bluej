import {
	Circle,
	Node,
	NodeProps,
	Rect,
	colorSignal,
	CubicBezier,
	Txt,
	initial,
	signal,
	vector2Signal,
	Path,
	Line,
	Layout,
} from "@motion-canvas/2d";
import {
	Color,
	ColorSignal,
	PossibleColor,
	SignalValue,
	SimpleSignal,
	SimpleVector2Signal,
	Vector2,
	Vector2Signal,
	all,
	createRef,
	createSignal,
	easeInOutCubic,
	tween,
	sequence,
	chain,
} from "@motion-canvas/core";

export interface ArrListEntryProps extends NodeProps {
	value: SignalValue<String>;
	type: SignalValue<String>;
	pointTo?: Vector2Signal<Node>;
}

export class ArrListEntry extends Node {
	@initial("0")
	@signal()
	public declare readonly value: SimpleSignal<String, this>;

	@initial("int")
	@signal()
	public declare readonly type: SimpleSignal<String, this>;

	@initial(new Vector2(100, 100))
	@vector2Signal()
	public declare readonly pointTo: SimpleVector2Signal<this>;

	private readonly container = createRef<Layout>();
	private readonly upperRect = createRef<Rect>();
	private readonly lowerRect = createRef<Rect>();
	private readonly arrow = createRef<Path>();
	public readonly textOpacity = createSignal(0);

	public constructor(props?: ArrListEntryProps) {
		super({
			...props,
		});

		this.add(
			<Node>
				<Layout ref={this.container} layout direction={"column"}>
					<Rect
						fill={"#444"}
						ref={this.upperRect}
						size={[500, 250]}
						layout
						direction={"row"}
						justifyContent={"space-evenly"}
						alignItems={"center"}
					>
						<Txt
							text={this.type()}
							fill={"FCBF49"}
							fontSize={100}
							fontFamily={"Ubuntu"}
							opacity={this.textOpacity}
						></Txt>
						<Txt
							text={this.value()}
							fill={"#fff"}
							fontSize={100}
							opacity={this.textOpacity}
							fontFamily={"Ubuntu"}
						></Txt>
					</Rect>
					<Rect
						fill={"#444"}
						ref={this.lowerRect}
						size={[500, 250]}
						justifyContent={"center"}
						alignItems={"center"}
					>
						<Path
							lineWidth={10}
							ref={this.arrow}
							position={[0, 0]}
							stroke={"#e13238"}
							data="m 256.2 -211.2 a 42.7 42.7 0 0 0 -42.6 -42.7 c -0.7 0 -341.3 -2.1 -342 -2.1 a 42.7 42.7 0 0 0 -42 43.4 c -0.2 24.8 23.4 43 42.7 42 h 237.2 l -352.9 353.6 a 42.8 42.8 0 0 0 60.4 60.9 c 0.1 0 353.8 -353.9 354 -354 v 238.1 a 42.7 42.7 0 1 0 85.3 0 z"
							scale={0.3}
							start={0}
							end={0}
						/>
					</Rect>
				</Layout>
			</Node>
		);
	}

	public *showArrow(duration: number) {
		yield* chain(
			this.arrow().end(1, duration),
			this.arrow().fill("f00", duration)
		);
	}
}
