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
	DEFAULT,
} from "@motion-canvas/core";
import {
	CodeBlock,
	edit,
	insert,
	range,
	remove,
} from "@motion-canvas/2d/lib/components/CodeBlock";

export default makeScene2D(function* (view) {
	const codeRef = createRef<CodeBlock>();

	yield view.add(
		<CodeBlock
			ref={codeRef}
			language="java"
			code={
				"for(int i = 0; i < list.size(); ++i) {\n    System.out.println(list.get(i));\n}"
			}
			scale={2}
		/>
	);
	yield* slideTransition(Direction.Right);

	yield* waitUntil("select index");
	yield* codeRef().selection(
		[...range(0, 4, 0, 14), ...range(0, 32, 0, 35)],
		1
	);

	yield* waitUntil("select count");
	yield* codeRef().selection(range(0, 15, 0, 31), 1);

	yield* waitUntil("undo selection");
	yield* codeRef().selection(DEFAULT, 1);

	yield* waitUntil("create iterator");
	yield* codeRef().edit(
		1
	)`${edit("for(int i = 0; i < list.size(); ++i) {\n    System.out.println(list.get(i));\n}", "Iterator it;")}`;

	yield* waitUntil("it type");
	yield* codeRef().edit(1)`Iterator${insert("<int>")} it;`;

	yield* waitUntil("init iterator");
	yield* codeRef().edit(1)`Iterator<int> it ${insert("= list.iterator()")};`;

	yield* waitUntil("empty while");
	yield* codeRef().edit(
		1
	)`Iterator<int> it = list.iterator();${insert("\nwhile() {}")}`;

	yield* waitUntil("while condition");
	yield* codeRef().edit(
		1
	)`Iterator<int> it = list.iterator();\nwhile(${insert("it.hasNext()")}) {}`;

	yield* waitUntil("currVar");
	yield* codeRef().edit(
		1
	)`Iterator<int> it = list.iterator();\nwhile(it.hasNext()) {${insert("\n    int i;\n")}}`;

	yield* waitUntil("currVar val");
	yield* codeRef().edit(
		1
	)`Iterator<int> it = list.iterator();\nwhile(it.hasNext()) {\n    int i${insert(" = it.next()")};\n}`;

	yield* waitUntil("print");
	yield* codeRef().edit(
		1
	)`Iterator<int> it = list.iterator();\nwhile(it.hasNext()) {\n    int i = it.next();\n${insert("    System.out.println(i);\n")}}`;

	yield* waitUntil("remove");
	yield* codeRef().edit(
		1
	)`Iterator<int> it = list.iterator();\nwhile(it.hasNext()) {\n    int i = it.next();\n    ${edit("System.out.println(i)", "it.remove()")};\n}`;

	yield* waitUntil("ListIterator");
	yield* codeRef().edit(
		1
	)`${edit("Iterator", "ListIterator")}<int> it = list.iterator();\nwhile(it.hasNext()) {\n    int i = it.next();\n    it.remove();\n}`;

	yield* waitUntil("set");
	yield* codeRef().edit(
		1
	)`ListIterator<int> it = list.iterator();\nwhile(it.hasNext()) {\n    int i = it.next();\n    it.${edit("remove()", "set(42)")};\n}`;

	yield* waitUntil("END SCENE");
});
