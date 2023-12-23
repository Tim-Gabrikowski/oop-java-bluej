import { makeScene2D } from "@motion-canvas/2d";
import {
	waitFor,
	waitUntil,
	createSignal,
	createRef,
	all,
	chain,
	slideTransition,
	Direction,
} from "@motion-canvas/core";
import {
	CodeBlock,
	insert,
	range,
} from "@motion-canvas/2d/lib/components/CodeBlock";
import {
	Circle,
	Txt,
	Line,
	Grid,
	Rect,
} from "@motion-canvas/2d/lib/components";

export default makeScene2D(function* (view) {
	const codeRef = createRef<CodeBlock>();

	yield* slideTransition(Direction.Right);

	yield view.add(
		<CodeBlock ref={codeRef} language="java" code={""} scale={2} />
	);

	yield* waitUntil("Insert Array");
	yield* codeRef().edit(1)`${insert("int[] zahlen;")}`;

	yield* waitUntil("init");
	yield* codeRef().edit(1)`int[] zahlen${insert(" = new int[]")};`;

	yield* waitUntil("Insert size");
	yield* codeRef().edit(1)`int[] zahlen = new int[${insert("5")}];`;
	yield* codeRef().selection(range(0, 0, 1, 0), 1);

	yield* waitUntil("FillArray");
	yield* codeRef().edit(
		1
	)`int[] zahlen = new int[5];${insert("\nzahlen = randomInts();")}`;

	yield* waitUntil("Read Array");
	yield* codeRef().edit(
		1
	)`int[] zahlen = new int[5]; \nzahlen = randomInts();${insert("\nint a = zahlen[2];")}`;

	yield* waitUntil("Save Value");
	yield* codeRef().edit(
		1
	)`int[] zahlen = new int[5]; \nzahlen = randomInts();\nint a = zahlen[2];${insert("\nzahlen[3];")}`;

	yield* waitUntil("Save Value 2");
	yield* codeRef().edit(
		1
	)`int[] zahlen = new int[5]; \nzahlen = randomInts();\nint a = zahlen[2];\nzahlen[3]${insert(" = 42")};`;
	yield* codeRef().selection(range(0, 0, 5, 0), 1);

	yield* waitUntil("Wt 1");
	yield* codeRef().selection(range(0, 0, 1, 0), 1);

	yield* waitUntil("Wt 2");
	yield* codeRef().selection(range(1, 0, 2, 0), 1);

	yield* waitUntil("Wt 3");
	yield* codeRef().selection(range(2, 0, 3, 0), 1);

	yield* waitUntil("Wt 4");
	yield* codeRef().selection(range(3, 0, 4, 0), 1);

	yield* waitUntil("END SCENE");
});
