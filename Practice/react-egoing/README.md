# React

- 리액트는 사용자 정의 태그를 만드는 기술이다.

### prop

```jsx
<a
    href="/"
    onClick={function (event) {
    event.preventDefault();
    props.triggerHeader();
    }}

    <Header
    title="Web"
    triggerHeader={function () {
        alert("Header");
    }}
/>
```

- triggerHeader는 특정 동작을 트리거하기 위한 함수이다.
- 동작
  - triggerHeader를 먼저 prop으로 전달한다.
  - onClick 이벤트 핸들러에서 triggerHandler 함수를 사용한다.
    - 이때, a href의 기본 동작을 무시한다.
    - prop을 통해 제공된 triggerHandler 함수를 실행한다.
- Arrow function으로 변경

```jsx
onClick={(event) => {
event.preventDefault();
props.triggerHeader();
}}

<Header
title="Web"
triggerHeader={() => {
    alert("Header");
}}
/>
```

### state
- 리액트는 변경이 생기더라도 컴포넌트를 다시 랜더링하지 않음. useState를 통해 변경하면 다시 랜더링함.
```jsx
  // const _mode = useState("WELCOME");
  // const mode = _mode[0];
  // const setMode = _mode[1];

  const [mode, setMode] = useState("WELCOME");
```

