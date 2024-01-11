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
	edit,
	insert,
	range,
} from "@motion-canvas/2d/lib/components/CodeBlock";

export default makeScene2D(function* (view) {
	const codeRef = createRef<CodeBlock>();

	yield* slideTransition(Direction.Right);

	yield view.add(
		<CodeBlock ref={codeRef} language="java" code={""} scale={2} />
	);

	yield* waitUntil("empty for");
	yield* codeRef().edit(1)`${insert("for() {}")}`;

	yield* waitUntil("elements in for");
	yield* codeRef().edit(1)`for(${insert("before; condition; afterEach")}) {}`;

	yield* waitUntil("extended for");
	yield* codeRef().edit(
		1
	)`for(${edit("before; condition; afterEach", "arg1 : arg2")}) {}`;

	yield* waitUntil("for var");
	yield* codeRef().edit(1)`for(${edit("arg1", "int i")} : arg2) {}`;

	yield* waitUntil("for list");
	yield* codeRef().edit(1)`for(int i : ${edit("arg2", "list")}) {}`;

	yield* waitUntil("print elem");
	yield* codeRef().edit(
		1
	)`for(int i : list) {${insert("\n    System.out.println(i);\n")}}`;

	yield* waitUntil("END SCENE");
});
