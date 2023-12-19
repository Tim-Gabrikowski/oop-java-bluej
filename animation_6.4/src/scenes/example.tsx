import {makeScene2D} from '@motion-canvas/2d';
import {waitFor, waitUntil, createSignal} from '@motion-canvas/core';
import {Circle, Txt, Line} from '@motion-canvas/2d/lib/components';

export default makeScene2D(function* (view) {
  // Create your animations here

  let opacity = createSignal(0);

  view.add(
    <>
      <Txt text={"Java Adventures"} y={-100} fontSize={175} fill={"d62828"} fontFamily={'Ubuntu'} opacity={opacity}></Txt>
      <Txt text={"Arrays, ArrayLists, Iterators"} y={150} fontSize={80} fill={"FCBF49"} fontFamily={'Ubuntu'}></Txt>
    </>
  );

  yield* opacity(100, 1);
  yield* waitUntil("hideTitle");
});
