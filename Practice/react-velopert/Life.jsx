import React, { useState, useEffect } from "react";

export default function LifeCycleExample() {
  const [count, setCount] = useState(0);

  useEffect(() => {
    console.log("Component Did Mount");
    return () => {
      console.log("Component Will Unmount");
    };
  }, []);

  useEffect(() => {
    console.log("Component Did Update");
  });

  const incrementCount = () => {
    setCount(count + 1);
  };

  console.log("Render");
  return (
    <div>
      <h1>Count: {count}</h1>
      <button onClick={incrementCount}>Increment</button>
    </div>
  );
}

