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
	yield* codeRef().edit(1)`${insert("import java.util.ArrayList;")}`;

	yield* waitUntil("new list");
	yield* codeRef().edit(
		1
	)`import java.util.ArrayList;${insert("\nArrayList<> list;")}`;

	yield* waitUntil("insert type");
	yield* codeRef().edit(
		1
	)`import java.util.ArrayList;\nArrayList<${insert("int")}> list;`;

	yield* waitUntil("construktor");
	yield* codeRef().edit(
		1
	)`import java.util.ArrayList;\nArrayList<int> list${insert(" = new ArrayList<>()")};`;

	yield* waitUntil("constructor type");
	yield* codeRef().edit(
		1
	)`import java.util.ArrayList;\nArrayList<int> list = new ArrayList<${insert("int")}>();`;
	yield* codeRef().selection(range(1, 0, 2, 0), 1);

	yield* waitUntil("add element");
	yield* codeRef().edit(
		1
	)`import java.util.ArrayList;\nArrayList<int> list = new ArrayList<int>();${insert("\nlist.add(5);\nlist.add(10);\nlist.add(19);")}`;

	yield* waitUntil("get element");
	yield* codeRef().edit(
		1
	)`import java.util.ArrayList;\nArrayList<int> list = new ArrayList<int>();\nlist.add(5);\nlist.add(10);\nlist.add(19);${insert("\nint a = list.get(0);")}`;

	yield* waitUntil("set element");
	yield* codeRef().edit(
		1
	)`import java.util.ArrayList;\nArrayList<int> list = new ArrayList<int>();\nlist.add(5);\nlist.add(10);\nlist.add(19);\nint a = list.get(0);${insert("\nlist.set(1, 16);")}`;

	yield* waitUntil("remove element");
	yield* codeRef().edit(
		1
	)`import java.util.ArrayList;\nArrayList<int> list = new ArrayList<int>();\nlist.add(5);\nlist.add(10);\nlist.add(19);\nint a = list.get(0);\nlist.set(1, 16);${insert("\nlist.remove(2);")}`;

	yield* waitUntil("END SCENE");
});
