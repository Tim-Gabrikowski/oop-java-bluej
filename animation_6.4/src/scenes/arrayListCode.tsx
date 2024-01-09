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

export default makeScene2D(function* (view) {
	const codeRef = createRef<CodeBlock>();

	yield* slideTransition(Direction.Right);

	yield view.add(
		<CodeBlock ref={codeRef} language="java" code={""} scale={2} />
	);

	yield* waitUntil("Import statement");
	yield* codeRef().edit(1)`${insert("int[] zahlen;")}`;

	yield* waitUntil("new list");
	yield* codeRef().edit(1)`int[] zahlen${insert(" = new int[]")};`;

	yield* waitUntil("insert type");
	yield* codeRef().edit(1)`int[] zahlen = new int[${insert("5")}];`;
	yield* codeRef().selection(range(0, 0, 1, 0), 1);

	yield* waitUntil("construktor");
	yield* codeRef().edit(
		1
	)`int[] zahlen = new int[5];${insert("\nzahlen = randomInts();")}`;

	yield* waitUntil("constructor type");
	yield* codeRef().edit(
		1
	)`int[] zahlen = new int[5]; \nzahlen = randomInts();${insert("\nint a = zahlen[2];")}`;

	yield* waitUntil("add element");
	yield* codeRef().edit(
		1
	)`int[] zahlen = new int[5]; \nzahlen = randomInts();\nint a = zahlen[2];${insert("\nzahlen[3];")}`;

	yield* waitUntil("get element");
	yield* codeRef().edit(
		1
	)`int[] zahlen = new int[5]; \nzahlen = randomInts();\nint a = zahlen[2];\nzahlen[3]${insert(" = 42")};`;
	yield* codeRef().selection(range(0, 0, 5, 0), 1);

	yield* waitUntil("Wset element");
	yield* codeRef().selection(range(0, 0, 1, 0), 1);

	yield* waitUntil("remove element");
	yield* codeRef().selection(range(1, 0, 2, 0), 1);

	yield* waitUntil("for loop");
	yield* codeRef().selection(range(2, 0, 3, 0), 1);

	// FOR LOOP STUFF

	yield* waitUntil("END SCENE");
});
