import React from "react";
import Counter from "./Counter";

function increase(number, callback) {
  setTimeout(() => {
    const result = number + 10;
    if (callback) {
      callback(result);
    }
  }, 1000);
}

function slowincrease(number, callback) {
  setTimeout(() => {
    const result = number + 10;
    if (callback) {
      callback(result);
    }
  }, 5000);
}

slowincrease(0, (result) => {
  console.log(result);
  console.log("아주 느린 첫번쨰 작업 완료");
  slowincrease(result, (result) => {
    console.log(result);
    console.log("아주 느린 두번째 작업 완료");
  });
});

console.log("작업 시작");
increase(0, (result) => {
  console.log(result);
  console.log("첫번째 작업 완료");
  increase(result, (result) => {
    console.log(result);
    console.log("두번째 작업 완료");
    increase(result, (result) => {
      console.log(result);
      console.log("세번째 작업 완료");
    });
  });
});

export default function AppCounter() {
  return <Counter />;
}
