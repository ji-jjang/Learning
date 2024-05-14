import React from "react";
import Hello from "./Hello";
import Wrapper from "./Wrapper";

export default function AppWrapper() {
  return (
    <Wrapper>
      <Hello name="react" color="red" isSpecial={true} />
      <Hello color="pink" />
    </Wrapper>
  );
}
